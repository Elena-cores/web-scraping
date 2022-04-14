package es.ceu.gisi.modcomp.webcrawler.jflex;

import es.ceu.gisi.modcomp.webcrawler.jflex.lexico.*;

/**
 * Analizador léxico de páginas
 * @author Sergio Saugar García <sergio.saugargarcia@ceu.es>
 */

%%

%class HTMLParser
%unicode
%public
%line
%column
%type Token
%function next

%{
   StringBuffer string = new StringBuffer();
%}

LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]
Identificador = [:jletter:] [:jletterdigit:]*

%state STRING

%%

/* Definiciones */
<YYINITIAL> {

   {Identificador} { return new Token(Type.WORD, yytext(),yyline,yycolumn); }
   "=" { return new Token(Type.EQUAL, yytext(),yyline,yycolumn); }

   "<" { return new Token(Type.OPEN, yytext(),yyline,yycolumn); }

   ">" { return new Token(Type.CLOSE, yytext(),yyline,yycolumn); }

   "/" { return new Token(Type.SLASH, yytext(),yyline,yycolumn); }

   /* Cadenas de caracteres */
   \" { string.setLength(0); yybegin(STRING); }

   /* Espacios en blanco */
   {WhiteSpace} {}
}

<STRING> {
   \" { yybegin(YYINITIAL); return new Token(Type.VALUE, string.toString(),yyline,yycolumn);}

   [^\n\r\"\\]+ { string.append( yytext() ); }

   \\t { string.append('\t'); }

   \\n { string.append('\n'); }

   \\r { string.append('\r'); }

   \\\" { string.append('\"'); }

   \\ { string.append('\\'); }
}

/* error fallback */
[^] {
   //throw new Error("Illegal character <"+ yytext()+">");
}