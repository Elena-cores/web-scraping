package es.ceu.gisi.modcomp.webcrawler.tests.htmlparser;

import es.ceu.gisi.modcomp.webcrawler.jflex.*;
import es.ceu.gisi.modcomp.webcrawler.jflex.lexico.*;
import es.ceu.gisi.modcomp.webcrawler.testfiles.TestFiles;
import java.io.*;
import java.util.logging.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Clase que testea y MUESTRA EL USO DEL ANALIZADOR LÉXICO con JFlex. El estudio
 * de esta clase es esencial para que el alumno vea cómo se utiliza el
 * analizador léxico y cuáles son las unidades léxicas que proporciona.
 *
 * @author Sergio Saugar García <sergio.saugargarcia@ceu.es>
 */
public class HTMLParserTest {

    /**
     * Readers de los ficheros.
     */
    private Reader reader1;
    private Reader reader2;

    /**
     * Analizador JFlex para HTML que el alumno debe utilizar en su programa
     * JFLexScraper.
     */
    HTMLParser analyzer;

    /**
     * Se va a crear un analizador léxico, a partir de uno de los ficheros de
     * prueba.
     */
    public HTMLParserTest() {
        try {
            /**
             * Se inician los readers con los dos ficheros que vamos a utilizar
             * a lo largo de los tests.
             */
            reader1 = new BufferedReader(new FileReader(TestFiles.TEST_FILE1));
            reader2 = new BufferedReader(new FileReader(TestFiles.TEST_FILE2));

            /**
             * Se inicializa el analizador JFlex con el primer fichero.
             */
            analyzer = new HTMLParser(reader1);

        } catch (FileNotFoundException fnfe) {
            System.out.println("No se pudo abrir alguno de los ficheros");
            fnfe.printStackTrace(System.out);
            throw new RuntimeException();
        }
    }

