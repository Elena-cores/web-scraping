package es.ceu.gisi.modcomp.webcrawler.testfiles;

import java.io.File;

/**
 * Clase que encapsula la localización de los diferentes ficheros de prueba
 * dentro del proyecto de programación.
 *
 * Esta clase puede ser utilizada por el alumno para hacer referencia a los
 * ficheros de prueba que deberá introducir.
 *
 * @author Sergio Saugar <sergio.saugargarcia@ceu.s>
 */
public abstract class TestFiles {

    /**
     * Ruta que accede a los ficheros que se almacenen en el directorio
     * testfiles.
     */
    public static final String TESTFILE_PATH = new java.io.File("").getAbsolutePath()
            + "/test/es/ceu/gisi/modcomp/webcrawler/testfiles/";

    /**
     * Ruta del fichero de prueba 0.
     */
    public static final File TEST_FILE0 = new File(TESTFILE_PATH + "prueba0.html");

    /**
     * Ruta del primer fichero de prueba.
     */
    public static final File TEST_FILE1 = new File(TESTFILE_PATH + "prueba1.html");

    /**
     * Ruta del segundo fichero de prueba.
     */
    public static final File TEST_FILE2 = new File(TESTFILE_PATH + "prueba2.html");

    /**
     * Ruta del tercer fichero de prueba.
     */
    public static final File TEST_FILE3 = new File(TESTFILE_PATH + "prueba3.html");

    /**
     * Ruta del cuarto fichero de prueba.
     */
    public static final File TEST_FILE4 = new File(TESTFILE_PATH + "prueba4.html");

    /**
     * Ruta del quinto fichero de prueba.
     */
    public static final File TEST_FILE5 = new File(TESTFILE_PATH + "prueba5.html");

    /**
     * Ruta del sexto fichero de prueba.
     */
    public static final File TEST_FILE6 = new File(TESTFILE_PATH + "prueba6.html");

}
