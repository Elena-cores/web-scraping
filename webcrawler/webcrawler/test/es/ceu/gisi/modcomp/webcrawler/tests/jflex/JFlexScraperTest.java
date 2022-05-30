package es.ceu.gisi.modcomp.webcrawler.tests.jflex;

import es.ceu.gisi.modcomp.webcrawler.exceptions.WebCrawlerException;
import es.ceu.gisi.modcomp.webcrawler.jflex.JFlexScraper;
import es.ceu.gisi.modcomp.webcrawler.testfiles.TestFiles;
import java.io.FileNotFoundException;
import java.io.IOException;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Clase que testea y muestra el uso del analizador JFlexScraper programado
 *
 * El estudio de esta clase es esencial para que el alumno entienda cómo son los
 * tests que se espera que realice.
 *
 * El alumno deberá generar tantos tests como crea necesarios para demostrar que
 * su clase funciona correctamente. Tests que cubran las distintas URLs
 * encontradas para las etiquetas A e IMG y tests que demuestren que la página
 * que está analizando está bien balanceada (o no).
 *
 * Deberá incluir un test por cada caso que desee comprobar. Por ejemplo, los
 * tests que deberá hacer el alumno irán orientados a comprobar si el software
 * respeta correctamente la estructuras de las etiquetas. Por ejemplo, el alumno
 * podrá crear un fichero HTML de prueba donde las palabras:
 * href="http://www.ibm.com" estén fuera de una etiqueta <A> y, en su test,
 * demostrará que esa secuencia de palabras NO ES RECONOCIDA POR SU SOFTWARE, ya
 * que no se encuentra dentro de una etiqueta válida.
 *
 * @author Sergio Saugar <sergio.saugargarcia@ceu.s>
 */
public class JFlexScraperTest {

    /**
     * Si tuviera que inicializar algún atributo, etc... lo haría en este
     * constructor.
     */
    public JFlexScraperTest() {
        super();
    }

    /**
     * Este test demuestra que el procesamiento del fichero1 se realiza
     * correctamente.
     *
     * Esto significa que el analizador programado:
     *
     * - No Encuentra urls correspondientes a etiquetas A. - No Encuentra urls
     * correspondientes a etiquetas IMG.
     *
     * - Determina que el fichero está bien balanceado.
     *
     * @throws java.io.FileNotFoundException Si el fichero no existe
     */
    @Test
    public void htmlVacio() throws FileNotFoundException, IOException, WebCrawlerException {
        //Creo una instancia del analizador para el fichero que tengo que analizar
        JFlexScraper parser = new JFlexScraper(TestFiles.TEST_FILE1);

        // El tamaño de la lista de URLs A encontrados es 0
        assertEquals(parser.retrieveHyperlinksA().size(), 0);

        // El tamaño de la lista de URLs IMG encontrados es 0
        assertEquals(parser.retrieveHyperlinksIMG().size(), 0);

        //El documento está bien balanceado
        assertTrue(parser.esDocumentoHTMLBienBalanceado());
    }

    /**
     * Este test demuestra que el procesamiento del fichero2 se realiza
     * correctamente.
     *
     * Esto significa que el analizador programado:
     *
     * - Encuentra una única URL correspondiente a etiqueta A y es:
     * http://www.bbc.co.uk
     *
     * - Encuentra una única URL correspondiente a etiqueta IMG y es:
     * brushedsteel.jpg
     *
     * - Determina que el fichero está bien balanceado.
     *
     * @throws java.io.FileNotFoundException Si el fichero no existe
     */
    @Test
    public void analizaFicheroEjemplo_y_Correcto() throws FileNotFoundException, IOException, WebCrawlerException {
        //Creo una instancia del analizador para el fichero que tengo que analizar
        JFlexScraper parser = new JFlexScraper(TestFiles.TEST_FILE2);

        // El tamaño de la lista de URLs A encontrados es 1
        assertEquals(1, parser.retrieveHyperlinksA().size());
        // El único elemento de esa lista es: http://www.bbc.co.uk
        assertEquals(parser.retrieveHyperlinksA().get(0), "http://www.bbc.co.uk");

        // El tamaño de la lista de URLs IMG encontrados es 1
        assertEquals(parser.retrieveHyperlinksIMG().size(), 1);
        // El único elemento de esa lista es: brushedsteel.jpg
        assertEquals(parser.retrieveHyperlinksIMG().get(0), "brushedsteel.jpg");

        //El documento está bien balanceado
        assertTrue(parser.esDocumentoHTMLBienBalanceado());
    }

