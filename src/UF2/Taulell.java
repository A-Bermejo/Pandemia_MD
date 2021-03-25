package UF2;

import java.util.Scanner;

public class Taulell {
    /**
     * @author Daniel Lopez & Morel Luque
     *
     */
    private Scanner scan = new Scanner(System.in);
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
     * Una vegada demanem les files y les columnes a la classe "main" les assignem al taulell perquè tingui les dimensions que volem
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
    public void createTaulell(int option) {
        switch (option) {
            case 1: //Creació de taulell buit amb mesures especificades per l'usuari
                Interficie.mostrarMissatge("Diga'm les mesures del taulell en números. Primer les files i després les columnes: ");
                setFiles(scan.nextInt());
                setColumnes(scan.nextInt());
                setTaulell(new float[getFiles()][getColumnes()]);
                break;
            case 2: //Creació d'un taulell aleatori
                setFiles((int) (Math.random() * 9 + 2));
                setColumnes((int) (Math.random() * 9 + 2));
                setTaulell(new float[getFiles()][getColumnes()]);
                Interficie.mostrarMissatge("Es crearà un taulell amb les següents dimensions (x:" + getFiles() + " y:" + getColumnes() + ")");
                for (int i = 0; i < getFiles(); i++) {
                    for (int j = 0; j < getColumnes(); j++) {
                        getTaulell()[i][j] = (int) (Math.random() * 10);
                    }
                }
                break;
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
