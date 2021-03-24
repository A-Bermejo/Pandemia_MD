package UF2;

public class Taulell {
    /**
     * @author Daniel Lopez & Morel Luque
     *
     */

    private int files;
    private int columnes;
    private float[][] taulell;

    /**
     * Inicialitzem el taulell a 0 per files i columnes
     *
     */
    public Taulell() {
        this.files = 0;
        this.columnes = 0;
        this.taulell = new float[files][columnes];
    }

    /**
     * Una vegada demanem les files y les columnes a la classe "main" les assignem al taulles perquè tingui les dimensions que volem
     *
     * @param f Files
     * @param c Columnes
     */
    public Taulell(int f, int c) {
        this.files = f;
        this.columnes = c;
        this.taulell = new float[files][columnes];
    }

    /**
     * Funció per obtenir les files del nostre taulell
     *
     * @return Número de files
     */
    public int getFiles() {
        return files;
    }

    /**
     * Funció per modificar les files del nostre taulell passant-li un nou valor
     *
     */
    public void setFiles(int files) {
        this.files = files;
    }

    /**
     * Funció per obtenir les columnes del nostre taulell
     *
     * @return Número de files
     */
    public int getColumnes() {
        return columnes;
    }

    /**
     * Funció per modificar les columnes del nostre taulell passant-li un nou valor
     *
     */
    public void setColumnes(int columnes) {
        this.columnes = columnes;
    }

    /**
     * Funció per obtenir el nostre taulell
     *
     */
    public float[][] getTaulell() {
        return taulell;
    }

    /**
     * Funció per modificar el taulell i assignar-li nous valors utilitzats en altres classes
     *
     */
    public void setTaulell(float[][] taulell) {
        this.taulell = taulell;
    }
}
