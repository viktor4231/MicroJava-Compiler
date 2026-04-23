
package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;

%%

%{

	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type) {
		return new Symbol(type, yyline+1, yycolumn);
	}
	
	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type, Object value) {
		return new Symbol(type, yyline+1, yycolumn, value);
	}

%}

%cup
%line
%column

// deklarisanje stanja COMMENT, koje ce se koristiti kasnije
%xstate COMMENT

%eofval{
	return new_symbol(sym.EOF);
%eofval}

%%

" " 	{ }
"\b" 	{ }
"\t" 	{ }
"\r\n" 	{ }
"\f" 	{ }

"program"   { return new_symbol(sym.PROG, yytext());} // kreiraj simbol za tekst u yytext(), taj text se za fiksne reci uvek poklapa sa regex-om
"print" 	{ return new_symbol(sym.PRINT, yytext()); }
"return" 	{ return new_symbol(sym.RETURN, yytext()); }
"read" 		{ return new_symbol(sym.READ, yytext()); }
"void" 		{ return new_symbol(sym.VOID, yytext()); }
"new" 		{ return new_symbol(sym.NEW, yytext()); }
"union" 	{ return new_symbol(sym.UNION, yytext()); }
"const" 	{ return new_symbol(sym.CONST, yytext()); }

"++" 		{ return new_symbol(sym.INC, yytext()); } // ne mora da bude iznad "+", jer lexer uvek prioritizuje duzu sekvencu
"--" 		{ return new_symbol(sym.DEC, yytext()); }
"+" 		{ return new_symbol(sym.PLUS, yytext()); }
"-" 		{ return new_symbol(sym.MINUS, yytext()); }
"*" 		{ return new_symbol(sym.MUL, yytext()); }
"/" 		{ return new_symbol(sym.DIV, yytext()); }
"%"			{ return new_symbol(sym.MOD, yytext()); }
"=" 		{ return new_symbol(sym.EQUAL, yytext()); }
";" 		{ return new_symbol(sym.SEMI, yytext()); }
":" 		{ return new_symbol(sym.COLON, yytext()); }
"." 		{ return new_symbol(sym.DOT, yytext()); }
"," 		{ return new_symbol(sym.COMMA, yytext()); }
"(" 		{ return new_symbol(sym.LPAREN, yytext()); }
")" 		{ return new_symbol(sym.RPAREN, yytext()); }
"{" 		{ return new_symbol(sym.LBRACE, yytext()); }
"}"			{ return new_symbol(sym.RBRACE, yytext()); }
"["			{ return new_symbol(sym.LBRACKET, yytext()); }
"]"			{ return new_symbol(sym.RBRACKET, yytext()); }


"//" {yybegin(COMMENT);} // predji u stanje comment
<COMMENT> . {yybegin(COMMENT);} // ostani u stanju comment dok god ima karaktera u tom redu
<COMMENT> "\r\n" { yybegin(YYINITIAL); } // vrati se u default stanje kad dodjes na kraj reda

[0-9]+  						{return new_symbol(sym.NUMBER, Integer.parseInt(yytext())); } // yytext() mora da se prebaci u int jer je broj
("true"|"false")				{return new_symbol (sym.BOOL, yytext().equals("true")? 1:0); } // bool se cuva kao 0 ili 1, ne kao rec
"'"."'"							{return new_symbol(sym.CHAR, yytext().charAt(1)); } // ovaj niz ima 3 karaktera, nama treba samo srednji
([a-z]|[A-Z])[a-z|A-Z|0-9|_]* 	{return new_symbol (sym.IDENT, yytext()); } // kreiraj simbol za tekst u yytext(), koristi se nazive

. { System.err.println("Leksicka greska ("+yytext()+") u liniji "+(yyline+1)); } // ako simbol nije obuhvacen lexer-om, . ga hvata i baca gresku


