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

    public int getInvalidPosition() {
        return INVALIDPOSITION;
    }

    /**
     * El número de malalts que s'introdueix.
     */
    private int patients;

    public int getPatients() {
        return patients;
    }

    public void setPatients(int patients) {
        this.patients = patients;
    }

    /**
     * El total de malalts que s'han curat pel taulell.
     */
    private int totalCured;

    public int getTotalCured() {
        return totalCured;
    }

    public void setTotalCured(int totalCured) {
        this.totalCured = totalCured;
    }

    /**
     * El total de malalts pel taulell.
     */
    private float totalPatients;

    public float getTotalPatients() {
        return totalPatients;
    }

    public void setTotalPatients(float totalPatients) {
        this.totalPatients = totalPatients;
    }

    public void sumTotalPatients(int totalPatients) {
        this.totalPatients += totalPatients;
    }

    /**
     * El total de malalts que s'han saltat el confinamen
     */
    private int totalDisplaced;

    public int getTotalDisplaced() {
        return totalDisplaced;
    }

    public void setTotalDisplaced(int totalDisplaced) {
        this.totalDisplaced = totalDisplaced;
    }

    /**
     * El malalts actuals del taulell
     */
    private float currentPatients;

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

    public void setCurrentPatients(float currentPatients) {
        this.currentPatients = currentPatients;
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

    public float getCasella(int i, int j) {
        return taulell[i][j];
    }

    public void sumCasella(int i, int j, float value) {
        this.taulell[i][j] += value;
    }

    public void subtractCasella(int i, int j, float value) {
        this.taulell[i][j] -= value;
    }

    /**
     * Funció per crear el taulell buit amb unes mesures introduïdes per l'usuari
     *
     * @param countBlockedPositions Número aleatori de caselles bloquejadas al taulell
     */
    public void createTaulellBuit(int countBlockedPositions,int x,int y) {
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
    public void createTaulellRand(int countBlockedPositions,int x,int y) {
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

    public void createTaulellFitxer(String sentence){
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

    public void createInvalidPosition(int numPositionRand) {
        for (int i = 0; i < numPositionRand; i++) {
            int x = (int) (Math.random() * files);
            int y = (int) (Math.random() * columnes);
            taulell[x][y] = INVALIDPOSITION;
        }
    }

    public void resetVariables(){
        totalCured = 0;
        totalPatients = 0;
        totalDisplaced = 0;
        currentPatients = 0;
    }

    /**
     * Funció que actualitza els malalts actuals del taulell
     *
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

    public void updateTotalPatients() {
        if (getCurrentPatients() > getTotalPatients()){
            sumTotalPatients((int)(getCurrentPatients() - getTotalPatients()));
        }
    }

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
    
    public void curarMalaltsPercentatgeVC(int cureNumber,int x, int y){
        if (taulell[x][y] != INVALIDPOSITION) {
            totalCured += Math.ceil(getCasella(x, y) * cureNumber / 100);
            taulell[x][y] -= (taulell[x][y] * cureNumber / 100);
        }
    }

    //COMENTAR DUPLICATE CODE PARA CREAR FUNCION O NO
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

    public void curarMalaltsValorConcret(int cureNumber,int x, int y) {
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
    
    public void desplacarMalalts(boolean lockedPosition, String answerDisplace,int x,int y){
        switch (answerDisplace) {
            case "q" -> {
                lockedPosition = Utils.validarCasellaDesti(Taulell.this,x - 1, y - 1, "No pots desplaçar els malalts a una posició bloquejada o fora del taulell");
                if (lockedPosition) {
                    taulell[x-1][y-1] += patients;
                    totalDisplaced += patients;
                }
            }
            case "w" -> {
                lockedPosition = Utils.validarCasellaDesti(Taulell.this, x - 1, y, "No pots desplaçar els malalts a una posició bloquejada o fora del taulell");
                if (lockedPosition) {
                    taulell[x-1][y] += patients;
                    totalDisplaced += patients;
                }
            }
            case "e" -> {
                lockedPosition = Utils.validarCasellaDesti(Taulell.this, x - 1, y + 1, "No pots desplaçar els malalts a una posició bloquejada o fora del taulell");
                if (lockedPosition) {
                    taulell[x-1][y+1] += patients;
                    totalDisplaced += patients;
                }
            }
            case "a" -> {
                lockedPosition = Utils.validarCasellaDesti(Taulell.this, x, y - 1, "No pots desplaçar els malalts a una posició bloquejada o fora del taulell");
                if (lockedPosition) {
                    taulell[x][y-1] += patients;
                    totalDisplaced += patients;
                }
            }
            case "d" -> {
                lockedPosition = Utils.validarCasellaDesti(Taulell.this, x, y + 1, "No pots desplaçar els malalts a una posició bloquejada o fora del taulell");
                if (lockedPosition) {
                    taulell[x][y+1] += patients;
                    totalDisplaced += patients;
                }
            }
            case "z" -> {
                lockedPosition = Utils.validarCasellaDesti(Taulell.this, x + 1, y - 1, "No pots desplaçar els malalts a una posició bloquejada o fora del taulell");
                if (lockedPosition) {
                    taulell[x+1][y-1] += patients;
                    totalDisplaced += patients;
                }
            }
            case "x" -> {
                lockedPosition = Utils.validarCasellaDesti(Taulell.this, x + 1, y, "No pots desplaçar els malalts a una posició bloquejada o fora del taulell");
                if (lockedPosition) {
                    taulell[x+1][y] += patients;
                    totalDisplaced += patients;

                }
            }
            case "c" -> {
                lockedPosition = Utils.validarCasellaDesti(Taulell.this, x + 1, y + 1, "No pots desplaçar els malalts a una posició bloquejada o fora del taulell");
                if (lockedPosition) {
                    taulell[x+1][y+1] += patients;
                    totalDisplaced += patients;
                }
            }
        }
        if (!lockedPosition) {
            taulell[x][y] += patients;
        }
    }

    public String taulellToString() {
        String dadesTaulell = "";
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < columnes; j++) {
                dadesTaulell += taulell[i][j] + " ";
            }
        }
        return dadesTaulell;
    }

    public void llegirFitxer() {
        try {
            File origin = new File("res/Taulells.txt");
            Scanner reader = new Scanner(origin);
            reader.nextLine();
            setCurrentPatients(Float.parseFloat(reader.next()));
            setTotalPatients(Float.parseFloat(reader.next()));
            setTotalCured(Integer.parseInt(reader.next()));
            setFiles(Integer.parseInt(reader.next()));
            setColumnes(Integer.parseInt(reader.next()));
            reader.nextLine();
            createTaulellFitxer(reader.nextLine());
            reader.close();
        } catch (Exception e) {
            Interficie.mostrarMissatge(e.getMessage());
        }
    }
}