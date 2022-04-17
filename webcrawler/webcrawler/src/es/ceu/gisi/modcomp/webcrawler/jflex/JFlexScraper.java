package es.ceu.gisi.modcomp.webcrawler.jflex;

import es.ceu.gisi.modcomp.webcrawler.exceptions.WebCrawlerException;
import es.ceu.gisi.modcomp.webcrawler.jflex.lexico.*;
import es.ceu.gisi.modcomp.webcrawler.scraper.WebScraper;
import java.io.*;
import java.util.*;

/**
 * Esta clase encapsula toda la lógica de interacción con el analizador JFlex de
 * HTML proporcionado.
 *
 * NO ESTÁ PERMITIDO que modifique el código de esta clase ni las signaturas de
 * los métodos a no ser que esté indicado en los comentarios que se encuentran
 * en el código.
 *
 * @author Sergio Saugar García <sergio.saugargarcia@ceu.es>
 */
public class JFlexScraper implements WebScraper {

    /**
     * Instancia del analizador a utilizar.
     */
    private HTMLParser parser;

    /**
     * Atributo que almacenará las distintas URLs correspondientes a los enlaces
     * de etiquetas A.
     */
    private final List<String> urlsA = new ArrayList<String>();

    /**
     * Atributo que almacenará las distintas URLs correspondientes a las
     * imágenes que se encuentran en las etiquetas IMG.
     */
    private final List<String> urlsIMG = new ArrayList<String>();

    /**
     * Pila de etiquetas. Una pila es una estructura de datos que se ajusta a la
     * política LAST-IN-FIRST-OUT, la misma política que siguen las etiquetas
     * HTML cuando se cierran (primero se cierra la última que se abrió).
     */
    private final Stack<String> tagStack = new Stack<String>();

    /**
     * Atributo que determinará si el documento analizado se encontraba bien
     * balanceado o no.
     */
    private boolean tagsBalanced = true;

