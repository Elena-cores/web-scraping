package es.ceu.gisi.modcomp.webcrawler.jflex.lexico;

/**
 * Tipo de token que puede devolver el analizador léxico HTML que hemos
 * generado. Representa los diferentes tipos de unidades léxicas.
 *
 * @author Sergio Saugar García <sergio.saugargarcia@ceu.es>
 */
public enum Type {
    OPEN, CLOSE, EQUAL, WORD, SLASH, VALUE
}
