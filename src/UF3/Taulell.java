package UF3;

import java.io.File;
import java.util.Scanner;

/**
 * Classe on es crea i es modifica l'estructura del Taulell.
 *
 * @author Daniel Lopez
 * @author Morel Luque
 */

public class Taulell {

    private int files;
    private int columnes;

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

    private float[][] taulell;
    private static final int INVALIDPOSITION = -1;

    /**
     * Funció per obtenir INVALIDPOSITION
     * @return INVALIDPOSITION (posició bloquejada)
     */
    public int getInvalidPosition() {
        return INVALIDPOSITION;
    }

    /**
     * El número de malalts que s'introdueix.
     */
    private int patients;

    /**
     * Funció per obtenir els malalts
     * @return malalts
     */
    public int getPatients() {
        return patients;
    }

    /**
     * Funció per assignar un nou valor de malalts
     * @param patients el nou valor de malalts
     */
    public void setPatients(int patients) {
        this.patients = patients;
    }

    /**
     * El total de malalts que s'han curat pel taulell.
     */
    private int totalCured;

    /**
     * Funció per obtenir el total de curats
     * @return el total de curats
     */
    public int getTotalCured() {
        return totalCured;
    }

    /**
     * Funció per assignar un nou valor al total de curats
     * @param totalCured el nou valor que tindrà el total de curats
     */
    public void setTotalCured(int totalCured) {
        this.totalCured = totalCured;
    }

    /**
     * El total de malalts pel taulell.
     */
    private float totalPatients;

    /**
     * Funció per obtenir el total de malalts
     * @return retorna el total de malalts
     */
    public float getTotalPatients() {
        return totalPatients;
    }

    /**
     * Funció per assignar un nou valor al total de malalts
     * @param totalPatients el un nou valor al total de malalts
     */
    public void setTotalPatients(float totalPatients) {
        this.totalPatients = totalPatients;
    }

    /**
     * Funció per incrementar el total de malalts
     * @param totalPatients l'increment que afegirem al total de malalts
     */
    public void sumTotalPatients(int totalPatients) {
        this.totalPatients += totalPatients;
    }

    /**
     * El total de malalts que s'han saltat el confinamen
     */
    private int totalDisplaced;

    /**
     * Funció per obtenir el total de malalts que s'han saltat el confinament
     * @return el total de persones que s'han desplaçat
     */
    public int getTotalDisplaced() {
        return totalDisplaced;
    }

    /**
     * El malalts actuals del taulell
     */
    private float currentPatients;