    /**
     * Este test comprueba que el analizador léxico reconoce los tres primeros
     * tokens de un fichero HTML y que corresponden con "<HTML>".
     */
    @Test
    public void compruebaEtiquetaInicioHTML() {
        // Recordemos que el analizador se ha inicializado en el constructor
        // para tratar el primer fichero de prueba "prueba1.html".
        try {
            /**
             * El método next() sirve para solicitar al analizador el siguiente
             * token a tratar. En este caso, como estamos comenzando a procesar
             * el fichero, se corresponderá con el primer token que se encuentre
             * en el fichero. Y lo primero que se encuentra es un "<". Por lo
             * tanto, es un token de tipo OPEN.
             */
            Token token1 = analyzer.next();
            /**
             * Si pedimos otro token, deberá corresponder con la siguiente
             * unidad léxica que tenga el fichero que está utilizando el
             * analizador.
             */
            Token token2 = analyzer.next();
            /**
             * Esto debería devolver el tercer token del fichero de prueba que
             * estamos analizando.
             */
            Token token3 = analyzer.next();

            /**
             * Con assertEquals se establece que el tests es válido si dos
             * elementos tienen el mismo valor. Por ejemplo, como sabemos cuál
             * es el valor para cada uno de los tokens (ya que sabemos que
             * estamos analizando el fichero "prueba1.html" y conocemos los
             * tokens que devuelve el analizador porque nos hemos leído el
             * enunciado de la práctica), sabemos qué tokens nos debería haber
             * devuelto el analizador y en qué orden.
             *
             * Por ejemplo, los tokens que devuelve el analizador pertenecen a
             * la clase Token y, como hemos explicado en el enunciado, tienen un
             * tipo (que es un valor válido del enumerado Tipo). Con el método
             * getTipo(), podemos acceder al tipo de ese token.
             */
            assertEquals(token1.getType(), Type.OPEN);
            /**
             * Si queremos obtener el valor de un token, es decir, la cadena de
             * caracteres que ha propiciado su generación, podemos utilizar el
             * método getValor() de la clase Token. Ese método nos devolverá un
             * String. Recordemos que si queremos comparar Strings sin tener en
             * cuenta si se encuentran en mayúscula o minúscula, ya que en una
             * web las etiquetas podrían estar escritas de ambas formas,
             * simplemente lo podemos pasar, por ejemplo, a minúsculas antes de
             * realizar la comparación.
             */
            assertEquals(token2.getValue().toLowerCase(), "html");
            assertEquals(token3.getType(), Type.CLOSE);
        } catch (IOException ex) {
            Logger.getLogger(HTMLParserTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * El test comprueba que el analizador léxico reconoce los tres primeros
     * tokens de un fichero HTML y que corresponden con "<HTML>".
     */
    @Test
    public void compruebaInicioYFinEtiquetaHTML() {
        try {

            /**
             * Si en algún momento queremos volver al inicio del fichero que
             * estamos analizando o, si queremos utilizar el analizador con otro
             * fichero, podemos utilizar el método yyreset que nos proporciona
             * la clase HTMLParser.
             */
            analyzer.yyreset(reader1);
            /**
             * La línea anterior reinicia el analizador y vuelve al inicio del
             * fichero. En estas líneas se muestra cómo se puede comprobar el
             * inicio de una etiqueta HTML, que se denota como: <NOMBREETIQUETA>
             */
            Token token1 = analyzer.next();
            Token token2 = analyzer.next();
            Token token3 = analyzer.next();
            assertEquals(token1.getType(), Type.OPEN);
            assertEquals(token2.getValue().toLowerCase(), "html");
            assertEquals(token3.getType(), Type.CLOSE);

            /**
             * En estas líneas se muestra cómo se puede comprobar el inicio de
             * una etiqueta HTML, que se denota como: </NOMBREETIQUETA>
             */
            Token token4 = analyzer.next();
            Token token5 = analyzer.next();
            Token token6 = analyzer.next();
            Token token7 = analyzer.next();
            assertEquals(token4.getType(), Type.OPEN);
            assertEquals(token5.getType(), Type.SLASH);
            assertEquals(token6.getValue().toLowerCase(), "html");
            assertEquals(token7.getType(), Type.CLOSE);
        } catch (IOException ex) {
            Logger.getLogger(HTMLParserTest.class.getName()).log(Level.SEVERE, null, ex);
            assertTrue(false);
        }
    }

    /**
     * Este tests muestra cómo se puede procesar el fichero analizado
     * (solicitando tokens hasta cierto momento o hasta que la función next()
     * devuelve null, que significa que el fichero a llegado a su fin).
     *
     * Además, demuestra cómo se puede comprobar que estamos ante una etiqueta
     * de "autocierre": etiquetas que no marcan el inicio/fin de un contenido
     * sino que producen una acción en el navegador. Por ejemplo, la etiqueta
     * <br/>, que introduce una nueva línea en el texto que muestra el
     * navegador.
     */
    @Test
    public void compruebaInicioYFinEtiquetaBR() {
        try {
            // Para este tests inicializamos el analizador con el segundo fichero
            // de pruebas "prueba2.html".
            analyzer.yyreset(reader2);
            //Una etiqueta BR tiene la forma: <BR /> (incluye inicio y fin de etiqueta)
            boolean encontradoBR = false;
            while (!encontradoBR) {
                /**
                 * Si vamos pidiendo los tokens dentro de un bucle while, iremos
                 * consumiendo uno a uno todos los tokens del fichero, hasta que
                 * nosotros queramos. En este caso, hasta que encontremos la
                 * etiqueta BR completa.
                 */
                Token token1 = analyzer.next();
                while (token1.getType() == Type.OPEN) {
                    /**
                     * Si el token devuelto es de tipo OPEN podría ser el inicio
                     * de una etiqueta HTML.
                     */
                    Token token2 = analyzer.next();
                    /**
                     * Si el siguiente token a un OPEN es otro token de tipo
                     * palabra y su contenido es br, entonces estamos ante una
                     * etiqueta BR.
                     */
                    if (token2.getType() == Type.WORD
                            && token2.getValue().toLowerCase().equals("br")) {
                        /**
                         * Si es un token palabra con un contenido BR, es la
                         * etiqueta que nos interesa.
                         */
                        encontradoBR = true;
                        Token token4 = analyzer.next();
                        Token token5 = analyzer.next();
                        /**
                         * Como era una etiqueta BR, si está bien escrita
                         * debería llevar un /> antes del final. Por lo tanto,
                         * los siguientes tokens que hemos leído deberían ser
                         * esos.
                         */
                        assertEquals(token4.getType(), Type.SLASH);
                        assertEquals(token5.getType(), Type.CLOSE);
                        break;
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(HTMLParserTest.class.getName()).log(Level.SEVERE, null, ex);
            assertTrue(false);
        }
    }
}
