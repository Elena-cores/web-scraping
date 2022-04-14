package es.ceu.gisi.modcomp.webcrawler.tests.crosstests;

import es.ceu.gisi.modcomp.webcrawler.exceptions.WebCrawlerException;
import es.ceu.gisi.modcomp.webcrawler.jflex.JFlexScraper;
import es.ceu.gisi.modcomp.webcrawler.jsoup.JsoupScraper;
import es.ceu.gisi.modcomp.webcrawler.testfiles.TestFiles;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Clase que testea el correcto funcionamiento de la implementación JFlexScraper
 * haciendo uso de la clase JsoupScraper.
 *
 * El objetivo de estos tests es comprobar si la implementación del alumno en la
 * realización de su clase JFlexScraper es igual de buena que la implementación
 * de la clase JsoupScraper, basada en una librería profesional.
 *
 * @author Sergio Saugar García <sergio.saugargarcia@ceu.es>
 */
public class CrossTest {

    /**
     * Analizador de tipo JsoupScraper
     */
    private final JsoupScraper parserJsoup;
    /**
     * Analizador de tipo JFlexScraper
     */
    private final JFlexScraper parserJFlex;

    /**
     * Este constructor nos crea dos analizadores a partir del mismo fichero de
     * prueba.
     *
     * @throws java.io.IOException Si ocurre algún error de entrada/salida al
     * crear el analizador.
     */
    public CrossTest() throws IOException, FileNotFoundException, WebCrawlerException {
        parserJsoup = new JsoupScraper(TestFiles.TEST_FILE6);
        parserJFlex = new JFlexScraper(TestFiles.TEST_FILE6);
    }

    /**
     * Este test comprueba que la implementación de JFlex ha obtenido, para las
     * etiquetas A, el mismo número y las mismas URLs que la implementación
     * basada en Jsoup.
     */
    @Test
    public void comprobacionDeteccionMismasEtiquetasA() {
        List<String> urlsJFlex = parserJFlex.retrieveHyperlinksA();
        List<String> urlsJsoup = parserJsoup.retrieveHyperlinksA();

        cleanEmptyStrings(urlsJsoup);
        cleanEmptyStrings(urlsJFlex);

        //Comprobamos mismo número
        assertEquals(urlsJFlex.size(), urlsJsoup.size());

        //Comprobamos que son las mismas:
        for (String url : urlsJsoup) {
            assertTrue(urlsJFlex.contains(url));
        }
    }

    /**
     * Este test comprueba que la implementación de JFlex ha obtenido, para las
     * etiquetas A, el mismo número y las mismas URLs que la implementación
     * basada en Jsoup.
     */
    @Test
    public void comprobacionDeteccionMismasEtiquetasIMG() {
        List<String> urlsJFlex = parserJFlex.retrieveHyperlinksIMG();
        List<String> urlsJsoup = parserJsoup.retrieveHyperlinksIMG();

        cleanEmptyStrings(urlsJsoup);
        cleanEmptyStrings(urlsJFlex);

        //Comprobamos mismo número
        assertEquals(urlsJFlex.size(), urlsJsoup.size());

        //Comprobamos que son las mismas:
        for (String url : urlsJsoup) {
            assertTrue(urlsJFlex.contains(url));
        }
    }

    private void cleanEmptyStrings(List<String> input) {
        List<String> output = input;
        while (output.contains("")) {
            output.remove("");
        }
    }

}