    /**
     * Este test demuestra que el procesamiento del fichero3 se realiza
     * correctamente.
     *
     * Recuerda que debes asegurarte de que encuentras el atributo href dentro
     * de una etiqueta A. También que las etiquetas pueden tener varios
     * atributos, href puede no ser el único ni el primero.
     *
     * Lo mismo debes asegurar para el atributo src de la etiqueta IMG.
     *
     * Esto significa que el analizador programado:
     *
     * - Encuentra dos URLs correspondiente a etiquetas A y son:
     * http://www.bbc.co.uk , http://www.ibm.com
     *
     * - Encuentra una única URL correspondiente a etiquetas IMG y es:
     * https://www.uspceu.com/Portals/_default/skins/uspceu-multi/assets/img/logos/logo-ceu-horizontal-positivo.png
     *
     * - Determina que el fichero está bien balanceado.
     *
     * @throws java.io.FileNotFoundException Si el fichero no existe
     */
    @Test
    public void tieneEnCuentaContextoEtiqueta_y_VariosAtributos() throws FileNotFoundException, IOException, WebCrawlerException {
        //Creo una instancia del analizador para el fichero que tengo que analizar
        JFlexScraper parser = new JFlexScraper(TestFiles.TEST_FILE3);

        // El tamaño de la lista de URLs A encontrados es 2
        assertEquals(parser.retrieveHyperlinksA().size(), 2);
        // El primer elemento de esa lista es: http://www.bbc.co.uk
        assertEquals(parser.retrieveHyperlinksA().get(0), "http://www.bbc.co.uk");
        // El segundo elemento de esa lista es: http://www.ibm.com
        assertEquals(parser.retrieveHyperlinksA().get(1), "http://www.ibm.com");

        // El tamaño de la lista de URLs IMG encontrados es 1
        assertEquals(parser.retrieveHyperlinksIMG().size(), 1);
        // El único elemento de esa lista es: https://www.uspceu.com/Portals/_default/skins/uspceu-multi/assets/img/logos/logo-ceu-horizontal-positivo.png
        assertEquals(parser.retrieveHyperlinksIMG().get(0), "https://www.uspceu.com/Portals/_default/skins/uspceu-multi/assets/img/logos/logo-ceu-horizontal-positivo.png");

        //El documento está bien balanceado
        assertTrue(parser.esDocumentoHTMLBienBalanceado());
    }

    /**
     * Este test demuestra que el procesamiento del fichero4 se realiza
     * correctamente.
     *
     * Esto significa que el analizador programado:
     *
     * - Encuentra una única URL correspondiente a etiqueta A y es:
     * http://www.bbc.co.uk
     *
     * - Encuentra una única URL correspondiente a etiqueta IMG y es:
     * brushedsteel.jpg
     *
     * - Determina que el fichero está MAL balanceado.
     *
     * @throws java.io.FileNotFoundException Si el fichero no existe
     */
    @Test
    public void ficheroCompleto_mal_balanceado_etiqueta_autocierre() throws FileNotFoundException, IOException, WebCrawlerException {
        //Creo una instancia del analizador para el fichero que tengo que analizar
        JFlexScraper parser = new JFlexScraper(TestFiles.TEST_FILE4);

        // El tamaño de la lista de URLs A encontrados es 1
        assertEquals(parser.retrieveHyperlinksA().size(), 1);
        // El único elemento de esa lista es: http://www.bbc.co.uk
        assertEquals(parser.retrieveHyperlinksA().get(0), "http://www.bbc.co.uk");

        // El tamaño de la lista de URLs IMG encontrados es 1
        assertEquals(parser.retrieveHyperlinksIMG().size(), 1);
        // El único elemento de esa lista es: brushedsteel.jpg
        assertEquals(parser.retrieveHyperlinksIMG().get(0), "brushedsteel.jpg");

        //El documento está MAL balanceado
        assertFalse(parser.esDocumentoHTMLBienBalanceado());
    }

    /**
     * Este test demuestra que el procesamiento del fichero5 se realiza
     * correctamente. Esto significa que el analizador programado:
     *
     * - Encuentra una única URL correspondiente a etiqueta A y es:
     * http://www.bbc.co.uk
     *
     * - Encuentra una única URL correspondiente a etiqueta IMG y es:
     * brushedsteel.jpg
     *
     * - Determina que el fichero está MAL balanceado.
     *
     * @throws java.io.FileNotFoundException Si el fichero no existe
     */
    @Test
    public void ficheroCompleto_mal_balanceado_etiqueta_normal() throws FileNotFoundException, IOException, WebCrawlerException {
        //Creo una instancia del analizador para el fichero que tengo que analizar
        JFlexScraper parser = new JFlexScraper(TestFiles.TEST_FILE5);

        // El tamaño de la lista de URLs A encontrados es 1
        assertEquals(parser.retrieveHyperlinksA().size(), 1);
        // El único elemento de esa lista es: http://www.bbc.co.uk
        assertEquals(parser.retrieveHyperlinksA().get(0), "http://www.bbc.co.uk");

        // El tamaño de la lista de URLs IMG encontrados es 1
        assertEquals(parser.retrieveHyperlinksIMG().size(), 1);
        // El único elemento de esa lista es: brushedsteel.jpg
        assertEquals(parser.retrieveHyperlinksIMG().get(0), "brushedsteel.jpg");

        //El documento está MAL balanceado
        assertFalse(parser.esDocumentoHTMLBienBalanceado());
    }
    @Test
     /**
     * Comprueba si el documento HTML actual tiene exactamente 1 etiqueta "br".
     */
    public void estadisticasEtiquetaTest() throws IOException, FileNotFoundException, WebCrawlerException {
        JFlexScraper parser = new JFlexScraper(TestFiles.TEST_FILE6);
        // El tamaño de la lista de URLs img encontrados es 14
         assertEquals(parser.retrieveHyperlinksIMG().size(), 14);
    }
     @Test
     /**
     * Comprueba si el documento HTML actual acepta un fichero vacío.
     */
    public void ficheroVacio() throws IOException, FileNotFoundException, WebCrawlerException {
        JFlexScraper parser = new JFlexScraper(TestFiles.TEST_FILE0);
        // Imprimir error
         assertEquals(parser.retrieveHyperlinksIMG().size(), 0);
         assertEquals(parser.retrieveHyperlinksIMG().size(), 0);
    }
    
    
}
