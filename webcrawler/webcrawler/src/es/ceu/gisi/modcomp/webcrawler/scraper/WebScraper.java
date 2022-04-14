package es.ceu.gisi.modcomp.webcrawler.scraper;

import java.util.List;

/**
 * Interfaz que define los métodos necesarios para cualquier software que
 * realice Web Scraping.
 *
 * @author Sergio Saugar <sergio.saugargarcia@ceu.s>
 */
public interface WebScraper {

    /**
     * Método que devuelve todas las URLs que se encuentran en las etiquetas de
     * hiperenlace de un documento HTML (etiquetas A).
     *
     * @return Una lista con todas las URLs de los hiperenlaces de un documento
     * HTML
     */
    public List<String> retrieveHyperlinksA();

    /**
     * Método que devuelve todas las URLs que se encuentran en las etiquetas de
     * imagen de un documento HTML (etiquetas IMG).
     *
     * @return Una lista con todas las URLs de las imágenes de un documento HTML
     */
    public List<String> retrieveHyperlinksIMG();

}
