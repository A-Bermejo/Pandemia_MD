package UF2;

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

    /**
     * Funció per modificar el taulell i assignar-li nous valors.
     *
     * @param taulell Passem una array bidimensional de tipus float.
     */
    public void setTaulell(float[][] taulell) {
        this.taulell = taulell;
    }

    /**
     * Funció per crear el taulell ja sigui de forma manual o de forma aleatoria.
     * @param option És la opció que introdueix l'usuari segons com
     * vulgui crear el taulell
     */
    public void createTaulell(int option) {
        switch (option) {
            //Creació de taulell buit amb mesures especificades per l'usuari
            case 1 -> {
                Interficie.mostrarMissatge("Diga'm les files (X:) que té el taulell: ");
                setFiles(Utils.validarEnter("No has introduït un número de X: correcte. Introdueix com a mínim 2", "No has introduït un caràcter númeric vàlid. Torna a provar.", 0, 2));
                Interficie.mostrarMissatge("Diga'm les columnes (Y:) que té el taulell: ");
                setColumnes(Utils.validarEnter("No has introduït un número de Y: correcte. Introdueix com a mínim 2", "No has introduït un caràcter númeric vàlid. Torna a provar.", 0, 2));
                setTaulell(new float[getFiles()][getColumnes()]);
            }
            //Creació d'un taulell aleatori
            case 2 -> {
                setFiles((int) (Math.random() * 9 + 2));
                setColumnes((int) (Math.random() * 9 + 2));
                setTaulell(new float[getFiles()][getColumnes()]);
                Interficie.mostrarMissatge("Es crearà un taulell amb les següents dimensions (x:" + getFiles() + " y:" + getColumnes() + ")");
                for (int i = 0; i < getFiles(); i++) {
                    for (int j = 0; j < getColumnes(); j++) {
                        getTaulell()[i][j] = (int) (Math.random() * 10);
                    }
                }
            }
        }
        int numPositionRand = (int) (Math.random() * 4);
        for (int i = 0; i < numPositionRand; i++) {
            int x = (int) (Math.random() * getFiles());
            int y = (int) (Math.random() * getColumnes());
            getTaulell()[x][y] = GestorTaulell.INVALIDPOSITION;
        }
        Interficie.mostrarMissatge("Total de posicions bloquejades: " + numPositionRand);
    }
}
