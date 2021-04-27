package UF3;

/**
 * Classe on es crea i es modifica l'estructura del Taulell.
 *
 * @author Daniel Lopez
 * @author Morel Luque
 */

public class Taulell {

    private int files;
    private int columnes;
    private float[][] taulell;
    private static final int INVALIDPOSITION = -1;
    /**
     * El número de malalts que s'introdueix.
     */
    private int patients;
    /**
     * El total de malalts que s'han curat pel taulell.
     */
    private int totalCured;
    /**
     * El total de malalts pel taulell.
     */
    private float totalPatients;
    /**
     * El total de malalts que s'han saltat el confinamen
     */
    private int totalDisplaced;

    /**
     * El malalts actuals del taulell
     */
    private float currentPatients;

    public float getCurrentPatients() {
        setCurrentPatients(0);
        for (int i = 0; i < getFiles(); i++) {
            for (int j = 0; j < getColumnes(); j++) {
                if (getCasella(i, j) != getInvalidPosition()) {
                    sumCurrentPatients((int)(getCasella(i,j)));
                }
            }
        }
        return currentPatients;
    }

    public void setCurrentPatients(int currentPatients) {
        this.currentPatients = currentPatients;
    }

    public void sumCurrentPatients(int currentPatients) {
        this.currentPatients += currentPatients;
    }

    public int getPatients() {
        return patients;
    }

    public void setPatients(int patients) {
        this.patients = patients;
    }

    public int getTotalCured() {
        return totalCured;
    }

    public void setTotalCured(int totalCured) {
        this.totalCured = totalCured;
    }

    public float getTotalPatients() {
        return totalPatients;
    }

    public void setTotalPatients(int totalPatients) {
        this.totalPatients = totalPatients;
    }

    public void sumTotalPatients(int totalPatients) {
        this.totalPatients += totalPatients;
    }

    public int getTotalDisplaced() {
        return totalDisplaced;
    }