    /**
     * Constructor de la clase JFlexScraper. Para construir esta clase es
     * necesario parametrizarla con un fichero HTML que se encuentre almacenado
     * en el disco. En el código de los tests encontrará ejemplos que le
     * mostrarán una manera sencilla de almacenar y hacer referencia a ficheros
     * que se encuentran dentro de la estructura del proyecto.
     *
     * @param fichero El fichero que contiene la página HTML a analizar.
     * @throws FileNotFoundException Excepción lanzada si el fichero no existe.
     */
    public JFlexScraper(File fichero) throws FileNotFoundException, IOException, WebCrawlerException {
        try {
            // Reader para la lectura del fichero que se pasa por parámetro.
            Reader reader = new BufferedReader(new FileReader(fichero));

            /**
             * Se crea una instancia que analizará el fichero HTML
             * parametrizado.
             */
            parser = new HTMLParser(reader);

            /**
             * A partir de aquí, escriba su código.
             *
             * Este código deberá ir consumiendo, uno a uno, todos los tokens
             * del fichero hasta que el fin de fichero.
             *
             * Diseñe un autómata que vaya transitando en función del token que
             * encuentre y del estado en que se encuentra. De esa manera,
             * reconozca estructuras como la apertura o cierre de una etiqueta.
             *
             * En ciertas etiquetas, como cuando reconozca la apertura de una
             * etiqueta A (o una IMG), deberá "recordarlo" para asegurarse de
             * que deberá encontrar el atributo href (o src), para almacenar su
             * valor en el atributo correspondiente. Para "recordar" esa
             * información extra utilice variables.
             *
             * Para llevar la cuenta de las etiquetas que se abre y se cierran,
             * y si lo hacen en el orden adecuado, utilice el atributo tagStack.
             * Use también el atributo tagsBalanced. Determine en qué estados de
             * autómata debe añadir etiquetas y en qué estados eliminarlas.
             *
             * Para la implementación siga, obligatoriamente, la estructura
             * propuesta.
             *
             */
            
            // Estado inicial del autómata.
            int state = 0;

            Token token = parser.next();
            
            boolean etiquetaA = false;
            boolean etiquetaIMG = false;
            boolean valorEsHREF = false;
            boolean valorEsSRC = false;
            
            while (token != null) {

                switch (state) {
                    case 0:
                        /**
                         * Estamos en el estado 0 y acabamos de leer un token.
                         * Si recibo un token OPEN quiere decir que es el inicio
                         * de una nueva etiqueta HTML y, por lo tanto, deberé
                         * comenzar a reconocerla (en el siguiente estado).
                         *
                         * En caso contrario, sigo quedándome en el estado 0
                         * hasta encontrar el inicio de una etiqueta HTML.
                         * Podría ser que estuviese leyendo texto.
                         *
                         * En cada estado sólo puedo mirar el tipo y el valor
                         * del estado que estoy procesando en ese momento.
                         */
                        switch (token.getType()) {
                            case OPEN:
                                /**
                                 * Este token nos hace transitar y el siguiente
                                 * le trataremos en el estado 1.
                                 */
                                state = 1;
                                break;

                            default:
                            // No interesa.
                        } // Continúe implementando su autómata....
                    case 1:
                        //Estado cuando se lee un OPEN
                        if(token.getType()==Type.WORD) {
                            state = 2;
                            //notar que se abre una etiqueta
                            tagStack.push(token.getValue().toLowerCase());
                            if(token.getValue().equalsIgnoreCase("a")) {
                                etiquetaA = true;
                            }   else if(token.getValue().equalsIgnoreCase("img")) {
                                etiquetaIMG = true; 
                            }
                        }   else if(token.getType()==Type.SLASH) {
                            state = 6;
                        }
                        break;
                    case 2:
                        //Se debe leer att=valor o fin de etiqueta
                        if(token.getType() == Type.WORD) {
                            state = 3;
                            if(etiquetaA) {
                                if(token.getValue().equalsIgnoreCase("href")){
                                    valorEsHREF = true;
                                 }
                            }   else if(etiquetaIMG) {
                                    if(token.getValue().equalsIgnoreCase("src")) {
                                         valorEsSRC = true;
                                     }
                            }
                        }  else if(token.getType()==Type.SLASH) {
                            state = 5; //revise later 
                            if(token.getValue().equalsIgnoreCase(tagStack.peek())) {
                                tagStack.pop(); //si coincide con lo que tengo en la cima de la pila. Elemento se quita
                                } else {  
                                    tagsBalanced = false;
                                }
                        }  else if(token.getType()==Type.CLOSE) {
                            state = 0;
                        } 
                        break;
                    case 3: 
                        //Se espera un =
                            if(token.getType()==Type.EQUAL) {
                            state = 4;
                            }
                        break;
                    case 4: 
                        //esto es un valor de un atributo. Se guarda url dependiendo del atributo del token
                        if(token.getType()==Type.VALUE) {
                            if(valorEsHREF) {
                                 urlsA.add(token.getValue());
                            }
                            if(valorEsSRC) {
                                urlsIMG.add(token.getValue());
                            }
                        }
                        break;
                    case 5: 
                        //Se espera un >
                        if(token.getType()==Type.CLOSE) {
                            state = 0; //se vuelve al estado inicial
                            }
                        break;
                    case 6: 
                        //Estado donde quitamos etiqueta de la pila
                        //Se compara la palabra del token con la cima de la pila. Si coincide lo quitaremos
                        if(token.getType()==Type.WORD) {
                            if(token.getValue().equalsIgnoreCase(tagStack.peek())) {
                                tagStack.pop(); //si coincide con lo que tengo en la cima de la pila
                                } else {  
                                    tagsBalanced = false;
                                }
                        }
                        break; 
                        
                    default:
                        throw new WebCrawlerException("Error en la asignación de estado, el programa termina.");
                }

                /**
                 * Una vez finalizado el procesamiento de un token, solicito el
                 * siguiente que trataré en la nueva pasada del bucle while.
                 */
                token = parser.next();
            }

        } catch (WebCrawlerException wce) {
            System.out.println(wce.toString());
        }
    }

    /**
     * Método que devuelve todas las URLs que se encuentran en las etiquetas de
     * imagen de un documento HTML (etiquetas IMG).
     *
     * @return Una lista con todas las URLs de las imágenes de un documento HTML
     */
    @Override
    public List<String> retrieveHyperlinksA() {
        // NO puede modificar la implementación de este método.
        return this.urlsA;
    }

    /**
     * Método que devuelve todas las URLs que se encuentran en las etiquetas de
     * hiperenlace de un documento HTML (etiquetas A).
     *
     * @return Una lista con todas las URLs de los hiperenlaces de un documento
     * HTML
     */
    @Override
    public List<String> retrieveHyperlinksIMG() {
        // NO puede modificar la implementación de este método.
        return this.urlsIMG;
    }

    /**
     * Método que devuelve si el documento se encontraba bien balanceado.
     *
     * @return Cierto si el documento estaba bien balanceado, falso en caso
     * contrario.
     */
    public boolean esDocumentoHTMLBienBalanceado() {
        // NO puede modificar la implementación de este método.
        return this.tagsBalanced;
    }
}
