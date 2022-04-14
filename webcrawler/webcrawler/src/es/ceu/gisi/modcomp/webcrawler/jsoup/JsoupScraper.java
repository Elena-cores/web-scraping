package es.ceu.gisi.modcomp.webcrawler.jsoup;

import es.ceu.gisi.modcomp.webcrawler.scraper.WebScraper;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Esta clase encapsula toda la lógica de interacción con el analizador Jsoup.
 *
 * NO está permitido modificar la signatura de NINGÚN MÉTODO DE ESTA CLASE.
 *
 * @author Sergio Saugar García <sergio.saugargarcia@ceu.es>
 */
public class JsoupScraper implements WebScraper {

    private final Document webPage;

    /**
     * Este constructor crea un analizador para la página web que indica la URL.
     *
     * @param url La URL real de un documento HTML público, por ejemplo:
     * (https://www.amazon.es/).
     */
    public JsoupScraper(URL url) throws IOException {
        /**
         * Deberá crear un objeto Jsoup a partir de la URL que está
         * parametrizada.
         *
         * Para ello, lea atentamente el manual que se le ha proporcionado en el
         * enunciado de la práctica y vea los diferentes ejemplos que hay.
         */

        // De momento, el documento se inicializa a null para que el proyecto compile.
        webPage = null;
    }

    /**
     * Este constructor crea un analizador a partir del contenido de un fichero
     * HTML.
     *
     * Este constructor no lo debe utilizar en su práctica. Este constructor
     * sólo se ha generado para que vea un uso de la clase Jsoup y para
     * construir los tests de ejemplo que se le proporciona.
     *
     * @param file Un fichero que contiene un documento HTML completo.
     */
    public JsoupScraper(File file) throws IOException {
        webPage = Jsoup.parse(file, "UTF-8");
    }

    /**
     * Método que devuelve todas las URLs que se encuentran en las etiquetas de
     * hiperenlace de un documento HTML (etiquetas A).
     *
     * @return Una lista con todas las URLs de los hiperenlaces de un documento
     * HTML
     */
    @Override
    public List<String> retrieveHyperlinksA() {

        List<String> resultado = null;

        /**
         * Deberá programar este método. El método devolverá todas las URLs que
         * contengan las etiquetas A que se encuentre en el documento HTML que
         * esté analizando. Recuerde que las URLs de la etiqueta A vienen
         * anotadas en un atributo denominado href.
         */
        return resultado;

    }

    /**
     * Método que devuelve todas las URLs que se encuentran en las etiquetas de
     * imagen de un documento HTML (etiquetas IMG).
     *
     * @return Una lista con todas las URLs de las imágenes de un documento HTML
     */
    @Override
    public List<String> retrieveHyperlinksIMG() {

        List<String> resultado = null;

        /**
         * Deberá programar este método. El método devolverá todas las URLs que
         * contengan las etiquetas IMG que se encuentre en el documento HTML que
         * esté analizando. Recuerde que las imágenes de un documento dentro de
         * la etiqueta IMG vienen anotadas en un atributo denominado src.
         */
        return resultado;
    }

    /**
     * Método que devuelve las estadísticas correspondientes a una etiqueta HTML
     * concreta.
     *
     * @param tag La etiqueta sobre la que se quieren estadísticas
     *
     * @return El número de etiquetas de ese tipo que hay en el documento HTML
     */
    public int tagUsage(String tag) {
        /**
         * Deberá programar este método. Las estadísticas simplemente serán el
         * número de etiquetas de ese tipo que se encuentran en el documento.
         */

        // De momento, devolvemos 0 para que el proyecto compile.
        return 0;
    }

    /**
     * Obtiene la URL de la primera imagen que contiene el documento HTML que
     * estamos analizando.
     *
     * @return El nombre (o ruta) de la primera imagen insertada en el documento
     * HTML.
     */
    public String retrieveImgSrc() {
        /**
         * Este es un método de ejemplo que deberá comprender. Lea atentamente
         * la documentación proporcionada en el enunciado para averiguar qué
         * hace este método. Es fundamental para las tareas que debe realizar.
         *
         * En el test que se proporciona puede ver un ejemplo de la ejecución de
         * este método sobre un fichero de ejemplo.
         */
        Element tagIMG = webPage.select("IMG").first();

        String imagen = tagIMG.attr("src");

        return imagen;
    }
}