    public void setTotalDisplaced(int totalDisplaced) {
        this.totalDisplaced = totalDisplaced;
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

    public float getCasella(int i, int j) {
        return taulell[i][j];
    }

    public void setCasella(int i, int j, float value) {
        this.taulell[i][j] = value;
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
        setFiles(x);
        setColumnes(y);
        this.taulell = new float[files][columnes];
        createInvalidPosition(countBlockedPositions);
    }

    /**
     * Funció per crear el taulell amb mesures i valors aleatoris
     *
     * @param countBlockedPositions Número aleatori de caselles bloquejadas al taulell
     */
    public void createTaulellRand(int countBlockedPositions,int x,int y) {
        setFiles(x);
        setColumnes(y);
        this.taulell = new float[files][columnes];
        for (int i = 0; i < getFiles(); i++) {
            for (int j = 0; j < getColumnes(); j++) {
                setCasella(i, j, (int) (Math.random() * 10));
            }
        }
        createInvalidPosition(countBlockedPositions);
    }

    public void createInvalidPosition(int numPositionRand) {
        for (int i = 0; i < numPositionRand; i++) {
            int x = (int) (Math.random() * getFiles());
            int y = (int) (Math.random() * getColumnes());
            setCasella(x, y, INVALIDPOSITION);
        }
    }

    public void resetVariables(){
        setTotalCured(0);
        setTotalPatients(0);
        setTotalDisplaced(0);
        setCurrentPatients(0);
    }

    /**
     * Funció que actualitza els malalts actuals del taulell
     *
     */
    public void startTotalPatients() {
        setTotalPatients(0);
        for (int i = 0; i < getFiles(); i++) {
            for (int j = 0; j < getColumnes(); j++) {
                if (getCasella(i, j) != getInvalidPosition()) {
                    setTotalPatients((int)(getTotalPatients() + getCasella(i, j)));
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
        for (int i = 0; i < getFiles(); i++) {
            for (int j = 0; j < getColumnes(); j++) {
                if (getCasella(i, j) != getInvalidPosition()) {
                    sumCasella(i, j, (getCasella(i, j) * infectionRate));
                }
            }
        }
        updateTotalPatients();
    }

    public void curarMalaltsPercentatgeGlobal(int cureNumber) {
        for (int i = 0; i < getFiles(); i++) {
            for (int j = 0; j < getColumnes(); j++) {
                if (getCasella(i, j) != getInvalidPosition()) {
                    setTotalCured((int) (getTotalCured() + Math.ceil(getCasella(i, j) * cureNumber / 100)));
                    subtractCasella(i, j, (getCasella(i, j) * cureNumber / 100));
                }
            }
        }
    }
    
    public void curarMalaltsPercentatgeVC(int cureNumber,int x, int y){
        if (getCasella(x,y) != getInvalidPosition()) {
            setTotalCured((int) (getTotalCured() + Math.ceil(getCasella(x, y) * cureNumber / 100)));
            subtractCasella(x, y, (getCasella(x, y) * cureNumber / 100));
        }
    }

    //COMENTAR DUPLICATE CODE PARA CREAR FUNCION O NO
    public void curarMalaltsValorConcretGlobal(int cureNumber) {
        for (int i = 0; i < getFiles(); i++) {
            for (int j = 0; j < getColumnes(); j++) {
                if (getCasella(i,j) != getInvalidPosition()) {
                    if ((getCasella(i,j) - cureNumber) < 0) {
                        setTotalCured((int) (getTotalCured() + (getCasella(i, j) - cureNumber) + cureNumber));
                        setCasella(i, j, 0);
                    } else {
                        subtractCasella(i,j, cureNumber);
                        setTotalCured(getTotalCured() + cureNumber);
                    }
                }
            }
        }
    }

    public void curarMalaltsValorConcret(int cureNumber,int x, int y) {
        if (getCasella(x,y) != getInvalidPosition()) {
            if ((getCasella(x,y) - cureNumber) < 0) {
                setTotalCured((int) (getTotalCured() + (getCasella(x,y) - cureNumber) + cureNumber));
                setCasella(x,y, 0);
            } else {
                subtractCasella(x,y, cureNumber);
                setTotalCured(getTotalCured() + cureNumber);
            }
        }
    }
    
    public void desplacarMalalts(boolean lockedPosition, String answerDisplace,int x,int y){
        /*
        switch (answerDisplace) {
            case "q" -> {
                lockedPosition = Utils.validarCasellaDesti(t,x - 1, y - 1, "No pots desplaçar els malalts a una posició bloquejada o fora del taulell");
                if (lockedPosition) {
                    sumCasella(x - 1, y - 1, getPatients());
                    setTotalDisplaced(getTotalDisplaced() + getPatients());
                }
            }
            case "w" -> {
                lockedPosition = Utils.validarCasellaDesti(t, x - 1, y, "No pots desplaçar els malalts a una posició bloquejada o fora del taulell");
                if (lockedPosition) {
                    sumCasella(x - 1, y, getPatients());
                    setTotalDisplaced(getTotalDisplaced() + getPatients());
                }
            }
            case "e" -> {
                lockedPosition = Utils.validarCasellaDesti(t, x - 1, y + 1, "No pots desplaçar els malalts a una posició bloquejada o fora del taulell");
                if (lockedPosition) {
                    sumCasella(x - 1, y + 1, getPatients());
                    setTotalDisplaced(getTotalDisplaced() + getPatients());
                }
            }
            case "a" -> {
                lockedPosition = Utils.validarCasellaDesti(t, x, y - 1, "No pots desplaçar els malalts a una posició bloquejada o fora del taulell");
                if (lockedPosition) {
                    sumCasella(x, y - 1, getPatients());
                    setTotalDisplaced(getTotalDisplaced() + getPatients());
                }
            }
            case "d" -> {
                lockedPosition = Utils.validarCasellaDesti(t, x, y + 1, "No pots desplaçar els malalts a una posició bloquejada o fora del taulell");
                if (lockedPosition) {
                    sumCasella(x, y + 1, getPatients());
                    setTotalDisplaced(getTotalDisplaced() + getPatients());
                }
            }
            case "z" -> {
                lockedPosition = Utils.validarCasellaDesti(t, x + 1, y - 1, "No pots desplaçar els malalts a una posició bloquejada o fora del taulell");
                if (lockedPosition) {
                    sumCasella(x + 1, y - 1, getPatients());
                    setTotalDisplaced(getTotalDisplaced() + getPatients());
                }
            }
            case "x" -> {
                lockedPosition = Utils.validarCasellaDesti(t, x + 1, y, "No pots desplaçar els malalts a una posició bloquejada o fora del taulell");
                if (lockedPosition) {
                    sumCasella(x + 1, y, getPatients());
                    setTotalDisplaced(getTotalDisplaced() + getPatients());
                }
            }
            case "c" -> {
                lockedPosition = Utils.validarCasellaDesti(t, x + 1, y + 1, "No pots desplaçar els malalts a una posició bloquejada o fora del taulell");
                if (lockedPosition) {
                    sumCasella(x + 1, y + 1, getPatients());
                    setTotalDisplaced(getTotalDisplaced() + getPatients());
                }
            }
        }
        if (!lockedPosition) {
            sumCasella(x, y, getPatients());
        }
         */
    }

    public String taulellToString() {
        String taulell = "";
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < columnes; j++) {
                taulell += getCasella(i,j) + " ";
            }
        }
        return taulell;
    }


}