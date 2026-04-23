package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class SemAnalyzer extends VisitorAdaptor {
	
	private boolean errorDetected = false;
	Logger log = Logger.getLogger(getClass());
	private Obj programCurrent;
	private Struct declaredType; // deklarisani tip konstante/promenljive
	private int constValue;
	private Struct realType; // tip kog je zaista konstanta (za proveru kompatibilnosti kod inicijalizacije)
	private boolean varIsArray; // proverava da li deklaracija varijable sadrzi zagrade za niz
	private String declaredTypeName;
	private Obj methodCurrent = null;
	private boolean programHasMain;
	private ArrayList<String> universeScopeIdentList = new ArrayList<>();
	private ArrayList<Struct> methodFormalParamsTypes = new ArrayList<>();
	private Struct boolType = Tab.find("bool").getType();
	private Struct setType = Tab.find("set").getType();
	int nVars;
	
	public void report_error(String message, SyntaxNode info) {
		errorDetected  = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	
	 public boolean passed(){
	    	return !errorDetected;
	    }
	    
	 
	 @Override
	 public void visit(ProgName progName) {
		 programCurrent = Tab.insert(Obj.Prog, progName.getI1(), Tab.noType);
		 Collection<Obj> universeScopeSymbols = Tab.currentScope().getLocals().symbols();
		 universeScopeSymbols.forEach(symbol -> universeScopeIdentList.add(symbol.getName()));
		// System.out.println(universeScopeIdentList); //ispis svih kljucnih reci iz universe opsega
		Tab.openScope();
	 }
	 
	 @Override
	 public void visit(Program program) {
		 nVars = Tab.currentScope().getnVars();
		 Tab.chainLocalSymbols(programCurrent);
		 Tab.closeScope();
		 if(!programHasMain){
			 report_error("Greska: Program mora imati main metodu!", null);
		 }
	//	System.out.println(setIdentList); // ispis imena svih setova
	 }
	 
	 @Override
	 public void visit(Type type) {
		 Obj TypeObjLocal = Tab.find(type.getI1());
		 if(TypeObjLocal == Tab.noObj){
	    		report_error("Nije pronadjen tip " + type.getI1() + " u tabeli simbola! ", type);
	    		declaredType = Tab.noType;
	    		type.struct = declaredType;
	    	}else{
	    		if(Obj.Type == TypeObjLocal.getKind()){
	    			declaredType = TypeObjLocal.getType();
	    			declaredTypeName = TypeObjLocal.getName();
		    		type.struct = declaredType;
	    		}else{
	    			report_error("Greska: Ime " + type.getI1() + " ne predstavlja tip!", type);
	    			declaredType = Tab.noType;
		    		type.struct = declaredType;
	    		}
	    	}
	 } 
	 
	 @Override
	 public void visit(ConDeclOne conDeclOne) {
		 Obj constantValue = Tab.find(conDeclOne.getI1());
		 if(constantValue != Tab.noObj) {
			 report_error("Greska: Konstanta sa imenom " + conDeclOne.getI1() + " vec postoji!", conDeclOne);
		 }
		 else {
			 if(realType.assignableTo(declaredType)) {
			 constantValue = Tab.insert(Obj.Con, conDeclOne.getI1(), declaredType);
			 constantValue.setAdr(constValue);
			 }
			 else report_error("Greska: Nekompatibilnost tipova kod dodele vrednosti konstanti " + conDeclOne.getI1(), conDeclOne);
		}
	 }

	 @Override
	 public void visit(ConstValue_n constValue_n) {
		 constValue = constValue_n.getN1();
		 realType = Tab.intType;
	 }
	 
	 @Override
	 public void visit(ConstValue_b constValue_b) {
		 constValue = constValue_b.getB1();
		 realType = Tab.find("bool").getType();
	 }
	 
	 @Override
	 public void visit(ConstValue_c constValue_c) {
		 constValue = constValue_c.getC1();
		 realType = Tab.charType;
	 }

	 @Override
	 public void visit(VarDeclOne varDeclOne) {
		 Obj variableValue = Tab.find(varDeclOne.getI1());
		 if(variableValue != Tab.noObj) {
			 report_error("Greska: Promenljiva sa imenom " + varDeclOne.getI1() + " vec postoji!", varDeclOne);
		 }
		 else {
			 if (!varIsArray && declaredType != Tab.noType) variableValue = Tab.insert(Obj.Var, varDeclOne.getI1(), declaredType);
			 else variableValue = Tab.insert(Obj.Var, varDeclOne.getI1(), new Struct(Struct.Array, declaredType));
		 }
	 }
	 
	 @Override
	 public void visit(YesBrackets yesBrackets) {
		 varIsArray = true;
	 }
	 public void visit(NoBrackets noBrackets) {
		 varIsArray = false;
	 }
	 
	 @Override
	 public void visit(MethodName methodName) {
		 if(Tab.find(methodName.getI1()) != Tab.noObj) {
			 report_error("Greska: Metoda sa imenom " + methodName.getI1() + " vec postoji!", methodName);
		 }
		 else {
			 methodCurrent = Tab.insert(Obj.Meth, methodName.getI1(), Tab.noType);
			 methodName.obj = methodCurrent;
			 Tab.openScope();
			 if (methodName.getI1().equalsIgnoreCase("main")) programHasMain = true;
		 }
	 }
	 
	 @Override
	 public void visit(MethodDecl methodDecl) {
		 if(methodCurrent != null){
		 Tab.chainLocalSymbols(methodCurrent);
		 Tab.closeScope();
		 methodCurrent = null;
		 }
	 }
	 
	 @Override
	 public void visit(VarDeclOneMeth varDeclOneMeth) {
		 if (methodCurrent != null) {
			 String varName = varDeclOneMeth.getI1();
			 if(!universeScopeIdentList.contains(varName)) {
				 if(Tab.currentScope.findSymbol(varDeclOneMeth.getI1())!= null) {
					 report_error("Greska: Promenljiva sa imenom " + varDeclOneMeth.getI1() + " vec postoji u opsegu delovanja funkcije" + methodCurrent.getName(), varDeclOneMeth);
				 }
				 else {
					 if (!varIsArray && declaredType != Tab.noType) Tab.insert(Obj.Var, varDeclOneMeth.getI1(), declaredType);
					 else Tab.insert(Obj.Var, varDeclOneMeth.getI1(), new Struct(Struct.Array, declaredType));
				 }
			 }
			 else report_error("Greska: Koriscenje zabranjene kjucne reci " + varDeclOneMeth.getI1() + " kao identifikatora", varDeclOneMeth);
		 }
	 }
	 
	 // Kontekstni uslovi
	 
	 @Override
	 public void visit(Factor_c factor_c) {
		 factor_c.struct = Tab.charType;
	 }
	 
	 @Override
	 public void visit(Factor_n factor_n) {
		 factor_n.struct = Tab.intType;
	 }
	 
	 @Override
	 public void visit(Factor_nn factor_nn) {
		 factor_nn.struct = Tab.intType;
	 }
	 
	 @Override
	 public void visit(Factor_b factor_b) {
		 factor_b.struct = boolType;
	 }
	 @Override
	 public void visit(Factor_d factor_d) {
		 if(factor_d.getDesignator().obj == Tab.noObj) {
			 factor_d.struct = Tab.noType;
		 }
		 else {
			 if(factor_d.getDesignator().obj.getKind() == Obj.Meth) {
				 report_error("Greska: Neadekvatan tip promenljive " + factor_d.getDesignator().obj.getName() ,factor_d);
			 }
			 else {
				 factor_d.struct = factor_d.getDesignator().obj.getType();
			 }
		 }
	 }
	 @Override
	 public void visit(Factor_dn factor_dn) {
		 if(factor_dn.getDesignator().obj == Tab.noObj) {
			 factor_dn.struct = Tab.noType;
		 }
		 else {
			 factor_dn.struct = factor_dn.getDesignator().obj.getType();
		 }
	 }
	 
	 @Override
	 public void visit(Designator_i designator_i) {
		 Obj designatorObj = Tab.find(designator_i.getI1());
		 	if (designatorObj == Tab.noObj) {
		 		report_error("Greska: Koriscenje promenljive koja nije prethodno deklarisana ", designator_i);
		 		designator_i.obj = Tab.noObj;
		 	}
		 	else {
		 		if(designatorObj.getKind() == Obj.Meth || designatorObj.getKind() == Obj.Con || designatorObj.getKind() == Obj.Var) {
		 			designator_i.obj = designatorObj;
		 			if(designatorObj.getKind() == Obj.Con) {
		 				 report_info("Pristup simbolickoj konstanti: " + designator_i.getI1()  + "[Kind:" + designatorObj.getKind() + " Type:" + designatorObj.getType().getKind() + " Adr:" + designatorObj.getAdr() + " Level:" + designatorObj.getLevel() + "]", designator_i);
		 			}
		 			if(designatorObj.getKind() == Obj.Var && designatorObj.getLevel() == 0) {
		 				 report_info("Pristup globalnoj promenljivoj: " + designator_i.getI1()  + "[Kind:" + designatorObj.getKind() + " Type:" + designatorObj.getType().getKind() + " Adr:" + designatorObj.getAdr() + " Level:" + designatorObj.getLevel() + "]", designator_i);
		 			}
		 			if(designatorObj.getKind() == Obj.Var && designatorObj.getLevel() > 0) {
		 				 report_info("Pristup lokalnoj promenljivoj: " + designator_i.getI1() + "[Kind:" + designatorObj.getKind() + " Type:" + designatorObj.getType().getKind() + " Adr:" + designatorObj.getAdr() + " Level:" + designatorObj.getLevel() + "]", designator_i);
		 			}
		 		}
		 		else {
		 			report_error("Greska: Neadekvatan tip promenljive " + designator_i.getI1() ,designator_i);
		 			designator_i.obj = Tab.noObj;
		 		}
		 	}
	 }
	 
	 @Override
	 public void visit(DesignatorArrName designatorArrName) {
		 Obj designatorArrObj = Tab.find(designatorArrName.getI1());
		 	if (designatorArrObj == Tab.noObj) {
		 		report_error("Greska: Koriscenje promenljive koja nije prethodno deklarisana ", designatorArrName);
		 		designatorArrName.obj = Tab.noObj;
		 	}
		 	else {
		 		if(designatorArrObj.getType().getKind() == Struct.Array && designatorArrObj.getKind() == Obj.Var) {
		 			designatorArrName.obj = designatorArrObj;
		 		}
		 		else {
		 			report_error("Greska: Neadekvatan tip promenljive " + designatorArrName.getI1() ,designatorArrName);
		 			designatorArrName.obj = Tab.noObj;
		 		}
		 	}
	 }
	 
	 @Override
	 public void visit(Designator_array designator_array) {
		 if(designator_array.getExpression().struct.equals(Tab.intType)) {
			Obj arr = designator_array.getDesignatorArrName().obj;
		 	if(arr == Tab.noObj) {
			 	designator_array.obj = Tab.noObj;
		 	}
		 	else {
			 	designator_array.obj = new Obj(Obj.Elem, arr.getName() + "[$]", arr.getType().getElemType());
		 	}
		 }
		 else {
			 report_error("Greska: Indeks niza mora biti tipa int ",designator_array);
			 designator_array.obj = Tab.noObj;
		 }
	 }
	 
	 @Override
	 public void visit(Factor_new factor_new) {
		 if(factor_new.getExpression().struct.equals(Tab.intType)) {
			 factor_new.struct = new Struct(Struct.Array, declaredType);
		 }
		 else {
			 report_error("Greska: Velicina niza ili skupa mora biti tipa int ",factor_new);
			 factor_new.struct = Tab.noType;
		 }
	 }
	 
	 @Override
	 public void visit(Factor_expr_neg factor_expr_neg) {
		 if(factor_expr_neg.getExpression().struct.equals(Tab.intType)) {
			 factor_expr_neg.struct = Tab.intType;
		 }
		 else {
			 report_error("Greska: Vrednost izraza u zagradama mora biti tipa int ",factor_expr_neg);
			 factor_expr_neg.struct = Tab.noType;
		 }
	 }
	 
	 public void visit(Factor_expr factor_expr) {
		 factor_expr.struct = factor_expr.getExpression().struct;
	 }
	 
	 @Override
	 public void visit(SingleTerm singleTerm) {
		 singleTerm.struct = singleTerm.getFactor().struct;
	 }
	 
	 @Override
	 public void visit(MultiTerm multiTerm) {
		 if(multiTerm.getTerm().struct.equals(Tab.intType) && multiTerm.getFactor().struct.equals(Tab.intType)) {
			 multiTerm.struct = Tab.intType;
		 }
		 else {
			 report_error("Greska: Prilikom Mulop operacija oba operanda moraju biti tipa int ",multiTerm);
			 multiTerm.struct = Tab.noType;
		 }
	 }
	 
	 @Override
	 public void visit(SingleExpression singleExpression) {
		 singleExpression.struct = singleExpression.getTerm().struct;
	 }
	 
	 @Override
	 public void visit(MultiExpression multiExpression) {
		 if(multiExpression.getExpression().struct.equals(Tab.intType) && multiExpression.getTerm().struct.equals(Tab.intType)) {
			 multiExpression.struct = Tab.intType;
		 }
		 else {
			 report_error("Greska: Prilikom Addop operacija oba operanda moraju biti tipa int ",multiExpression);
			 multiExpression.struct = Tab.noType;
		 }
	 }
	 
	 @Override
	 public void visit(Factor_d_ap factor_d_ap) {
		 if(factor_d_ap.getDesignator().obj == Tab.noObj) {
			 report_error("Greska: Poziv nepostojece metode", factor_d_ap);
			 factor_d_ap.struct = Tab.noType;
		 }
		 else if(factor_d_ap.getDesignator().obj.getKind() != Obj.Meth) {
			 report_error("Greska: " + factor_d_ap.getDesignator().obj.getName() + " nije metoda", factor_d_ap);
			 factor_d_ap.struct = Tab.noType;
		 	}
		 	else {
		 		factor_d_ap.struct = factor_d_ap.getDesignator().obj.getType();
		 		if(factor_d_ap.getDesignator().obj.getLevel() != methodFormalParamsTypes.size()) {
		 			System.out.println(factor_d_ap.getDesignator().obj.getLevel());
		 			System.out.println(methodFormalParamsTypes.size());
		 			report_error("Greska: Broj stvarnih parametara ne odgovara metodi '" + factor_d_ap.getDesignator().obj.getName() + "'", factor_d_ap);
		 		}
		 		int i = 0;
		 		for (Obj o : factor_d_ap.getDesignator().obj.getLocalSymbols()) {
		 		    if (i >= factor_d_ap.getDesignator().obj.getLevel()) break; 
		 		    if(o.getType().getKind() == methodFormalParamsTypes.get(i).getKind()) {
		 		    	if(o.getType().getKind() == Struct.Array) {
		 		    		if(o.getType().getElemType() == Tab.noType) continue;
		 		    		if(o.getType().getElemType() != methodFormalParamsTypes.get(i).getElemType()) {
		 		    			report_error("Greska: Parametar na poziciji " + i + " metode '" + factor_d_ap.getDesignator().obj.getName() + "' nije adekvatnog tipa", factor_d_ap);	
		 		    		}
		 		    	}
		 		    }
		 		    else {
		 		    	report_error("Greska: Parametar na poziciji " + i + " metode '" + factor_d_ap.getDesignator().obj.getName() + "' nije adekvatnog tipa", factor_d_ap);
		 		    }
		 		    i++;
		 		}
		 	}
		 
		 methodFormalParamsTypes.clear();
	 }
	 public void visit(Factor_d_apn factor_d_apn) {
		 if(factor_d_apn.getDesignator().obj.getKind() == Obj.Meth) {
			 factor_d_apn.struct = factor_d_apn.getDesignator().obj.getType();
			 }
			 else {
				 factor_d_apn.struct = Tab.noType;
				 report_error("Greska: Pokusaj poziva nepostojece metode", factor_d_apn);
			 }
	 }
	 
	 @Override
	 public void visit(DesignatorStatement_inc designatorStatement_inc) {
		 Obj designator = designatorStatement_inc.getDesignator().obj;
		 if(!(((designator.getKind() == Obj.Var && designator.getType().equals(Tab.intType)) || (designator.getType().getKind() == Struct.Array && designator.getType().getElemType() == Tab.intType)) && !designator.getType().equals(setType))) {
			 report_error("Greska: Moguce je inkrementirati samo promenljive tipa int.", designatorStatement_inc);
		 }
	 }
	 
	 @Override
	 public void visit(DesignatorStatement_dec designatorStatement_dec) {
		 Obj designator = designatorStatement_dec.getDesignator().obj;
		 if(!(((designator.getKind() == Obj.Var && designator.getType().equals(Tab.intType)) || (designator.getType().getKind() == Struct.Array && designator.getType().getElemType() == Tab.intType)) && !designator.getType().equals(setType))) {
			 report_error("Greska: Moguce je dekrementirati samo promenljive tipa int.", designatorStatement_dec);
		 }
	 }
	 
	 public void visit(DesignatorStatement_union designatorStatement_union) {
		 Obj set1Obj = designatorStatement_union.getDesignator().obj;
		 Obj set2Obj = designatorStatement_union.getDesignator1().obj;
		 Obj set3Obj = designatorStatement_union.getDesignator2().obj;
		 if(set1Obj.getType() != setType) {
			 report_error("Greska: Promenljiva " + set1Obj.getName() + " nije tipa set.", designatorStatement_union);
		 }
		 if(set2Obj.getType() != setType) {
			 report_error("Greska: Promenljiva " + set2Obj.getName() + " nije tipa set.", designatorStatement_union);
		 }
		 if(set3Obj.getType() != setType) {
			 report_error("Greska: Promenljiva " + set3Obj.getName() + " nije tipa set.", designatorStatement_union);
		 }
	 }
	 
	 @Override
	 public void visit(DesignatorStatement_funcitionCall designatorStatement_funcitionCall) {
		 if(designatorStatement_funcitionCall.getDesignator().obj == Tab.noObj) {
			 report_error("Greska: Poziv nepostojece metode", designatorStatement_funcitionCall);				 
		 }
		 else if(designatorStatement_funcitionCall.getDesignator().obj.getKind() != Obj.Meth) {
			 report_error("Greska: " + designatorStatement_funcitionCall.getDesignator().obj.getName() + " nije metoda", designatorStatement_funcitionCall);	
		 	}
		 	else {
		 		if(designatorStatement_funcitionCall.getDesignator().obj.getLevel() != methodFormalParamsTypes.size()) {
		 			System.out.println(designatorStatement_funcitionCall.getDesignator().obj.getLevel());
		 			System.out.println(methodFormalParamsTypes.size());
		 			report_error("Greska: Broj stvarnih parametara ne odgovara metodi '" + designatorStatement_funcitionCall.getDesignator().obj.getName() + "'", designatorStatement_funcitionCall);
		 		}
		 		int i = 0;
		 		for (Obj o : designatorStatement_funcitionCall.getDesignator().obj.getLocalSymbols()) {
		 		    if (i >= designatorStatement_funcitionCall.getDesignator().obj.getLevel()) break; 
		 		    if(o.getType().getKind() == methodFormalParamsTypes.get(i).getKind()) {
		 		    	if(o.getType().getKind() == Struct.Array) {
		 		    		if(o.getType().getElemType() == Tab.noType) continue;
		 		    		if(o.getType().getElemType() != methodFormalParamsTypes.get(i).getElemType()) {
		 		    			report_error("Greska: Parametar na poziciji " + i + " metode '" + designatorStatement_funcitionCall.getDesignator().obj.getName() + "' nije adekvatnog tipa", designatorStatement_funcitionCall);	
		 		    		}
		 		    	}
		 		    }
		 		    else {
		 		    	report_error("Greska: Parametar na poziciji " + i + " metode '" + designatorStatement_funcitionCall.getDesignator().obj.getName() + "' nije adekvatnog tipa", designatorStatement_funcitionCall);
		 		    }
		 		    i++;
		 		}
		 	}
		 methodFormalParamsTypes.clear();
	 }
	 
	 @Override
	 public void visit(ActParsOne actParsOne) {
		 methodFormalParamsTypes.add(actParsOne.getExpression().struct);
	 }
	 
	 @Override
	 public void visit(DesignatorStatement_funcitionCallNoParams designatorStatement_funcitionCallNoParams) {
		 if(!(designatorStatement_funcitionCallNoParams.getDesignator().obj.getKind() == Obj.Meth)) {
			 if(designatorStatement_funcitionCallNoParams.getDesignator().obj != Tab.noObj) {
				 report_error("Greska: Metoda " + designatorStatement_funcitionCallNoParams.getDesignator().obj.getName() + " ne postoji", designatorStatement_funcitionCallNoParams);				 
			 }
		 }
	 }
	 
	 @Override
	 public void visit(DesignatorStatement_expr designatorStatement_expr) {
		 Obj designator = designatorStatement_expr.getDesignator().obj;
		 Struct expression = designatorStatement_expr.getExpression().struct;
		 if(!expression.assignableTo(designator.getType())) {
			 if(!(designator.getType().equals(setType) && expression.getElemType().assignableTo(designator.getType()))){
				 report_error("Greska: Pogresna dodela tipova.", designatorStatement_expr); 
			 }
		 }
	 }
	 
	 @Override
	 public void visit(Statement_r statement_r) {
		 Obj designator = statement_r.getDesignator().obj;
		 if(designator.getKind() != Obj.Var && (designator.getKind() != Obj.Elem)) {
			 report_error("Greska: Moguce je procitati samo promenljive i elemente niza.", statement_r);
		 }
		 else {
			 if(!(designator.getType().equals(Tab.intType) || designator.getType().equals(Tab.charType) || designator.getType().equals(boolType)) ) {
				 report_error("Greska: Promenljiva koja se cita mora biti tipa int, char ili bool.", statement_r);
			 }
		 }
	 }
	 
	 @Override
	 public void visit(Statement_p1 statement_p1) {
		 Struct type = statement_p1.getExpression().struct;
		 if(!type.equals(Tab.intType) && !type.equals(Tab.charType) && !type.equals(boolType) && !type.equals(setType)) {
			 report_error("Greska: Promenljiva koja se ispisuje mora biti tipa int, char, bool ili set.", statement_p1);
		 }
	 }
	 @Override
	 public void visit(NumConst_exists numConst_exists) {
		 numConst_exists.obj = new Obj(Obj.Con, "numConst", Tab.intType);
		 numConst_exists.obj.setAdr(numConst_exists.getN1()); 
	 }
	 
	 @Override
	 public void visit(NoNumConst noNumConst) {
		 noNumConst.obj = Tab.noObj;
		 noNumConst.obj.setAdr(0); 
	 }
}

