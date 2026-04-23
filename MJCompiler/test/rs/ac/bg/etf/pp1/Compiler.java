package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import java_cup.runtime.Symbol;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.util.Log4JUtils;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class Compiler {

	static {
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
	}
	
	public static void main(String[] args) throws Exception {
		
		Logger log = Logger.getLogger(Compiler.class);
		
		Reader br = null;
		try {
			File sourceCode = new File("test/program.mj");
			log.info("Compiling source file: " + sourceCode.getAbsolutePath());
			
			// kreiranje lexera i parsera nad ulaznim fajlom
			// lexer kreira tokene od simbola u ulaznom fajlu, a parser proverava da li se svi tokeni uklapaju u pravila definisana parserom
			br = new BufferedReader(new FileReader(sourceCode));
			Yylex lexer = new Yylex(br);
			
			MJParser p = new MJParser(lexer);
	        Symbol s = p.parse();  //formiranje AST
	        
	        Program prog = (Program)(s.value);
	        
			// ispis AST
			log.info(prog.toString(""));
			log.info("=====================================================================");
			
			// inicijalizacija tabele simbola
			Tab.init();
			Struct boolType = new Struct(Struct.Bool);
			Obj boolObj = Tab.insert(Obj.Type, "bool", boolType);
			boolObj.setAdr(-1);
			boolObj.setLevel(-1);
			Struct setType = new Struct(Struct.Enum, Tab.intType);
			Obj setObj = Tab.insert(Obj.Type, "set", setType);
			setObj.setAdr(-1);
			setObj.setLevel(-1);
			Obj addMeth = Tab.insert(Obj.Meth, "add", Tab.noType);
			addMeth.setLevel(2);
			Tab.openScope();
			Obj setArrObj = Tab.insert(Obj.Var, "set", setType);
			setArrObj.setLevel(1);
			setArrObj.setFpPos(1);
			Obj numObj = Tab.insert(Obj.Var, "number", Tab.intType);
			numObj.setLevel(1);
			numObj.setFpPos(1);
			Tab.chainLocalSymbols(addMeth);
			Tab.closeScope();
			Obj addAllMeth = Tab.insert(Obj.Meth, "addAll", Tab.noType);
			addAllMeth.setLevel(2);
			Tab.openScope();
			Obj setAAObj = Tab.insert(Obj.Var, "set", setType);
			setAAObj.setLevel(1);
			setAAObj.setFpPos(1);
			Obj arrAAObj = Tab.insert(Obj.Var, "array", new Struct(Struct.Array, Tab.intType));
			arrAAObj.setLevel(1);
			arrAAObj.setFpPos(1);
			Tab.chainLocalSymbols(addAllMeth);
			Tab.closeScope();
			
			
			
			
			// semanticka analiza
			
			SemAnalyzer sa = new SemAnalyzer();
			prog.traverseBottomUp(sa);
			
			// ispis tebele simbola
			log.info("=====================================================================");
			Tab.dump();
			

			// provera da li postoji sintaksna ili semanticka greska u ulaznom fajlu
			if(!p.errorDetected && sa.passed()){
				// generisanje koda
				File objFile = new File("test/program.obj");
				if(objFile.exists()) objFile.delete();
				
				CodeGenerator codeGenerator = new CodeGenerator();
				prog.traverseBottomUp(codeGenerator);
				Code.dataSize = sa.nVars;
				Code.mainPc = codeGenerator.getMainPc();
				Code.write(new FileOutputStream(objFile));
				log.info("Parsiranje uspesno zavrseno!");
			}else{
				log.error("Parsiranje NIJE uspesno zavrseno!");
			}
			
		} 
		finally {
			if (br != null) try { br.close(); } catch (IOException e1) { log.error(e1.getMessage(), e1); }
		}

	}
	
	
}
