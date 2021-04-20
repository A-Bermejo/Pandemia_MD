package UF3;

/**
 * Classe on es crea i es modifica l'estructura del Taulell.
 *
 * @author Daniel Lopez
 * @author Morel Luque
 *
 */

public class Taulell {

    private int files;
    private int columnes;
    private float[][] taulell;
    private static final int INVALIDPOSITION = -1;

    /**
     * Inicialitzem el taulell a 0 per files i columnes.
     *
     */
    public Taulell() {
        this.files = 0;
        this.columnes = 0;
        this.taulell = new float[files][columnes];
    }

    /**
     * Una vegada demanem les files y les columnes les assignem al taulell perquè tingui les dimensions que volem.
     *
     * @param f Files.
     * @param c Columnes.
     */
    public Taulell(int f, int c) {
        this.files = f;
        this.columnes = c;
        this.taulell = new float[files][columnes];
    }
    public int getInvalidPosition() {
        return INVALIDPOSITION;
    }
    /**
     * Funció per obtenir les files del nostre taulell.
     *
     * @return Número de files.
     */
    public int getFiles() {
        return files;
    }

    /**
     * Funció per modificar les files del nostre taulell passant-li un nou valor.
     *
     * @param files Nou valor de files
     */
    public void setFiles(int files) {
        this.files = files;
    }

    /**
     * Funció per obtenir les columnes del nostre taulell.
     *
     * @return Número de files.
     */
    public int getColumnes() {
        return columnes;
    }

    /**
     * Funció per modificar les columnes del nostre taulell passant-li un nou valor.
     *
     * @param columnes Nou valor de columnes.
     */
    public void setColumnes(int columnes) {
        this.columnes = columnes;
    }

    /**
     * Funció per obtenir el nostre taulell.
     *
     * @return Ens retorna el taulell com una array bidimensional de tipus float.
     */
    public float[][] getTaulell() {
        return taulell;
    }
    public float getCasella(int i, int j) {
        return taulell[i][j];
    }

    public void setCasella(int i, int j, float value) {
        this.taulell[i][j] = value;
    }


    /**
     * Funció per crear el taulell ja sigui de forma manual o de forma aleatoria.
     * @param option És la opció que introdueix l'usuari segons com
     * vulgui crear el taulell
     */
    public void createTaulellBuit( int numPositionRand) {
        this.taulell = new float[files][columnes];
        createInvalidPosition(numPositionRand);
    }
    public void createTaulellRand(int numPositionRand) {
        this.taulell = new float[files][columnes];
        for (int i = 0; i < getFiles(); i++) {
            for (int j = 0; j < getColumnes(); j++) {
                getTaulell()[i][j] = (int) (Math.random() * 10);
            }
        }
        createInvalidPosition(numPositionRand);
    }
    public void createInvalidPosition(int numPositionRand) {
        for (int i = 0; i < numPositionRand; i++) {
            setFiles((int) (Math.random() * getFiles()));
            setColumnes((int) (Math.random() * getColumnes()));
            setCasella(files,columnes,INVALIDPOSITION);
        }
    }
}