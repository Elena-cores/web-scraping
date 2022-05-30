package es.ceu.gisi.modcomp.webcrawler.tests.jsoup;

import es.ceu.gisi.modcomp.webcrawler.jsoup.JsoupScraper;
import es.ceu.gisi.modcomp.webcrawler.testfiles.TestFiles;
import java.io.IOException;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Clase que testea el correcto funcionamiento de la clase JsoupScraper.
 *
 * El estudio de esta clase es esencial para que el alumno vea cómo se utiliza
 * la librería JSoup y cuáles son los principales métodos que proporciona.
 * También para que entienda cómo son los tests que se espera que realice.
 *
 * El alumno deberá generar tantos tests como crea necesarios para demostrar que
 * su clase funciona correctamente. Tests que cubran las distintas URLs
 * encontradas para las etiquetas A e IMG y tests que demuestren que las
 * estadísticas que está obteniendo de la página son correctas.
 *
 * Deberá incluir Un test por cada caso que desee comprobar. Se proporciona un
 * test de ejemplo, al final de esta clase, que comprueba si las estadísticas de
 * una etiqueta son correctas.
 *
 * @author Sergio Saugar García <sergio.saugargarcia@ceu.es>
 */
public class JsoupScraperTest {

    /**
     * Analizador de tipo JsoupScraper
     */
    private final JsoupScraper parser;
    private final JsoupScraper parseri;

    /**
     * Este constructor nos crea un analizador JSoup a partir del String de
     * prueba que se define en esta clase.
     *
     * @throws java.io.IOException Si ocurre algún error de entrada/salida al
     * crear el analizador.
     */
    public JsoupScraperTest() throws IOException {

        /**
         * El alumno deberá modificar esta línea y crear una instancia de su
         * analizador, pero utilizando la URL de la página que desee utilizar
         * como pruebas.
         *
         * Para las pruebas intermedias puede utilizar un fichero en disco, tal
         * y como se muestra en este ejemplo.
         *
         * El resto de los tests deberá adaptarlos para que verifiquen que el
         * software implementado funciona correctamente.
         */
        parser = new JsoupScraper(TestFiles.TEST_FILE3);
        parseri = new JsoupScraper(TestFiles.TEST_FILE2);

    }

    /**
     * Este test muestra cómo se pueden utilizar los métodos que el usuario
     * defina en la clase JsoupScraper de forma que pueda testear que el código
     * funciona correctamente, recuperando la información adecuada.
     *
     * En este caso, se testea que, utilizando el HTML codificado en el ejemplo,
     * la URL de la imagen que se obtiene es: brushedsteel.jpg. Que es,
     * exáctamente, el valor de la única imagen que existe en esa página.
     */
    @Test
     /**
     * Comprueba si el documento HTML actual tiene exactamente 1 etiqueta "br".
     */
    public void estadisticasEtiquetaTest() {
        int countOfBrTag = parseri.tagUsage("br");
        assertEquals(countOfBrTag, 1);
    }
    @Test
    /**
     * Comprueba si el documento HTML actual tiene exactamente 1 HyperLink en la etiqueta A con valor "http://www.bbc.co.uk".
    */
    
    public void getHyperlinksTest() {
        List<String> hyperLinks  = parseri.retrieveHyperlinksA();
        String hyperLink = hyperLinks.get(0);
        assertEquals(hyperLink, "http://www.bbc.co.uk");
    }
    
    /**
     * Comprueba si el documento HTML actual tiene exactamente 1 Hiperenlace de Imagen en la etiqueta IMG con valor "brushedsteel.jpg".
    */
    @Test
    public void getHyperlinksImagesTest() {
        List<String> hyperLinksImg = parseri.retrieveHyperlinksIMG();
        String hyperlinkImg = hyperLinksImg.get(0);
        assertEquals(hyperlinkImg, "brushedsteel.jpg");
    }
    @Test
    public void recuperaNombrePrimeraImagenEnPrueba3() {
        assertEquals(parser.retrieveImgSrc(),
                "https://www.uspceu.com/Portals/_default/skins/uspceu-multi/assets/img/logos/logo-ceu-horizontal-positivo.png");
    }

    /**
     * Test de ejemplo que comprobaría que el número de etiquetas A que se
     * encuentran en el documento de prueba es 2.
     */
    @Test
    public void mostrarUsoDeEtiquetasAEnPrueba3() {
        assertEquals(parser.tagUsage("A"), 2);
    }

    /**
     * Test de ejemplo que comprobaría que el número de etiquetas IMG que se
     * encuentran en el documento de prueba es 1.
     */
    @Test
    public void mostrarUsoDeEtiquetasIMGEnPrueba3() {
        assertEquals(parser.tagUsage("IMG"), 1);
    }

    /**
     * Test de ejemplo que comprobaría que el número de URLs extraídas de
     * etiquetas A es correcta para el documento de prueba.
     */
    @Test
    public void URLsEtiquetasAExtraidasCorrectamente() {
        assertEquals(parser.retrieveHyperlinksA().size(), 2);
    }

    /**
     * Test de ejemplo que comprobaría que el número de URLs extraídas de
     * etiquetas IMG es correcta para el documento de prueba.
     */
    @Test
    public void URLsEtiquetasIMGExtraidasCorrectamente() {
        assertEquals(parser.retrieveHyperlinksIMG().size(), 1);
    }
}
