package es.ceu.gisi.modcomp.webcrawler.app;

import es.ceu.gisi.modcomp.webcrawler.exceptions.WebCrawlerException;
import es.ceu.gisi.modcomp.webcrawler.jflex.JFlexScraper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Esta aplicación contiene el programa principal que ejecuta ambas partes del
 * proyecto de programación de la asignatura Modelos de Computación.
 *
 * @author Sergio Saugar García <sergio.saugargarcia@ceu.es>
 */
public class WebCrawler {

    public static void main(String[] args) throws IOException, FileNotFoundException, WebCrawlerException {
        /**
         * En este método main, debajo de este comentario, escribirá las líneas
         * necesarias para mostrar el funcionamiento de las clases JFlexScraper
         * y JsoupScraper que ha creado en la práctica. Primero se centrará en
         * la parte correspondiente a JFlex.
         *
         * En primer lugar, deberá inicializar su clase JFlexScraper con un
         * fichero HTML REAL (y complejo) que haya almacenado en disco. Para la
         * lectura del fichero se recomienda almacenarlo en el mismo directorio
         * en que se encuentran los ficheros de prueba y que haga uso del mismo
         * código que ilustra cómo se cargan los ficheros de pruebas en la clase
         * de test.
         *
         * Posteriormente debe analizar el fichero y, el resultado que le
         * devuelvan los métodos que ha programado en la clase JFlexScraper
         * (retrieveHyperlinksA() y retrieveHyperlinksIMG()), deberá almacenarlo
         * en un fichero.
         *
         * También deberá mostrar un mensaje en pantalla indicando si el fichero
         * HTML que se ha pasado está bien balanceado.
         */
        //System.out.println("\n\n** JFLEXSCRAPER **\n\n");
        
        // Si el archivo está presente en el proyecto, sólo indique el nombre, de lo contrario proporcione la ruta completa.
        String htmlFilePath = "eleconomista.html";
        
        String TESTFILE_PATH = new java.io.File("").getAbsolutePath()
            + "/test/es/ceu/gisi/modcomp/webcrawler/testfiles/";
        
        //Inicializar el objeto htmlFile desde la ruta
        File htmlFile = new File(TESTFILE_PATH + htmlFilePath);
        
        // Debe inicializar JFlexScraper con el archivo HTML a analizar
         JFlexScraper scrapper = new JFlexScraper(htmlFile);
         
        List<String> hyperLinks =  scrapper.retrieveHyperlinksA();
        List<String> imgLinks =  scrapper.retrieveHyperlinksIMG();
        
        // Creará un archivo con todos los hipervínculos que encuentre.
        WriteLinksToFile("linksFile.txt", hyperLinks, imgLinks); 
        
        // También se debe indicar, mediante un mensaje en la pantalla que
       // el archivo HTML pasado está bien balanceado.
        boolean isDocBalanced = scrapper.esDocumentoHTMLBienBalanceado();
        if (isDocBalanced)
            JOptionPane.showMessageDialog(null, "HTML Document is balanced");
        else
            JOptionPane.showMessageDialog(null, "HTML Document is not balanced");
        
        
        
        /**
         * En segundo lugar, debajo de este comentario, pasará a demostrar el
         * uso de la clase JsoupScraper que ha programado. Para ello, escribirá
         * las líneas de código necesarias para crear un objeto de tipo
         * JsoupScraper con la DIRECCIÓN HTTP de una página web REAL que desee
         * analizar.
         *
         * Al igual que hizo en el apartado anterior, creará un fichero con
         * todos los hiperenlaces que encuentre en la página web (utilizando el
         * resultado que le proporcionen los métodos retrieveHyperlinksA() y
         * retrieveHyperlinksIMG() que ha programado.
         *
         * Además, mostrará por pantalla las estadísticas de uso de las
         * etiquetas HTML más comunes: a, img, br, div, li, ul, p, span, table,
         * td, tr. Para ello, utilice los resultados que le proporcione el
         * método tagUsage() que ha programado.
         */
        System.out.println("\n\n** JSOUPSCRAPER **\n\n");

    }
    
    /**
     * Escribir los datos tanto del List<String> al archivo, con el filePath dado
     *
     * @param filePath Ruta del archivo junto con el nombre y el tipo.
     * @param hyperLinks Lista de hiperenlaces de la etiqueta A.
     * @param imgLinks Lista de hiperenlaces de la etiqueta IMG.
     */

    private static void WriteLinksToFile(String filePath, List<String> hyperLinks, List<String> imgLinks) throws FileNotFoundException {
         PrintWriter out = new PrintWriter(filePath);
          AddToFile("Hyperlinks:", hyperLinks, out);
          AddToFile("HyperlinkImages:", imgLinks, out);
    }
    
    /**
     * Método que añade datos de y List<String> a una instancia de PrintWriter.
     * Cada elemento de datos en una línea diferente.
     * Limpia la instancia de PrintWriter una vez que los datos son añadidos a ella.
     *
     * @param type Descripción del tipo de datos en List<String>.
     * @param linksList Lista de datos.
     * @param out Instancia de PrintWriter a la que añadir los datos.
     */

    private static void AddToFile(String type, List<String> linksList, PrintWriter out) {
        for(String s: linksList) {
            out.println(s);
        }
    }

}
