package es.ceu.gisi.modcomp.webcrawler.exceptions;

import es.ceu.gisi.modcomp.webcrawler.jflex.lexico.Token;

/**
 *
 * @author Sergio Saugar <sergio.saugargarcia@ceu.s>
 */
public class WebCrawlerException extends Exception {

    private Token token;
    private int state;

    public WebCrawlerException(String s) {
        this(s, null, -1);
    }

    public WebCrawlerException(String s, Token token, int state) {
        super(s);
        this.state = state;
        this.token = token;
    }

    public String toString() {
        String output = super.toString() + "\n";
        output += "--> In state: " + this.state + ", when processing: " + token + "\n\n";
        return output;
    }

}