    /**
     * Funció per obtenir el total de malalts actuals
     * @return el total de malalts actuals
     */
    public float getCurrentPatients() {
        currentPatients = 0;
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < columnes; j++) {
                if (taulell[i][j] != INVALIDPOSITION) {
                    currentPatients += taulell[i][j];
                }
            }
        }
        return currentPatients;
    }

    /**
     * Funció per assignar un nou valor al total de malalts actuals
     * @param currentPatients el nou valor que tindrà els malalts actuals
     */
    public void setCurrentPatients(float currentPatients) {
        this.currentPatients = currentPatients;
    }

    private int positionPatients;

    /**
     * Funció per obtenir els malalts que s'assignaràn a una casella
     * @return malalts que s'assignaràn a una casella
     */
    public int getPositionPatients() {
        return positionPatients;
    }

    /**
     * Funció per assignar un nou valor als malalts que s'assignaràn a una casella
     * @param positionPatients nou valor
     */
    public void setPositionPatients(int positionPatients) {
        this.positionPatients = positionPatients;
    }

    /**
     * Inicialitzem el taulell a 0 per files i columnes.
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
     * Funció per obtenir una casella concreta
     * @param i fila
     * @param j columna
     * @return valor de casella
     */
    public float getCasella(int i, int j) {
        return taulell[i][j];
    }

    /**
     * Funció per restar al valor d'una casella concreta
     * @param i fila
     * @param j columna
     * @param value el valor que li restarem al valor de la casella
     */
    public void subtractCasella(int i, int j, float value) {
        this.taulell[i][j] -= value;
    }

    /**
     * Funció per crear el taulell buit amb unes mesures introduïdes per l'usuari
     *
     * @param countBlockedPositions Número aleatori de caselles bloquejadas al taulell
     */
    public void createTaulellBuit(int countBlockedPositions, int x, int y) {
        files = x;
        columnes = y;
        this.taulell = new float[files][columnes];
        createInvalidPosition(countBlockedPositions);
    }

    /**
     * Funció per crear el taulell amb mesures i valors aleatoris
     *
     * @param countBlockedPositions Número aleatori de caselles bloquejadas al taulell
     */
    public void createTaulellRand(int countBlockedPositions, int x, int y) {
        files = x;
        columnes = y;
        this.taulell = new float[files][columnes];
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < columnes; j++) {
                taulell[i][j] = (int) (Math.random() * 10);
            }
        }
        createInvalidPosition(countBlockedPositions);
    }

    /**
     * Funció per crear un taulell amb les dades d'un fitxer
     * @param sentence linia que conté les dades del taulell
     */
    public void createTaulellFitxer(String sentence) {
        String[] dades = sentence.split(" ");
        int position = 0;
        this.taulell = new float[files][columnes];
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < columnes; j++) {
                taulell[i][j] = Float.parseFloat(dades[position]);
                position++;
            }
        }
    }

    /**
     * Funció per assignar posicions bloquejades al taulell
     * @param numPositionRand el número de posicions bloquejades que tindrà el taulell
     */
    public void createInvalidPosition(int numPositionRand) {
        for (int i = 0; i < numPositionRand; i++) {
            int x = (int) (Math.random() * files);
            int y = (int) (Math.random() * columnes);
            taulell[x][y] = INVALIDPOSITION;
        }
    }

    /**
     * Funció per resetejar variables
     */
    public void resetVariables() {
        totalCured = 0;
        totalPatients = 0;
        totalDisplaced = 0;
        currentPatients = 0;
    }

    /**
     * Funció que recull els malalts totals al crear el taulell
     */
    public void startTotalPatients() {
        totalPatients = 0;
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < columnes; j++) {
                if (taulell[i][j] != INVALIDPOSITION) {
                    totalPatients += taulell[i][j];
                }
            }
        }
    }

    /**
     * Funció que actualitza els malalts totals
     */
    public void updateTotalPatients() {
        if (getCurrentPatients() > getTotalPatients()) {
            sumTotalPatients((int) (getCurrentPatients() - getTotalPatients()));
        }
    }

    /**
     * Funció que va per cada casella aplicant la taxa de contagi que li passem
     * @param infectionRate la taxa de contagi
     */
    public void transmitirVirus(float infectionRate) {
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < columnes; j++) {
                if (taulell[i][j] != INVALIDPOSITION) {
                    taulell[i][j] += (taulell[i][j] * infectionRate);
                }
            }
        }
        updateTotalPatients();
    }

    /**
     * Funció per curar malalts de forma global quan se li passa un percentatge
     * @param cureNumber el percentatge de cura
     */
    public void curarMalaltsPercentatgeGlobal(int cureNumber) {
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < columnes; j++) {
                if (taulell[i][j] != INVALIDPOSITION) {
                    totalCured += Math.ceil(taulell[i][j] * cureNumber / 100);
                    taulell[i][j] -= (taulell[i][j] * cureNumber / 100);
                }
            }
        }
    }

    /**
     * Funció per curar malalts a una casella concreta quan se li passa un percentatge
     * @param cureNumber el valor concret
     * @param x fila
     * @param y columna
     */
    public void curarMalaltsPercentatgeVC(int cureNumber, int x, int y) {
        if (taulell[x][y] != INVALIDPOSITION) {
            totalCured += Math.ceil(getCasella(x, y) * cureNumber / 100);
            taulell[x][y] -= (taulell[x][y] * cureNumber / 100);
        }
    }

    /**
     * Funció per curar malalts de forma global quan se li passa un valor concret
     * @param cureNumber el valor concret
     */
    public void curarMalaltsValorConcretGlobal(int cureNumber) {
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < columnes; j++) {
                if (taulell[i][j] != INVALIDPOSITION) {
                    if ((taulell[i][j] - cureNumber) < 0) {
                        totalCured += (taulell[i][j] - cureNumber) + cureNumber;
                        taulell[i][j] = 0;
                    } else {
                        taulell[i][j] -= cureNumber;
                        totalCured += cureNumber;
                    }
                }
            }
        }
    }

    /**
     * Funció per curar malalts a una casella concreta quan se li passa un valor concret
     * @param cureNumber el percentatge de cura
     * @param x fila
     * @param y columna
     */
    public void curarMalaltsValorConcret(int cureNumber, int x, int y) {
        if (taulell[x][y] != INVALIDPOSITION) {
            if ((taulell[x][y] - cureNumber) < 0) {
                totalCured += (taulell[x][y] - cureNumber) + cureNumber;
                taulell[x][y] = 0;
            } else {
                taulell[x][y] -= cureNumber;
                totalCured += cureNumber;
            }
        }
    }

    /**
     * Funció que ens permet validar la casella de destí quan desplaçem un malalt.
     *
     * @param x Representa la fila a on es vol desplaçar el malalt.
     * @param y Representa la columna a on es vol desplaçar el malalt.
     *          que introdueixi una posició que estigui fora del taulell o si està bloquejada.
     * @return Dona "true" o "false" depent si la comprovació que es fa sobre
     * si la posició a on es mou el malalt està fora del taulell, es una posició bloquejada o es una posició vàlida.
     */
    public boolean validarCasellaDesti(int x, int y) {
        return (x != INVALIDPOSITION && y != INVALIDPOSITION) && (x != files && y != columnes) && taulell[x][y] != INVALIDPOSITION;
    }

    /**
     * Funció per desplaçar els malalts segons la direcció que ens passi l'usuari mitjançant una lletra
     * @param lockedPosition boolean que ens comprova si és una posició bloquejada
     * @param answerDisplace resposta de l'usuari per saber a on vol moure els malalts
     * @param x fila
     * @param y columna
     */
    public void desplacarMalalts(boolean lockedPosition, String answerDisplace, int x, int y) {
        switch (answerDisplace) {
            case "q" -> {
                lockedPosition = validarCasellaDesti(x - 1, y - 1);
                if (lockedPosition) {
                    taulell[x - 1][y - 1] += patients;
                    totalDisplaced += patients;
                }
            }
            case "w" -> {
                lockedPosition = validarCasellaDesti(x - 1, y);
                if (lockedPosition) {
                    taulell[x - 1][y] += patients;
                    totalDisplaced += patients;
                }
            }
            case "e" -> {
                lockedPosition = validarCasellaDesti(x - 1, y + 1);
                if (lockedPosition) {
                    taulell[x - 1][y + 1] += patients;
                    totalDisplaced += patients;
                }
            }
            case "a" -> {
                lockedPosition = validarCasellaDesti(x, y - 1);
                if (lockedPosition) {
                    taulell[x][y - 1] += patients;
                    totalDisplaced += patients;
                }
            }
            case "d" -> {
                lockedPosition = validarCasellaDesti(x, y + 1);
                if (lockedPosition) {
                    taulell[x][y + 1] += patients;
                    totalDisplaced += patients;
                }
            }
            case "z" -> {
                lockedPosition = validarCasellaDesti(x + 1, y - 1);
                if (lockedPosition) {
                    taulell[x + 1][y - 1] += patients;
                    totalDisplaced += patients;
                }
            }
            case "x" -> {
                lockedPosition = validarCasellaDesti(x + 1, y);
                if (lockedPosition) {
                    taulell[x + 1][y] += patients;
                    totalDisplaced += patients;

                }
            }
            case "c" -> {
                lockedPosition = validarCasellaDesti(x + 1, y + 1);
                if (lockedPosition) {
                    taulell[x + 1][y + 1] += patients;
                    totalDisplaced += patients;
                }
            }
        }
        if (!lockedPosition) {
            taulell[x][y] += patients;
        }
    }

    /**
     * Funció per escriure el nostre taulell en un fitxer com si fos una String
     * @return dades del taulell passades a String
     */
    public String taulellToString() {
        String dadesTaulell = "";
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < columnes; j++) {
                dadesTaulell += taulell[i][j] + " ";
            }
        }
        return dadesTaulell;
    }

    /**
     * Funció que ens serveix per llegir un fitxer i obtenir un taulell amb les dades corresponents
     * @param nTaulell número de taulell que volem importar
     */
    public void llegirTaulell(int nTaulell) {
        try {
            File origin = new File("res/Taulells.txt");
            Scanner reader = new Scanner(origin);
            boolean taulellN = true;
            while (taulellN) {
                if (reader.nextInt() == nTaulell) {
                    taulellN = false;
                    reader.nextLine();
                    setCurrentPatients(Float.parseFloat(reader.next()));
                    setTotalPatients(Float.parseFloat(reader.next()));
                    setTotalCured(Integer.parseInt(reader.next()));
                    setFiles(Integer.parseInt(reader.next()));
                    setColumnes(Integer.parseInt(reader.next()));
                    reader.nextLine();
                    createTaulellFitxer(reader.nextLine());
                    reader.close();
                } else {
                    for (int i = 0; i < 4; i++) {
                        reader.nextLine();
                    }
                }
            }
            reader.close();
        } catch (Exception e) {
            Interficie.mostrarMissatge(e.getMessage());
        }
    }

    /**
     * Funció que introdueix els malalts a la casella corresponent i que retorna un int per mostrar un missatge
     * @param x fila
     * @param y columna
     * @param aux variable per comprovar que no s'excedeixi el límit de malalts especificats anteriorment
     * @return un int per mostrar diferents missatges a la classe GestorTaulell
     */
    public int introduirMalalts(int x, int y,int aux){
        if (taulell[x][y] != INVALIDPOSITION) {
            if (x <= files && y <= columnes) {
                positionPatients = Utils.validarEnter("Introdueix una quantitat de malalts no superior a la especificada anteriorment","Introdueix un caràcter numèric",0,0);
                if (positionPatients + aux <= patients) { // Es suma positionPatients + i per tenir en compte els malalts que ya s'han introduït.
                    taulell[x][y] += positionPatients;
                    return 0;
                } else {
                    return 1;
                }
            } else {
                return 2;
            }
        }
        return 3;
    }
}