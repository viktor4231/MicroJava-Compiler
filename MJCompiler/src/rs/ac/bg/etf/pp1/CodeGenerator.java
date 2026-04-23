package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.HashMap;

import javax.lang.model.type.DeclaredType;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {
	
	private Struct setType = Tab.find("set").getType();
//	private HashMap<String, Integer> setovi = new HashMap<>();
	
	private int mainPC;
private int printSet;
private int union;
//	private boolean newSet;
//	private Integer newSetElem;

	public int getMainPc() {
		// TODO Auto-generated method stub
		return mainPC;
	}
	
	CodeGenerator(){
		Obj chr = Tab.find("chr");
		Obj ord = Tab.find("ord");
		chr.setAdr(Code.pc);
		ord.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(1);
		Code.put(1);
		Code.put(Code.load_n);
		Code.put(Code.exit);
		Code.put(Code.return_);
		
		Obj len = Tab.find("len");
        len.setAdr(Code.pc);
        Code.put(Code.enter);
        Code.put(1);
        Code.put(1);
        Code.put(Code.load_n);
        Code.put(Code.arraylength);
        Code.put(Code.exit);
        Code.put(Code.return_);
        
        Obj add = Tab.find("add");
        add.setAdr(Code.pc);
        Code.put(Code.enter);
        Code.put(2);
        Code.put(2);
        Code.put(Code.load_n);
        Code.put(Code.dup);
        Code.put(Code.dup);
        Code.put(Code.arraylength);
        Code.loadConst(1);
        Code.put(Code.sub);
        Code.put(Code.aload);
        Code.put(Code.dup);
        Code.put(Code.dup);
        Code.put(Code.store_2);
        Code.put(Code.load_n);
        Code.put(Code.arraylength);
        Code.loadConst(1);
        Code.put(Code.sub);
        //provera da li je skup pun
        Code.putFalseJump(Code.ne, Code.pc + 42);
        //provera da li broj vec postoji u skupu
        Code.loadConst(0);
        Code.put(Code.store_3);
        int loop = Code.pc;
        //ovde staviti proveru uslova da li se stiglo do kraja petlje
        Code.put(Code.load_3);
        Code.put(Code.load_2);
        Code.putFalseJump(Code.ne, Code.pc + 17);
        //provera da li su elementi skupa jednaki
        Code.put(Code.load_n);
        Code.put(Code.load_3);
        Code.put(Code.aload);
        Code.put(Code.load_1);
        Code.putFalseJump(Code.ne, Code.pc + 28);
        Code.put(Code.load_3);
        Code.loadConst(1);
        Code.put(Code.add);
        Code.put(Code.store_3);
        //Code.put(Code.load_3);
        //Code.loadConst(1);
        //Code.put(Code.add);
        //Code.put(Code.pop);
        Code.putJump(loop);
        //dodavanje elementa
        Code.put(Code.load_1);
        Code.put(Code.astore);
        Code.put(Code.load_n);
        Code.put(Code.dup);
        Code.put(Code.arraylength);
        Code.loadConst(1);
        Code.put(Code.sub);
        Code.put(Code.dup);
        Code.put(Code.load_n);
        Code.put(Code.dup_x1);
        Code.put(Code.pop);
        Code.put(Code.aload);
        Code.loadConst(1);
        Code.put(Code.add);
        Code.put(Code.astore);
        Code.putJump(Code.pc + 5);
        Code.put(Code.pop);
        Code.put(Code.pop);
        Code.put(Code.exit);
        Code.put(Code.return_);
        
        printSet = Code.pc;
        Code.put(Code.enter);
        Code.put(2);
        Code.put(2);
        Code.put(Code.load_n);
        Code.put(Code.dup);
        Code.put(Code.arraylength);
        Code.loadConst(1);
        Code.put(Code.sub);
        Code.put(Code.aload);
        Code.put(Code.store_2); //storuj duzinu niza u lokalnu promenljivu
        //prolaz kroz niz i ispis svih clanova
        Code.loadConst(0);
        Code.put(Code.store_3);
        int loopPrint = Code.pc;
        //provera da li se stiglo do kraja
        Code.put(Code.load_3);
        Code.put(Code.load_2);
        Code.putFalseJump(Code.ne, Code.pc + 22);
        //ispis
        Code.put(Code.load_n);
        Code.put(Code.load_3);
        Code.put(Code.aload);
        Code.loadConst(1);
        Code.put(Code.print);
        Code.loadConst(32);
        Code.loadConst(1);
        Code.put(Code.bprint);
        //inkrement
        Code.put(Code.load_3);
        Code.loadConst(1);
        Code.put(Code.add);
        Code.put(Code.store_3);
        Code.putJump(loopPrint);
        Code.put(Code.exit);
        Code.put(Code.return_);
        
        Obj addAll = Tab.find("addAll");
        addAll.setAdr(Code.pc);
        Code.put(Code.enter);
        Code.put(2);
        Code.put(2);
        Code.put(Code.load_1);
        Code.put(Code.arraylength);
        Code.put(Code.store_2); //storuj duzinu niza u lokalnu promenljivu
        //prolaz kroz niz i dodavanje clanova u skup
        Code.loadConst(0);
        Code.put(Code.store_3);
        int loopAddAll = Code.pc;
        //provera da li se stiglo do kraja
        Code.put(Code.load_3);
        Code.put(Code.load_2);
        Code.putFalseJump(Code.ne, Code.pc + 25);
        //ispis
        Code.put(Code.load_n);
        Code.put(Code.load_1);
        Code.put(Code.load_2);
        Code.put(Code.load_3);
        Code.put(Code.load_n);
        Code.put(Code.load_1);
        Code.put(Code.load_3);
        Code.put(Code.aload);
        Code.put(Code.call);
        Code.put2(add.getAdr() - Code.pc + 1);
        Code.put(Code.store_3);
        Code.put(Code.store_2);
        Code.put(Code.store_1);
        Code.put(Code.store_n);
        //inkrement
        Code.put(Code.load_3);
        Code.loadConst(1);
        Code.put(Code.add);
        Code.put(Code.store_3);
        Code.putJump(loopAddAll);
        Code.put(Code.exit);
        Code.put(Code.return_);
        
        union = Code.pc;
        Code.put(Code.enter);
        Code.put(2);
        Code.put(2);
        Code.put(Code.load_1);
        Code.put(Code.dup);
        Code.put(Code.arraylength);
        Code.loadConst(1);
        Code.put(Code.sub);
        Code.put(Code.aload);
        Code.put(Code.store_2); //storuj duzinu niza u lokalnu promenljivu
        //prolaz kroz niz i dodavanje clanova u skup
        Code.loadConst(0);
        Code.put(Code.store_3);
        int loopUnion = Code.pc;
        //provera da li se stiglo do kraja
        Code.put(Code.load_3);
        Code.put(Code.load_2);
        Code.putFalseJump(Code.ne, Code.pc + 25);
        //ispis
        Code.put(Code.load_n);
        Code.put(Code.load_1);
        Code.put(Code.load_2);
        Code.put(Code.load_3);
        Code.put(Code.load_n);
        Code.put(Code.load_1);
        Code.put(Code.load_3);
        Code.put(Code.aload);
        Code.put(Code.call);
        Code.put2(add.getAdr() - Code.pc + 1);
        Code.put(Code.store_3);
        Code.put(Code.store_2);
        Code.put(Code.store_1);
        Code.put(Code.store_n);
        //inkrement
        Code.put(Code.load_3);
        Code.loadConst(1);
        Code.put(Code.add);
        Code.put(Code.store_3);
        Code.putJump(loopUnion);
        Code.put(Code.exit);
        Code.put(Code.return_);

	}

	@Override
	public void visit(MethodName methodName) {
		methodName.obj.setAdr(Code.pc);
		if(methodName.getI1().equalsIgnoreCase("main")) mainPC = Code.pc;
		Code.put(Code.enter);
		Code.put(methodName.obj.getLevel());
		Code.put(methodName.obj.getLocalSymbols().size());
	}
	
	@Override
	public void visit(MethodDecl methodDecl) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	@Override
	public void visit(Statement_p1 statement_p1) {
		Code.loadConst(statement_p1.getNumConst().obj.getAdr());
		if(statement_p1.getExpression().struct.equals(Tab.charType)) Code.put(Code.bprint);
		else {
			if(statement_p1.getExpression().struct.equals(setType)) {
				Code.put(Code.call);
				Code.put2(printSet - Code.pc + 1);
			}
			else Code.put(Code.print);
		}
	}
	
	@Override
	public void visit(Factor_n factor_n) {
		Code.loadConst(factor_n.getN1());
	}
	
	@Override
	public void visit(Factor_nn factor_nn) {
		Code.loadConst(-factor_nn.getN1());
	}
	
	public void visit(Factor_b factor_b) {
		Code.loadConst(factor_b.getB1());
	}
	
	public void visit(Factor_c factor_c) {
		Code.loadConst(factor_c.getC1());
	}
	
	public void visit(Factor_d factor_d) {
		Code.load(factor_d.getDesignator().obj);
	}
	
	public void visit(Factor_dn factor_dn) {
		Code.load(factor_dn.getDesignator().obj);
		Code.put(Code.neg);
	}
	
	public void visit(Factor_expr_neg factor_expr_neg) {
		Code.put(Code.neg);
	}
	
	@Override
	public void visit(MultiExpression multiExpression) {
		if (multiExpression.getAddOp() instanceof AddOp_sub) {
			Code.put(Code.sub);
		}
		else Code.put(Code.add);
	}
	
	@Override 
	public void visit(DesignatorStatement_expr designatorStatement_expr) {
		Code.store(designatorStatement_expr.getDesignator().obj);
	}
	
	@Override
	public void visit(MultiTerm multiTerm) {
		if (multiTerm.getMullOp() instanceof MullOp_mul) {
			Code.put(Code.mul);
		}
		else if (multiTerm.getMullOp() instanceof MullOp_div) {
			Code.put(Code.div);
		}
		else Code.put(Code.rem);
	}
	
	@Override
	public void visit(DesignatorStatement_inc designatorStatement_inc) {
		Code.load(designatorStatement_inc.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(designatorStatement_inc.getDesignator().obj);
	}
	
	@Override
	public void visit(DesignatorStatement_dec designatorStatement_dec) {
		if(designatorStatement_dec.getDesignator().obj.getKind() == Obj.Elem) Code.put(Code.dup2);
		Code.load(designatorStatement_dec.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.store(designatorStatement_dec.getDesignator().obj);
	}
	
	@Override
	public void visit(DesignatorArrName designatorArrName) {
		Code.load(designatorArrName.obj);
	}
	
	@Override
	public void visit(Factor_new factor_new) {
		if(factor_new.getType().struct.equals(Tab.charType)) {
			Code.put(Code.newarray);
			Code.put(0);
		}
		else {
			if(factor_new.getType().struct.equals(setType)) {
				Code.loadConst(1);
				Code.put(Code.add); // alokacija dodatnog elementa niza da bi se pratio broj elemenata skupa
				Code.put(Code.newarray);
				Code.put(1);
			}
			else {
				Code.put(Code.newarray);
				Code.put(1);
			} 
		}
	}
	
	@Override
	public void visit(Statement_r statement_r) {
		if(statement_r.getDesignator().obj.getType().equals(Tab.charType)) Code.put(Code.bread);
		else Code.put(Code.read);
		Code.store(statement_r.getDesignator().obj);
	}
	
	@Override
	public void visit(DesignatorStatement_funcitionCall designatorStatement_funcitionCall) {
		Code.put(Code.call);
		Code.put2(designatorStatement_funcitionCall.getDesignator().obj.getAdr() - Code.pc + 1);
	}
	
	@Override
	public void visit(Factor_d_ap factor_d_ap) {
		Code.put(Code.call);
		Code.put2(factor_d_ap.getDesignator().obj.getAdr() - Code.pc + 1);
	}
	
	@Override
	public void visit(Factor_d_apn factor_d_apn) {
		Code.put(Code.call);
		Code.put2(factor_d_apn.getDesignator().obj.getAdr() - Code.pc + 1);
	}
	
	@Override
	public void visit(DesignatorStatement_union designatorStatement_union) {
		Code.load(designatorStatement_union.getDesignator().obj);
		Code.load(designatorStatement_union.getDesignator1().obj);
		Code.put(Code.call);
		Code.put2(union - Code.pc + 1);
		Code.load(designatorStatement_union.getDesignator().obj);
		Code.load(designatorStatement_union.getDesignator2().obj);
		Code.put(Code.call);
		Code.put2(union - Code.pc + 1);
	}
}
