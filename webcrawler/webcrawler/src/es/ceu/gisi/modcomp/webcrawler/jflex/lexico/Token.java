package es.ceu.gisi.modcomp.webcrawler.jflex.lexico;

/**
 * Clase que almacena la información de los tokens devueltos por el analizador
 * léxico.
 *
 * @author Sergio Saugar García <sergio.saugargarcia@ceu.es>
 */
public class Token {

    /**
     * Tipo de token.
     */
    private final Type type;
    /**
     * Cadena de texto que ha provocado la generación del token o valor asociado
     * al token.
     */
    private final String value;

    /**
     * Número de línea donde se encuentra el token.
     */
    private final int line_number;

    /**
     * Número de la columna donde se encuentra el token.
     */
    private final int column_number;

    /**
     * Constructor de token.
     *
     * @param type Tipo establecido para el nuevo token
     * @param value Cadena leída como valor del nuevo token
     */
    public Token(Type type, String value, int line_number, int column_number) {
        this.type = type;
        this.value = value;
        this.line_number = line_number;
        this.column_number = column_number;
    }

    /**
     * Método que devuelve de qué tipo es el token.
     *
     * @return Tipo del token
     */
    public Type getType() {
        return type;
    }

    /**
     * Método que devuelve la cadena de caracteres que produjo la generación del
     * token.
     *
     * @return El valor del token
     */
    public String getValue() {
        return value;
    }

    /**
     * Método que devuelve una representación en String del token actual
     *
     * @return Representación textual del token
     */
    @Override
    public String toString() {
        return "Token(" + this.type + ", at: [l:" + this.line_number + ", c:" + this.column_number + "], \"" + this.value + "\")";
    }
}
