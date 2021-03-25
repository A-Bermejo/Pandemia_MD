package UF2;

import java.util.Scanner;

public class GestorTaulell {
    /**
     * @author Daniel Lopez & Morel Luque
     */
    static final int INVALIDPOSITION = -1;

    Taulell t = new Taulell();

    static Scanner scan = new Scanner(System.in);
    public static int patients;
    public static int totalCured;
    public static float totalPatients;
    public static int totalDisplaced;

    /**
     * Funció que serveix per carregar dades al taulell, tant si es vol crear un taulell aleatori com si es vol crear un amb mesures introduïdes per l'usuari,
     * desprès de demanar al principi de tot el primer taulell. És a dir, aquesta funció la utilitzem una vegada ja s'ha creat el primer taulell però es vol tornar a crear un altre al llarg del programa.
     *
     * @param t Es pasa el taulell de la classe "Taulell" perquè és on es guarda tota la informació del taulell que utilitzem.
     */
    public void carregarDades(Taulell t) {
        Interficie.mostrarMenu(1);
        int answerBoard = Utils.validarEnter("Introdueix un número de la llista", "No has introduït un caràcter númeric vàlid. Torna a provar.", 2, 1);
        totalCured = 0;
        totalPatients = 0;
        totalDisplaced = 0;
        t.createTaulell(answerBoard);
        Interficie.mostrarTaulell(t);
    }

    /**
     * Funció que ens permet introduïr malalts al nostre taulell i no només en una sola cel·la, sino que pels podem repartir en varies cel·les.
     *
     * @param t Es pasa el taulell de la classe "Taulell" perquè és on es guarda tota la informació del taulell que utilitzem.
     */
    public void introduirMalalts(Taulell t) {
        Interficie.mostrarMissatge("Quants malalts vols introduir: ");
        patients = Math.abs(scan.nextInt());
        int positionPatients = 0;
        Interficie.mostrarTaulell(t);
        int aux = 0;
        while (aux < patients) {
            Interficie.mostrarMissatge("A quina fila (X:) vols introduir el/s malalt/s: ");
            int x = Utils.validarEnter("Indica una posició per X: que estigui dins del taulell", "No has introduït un caràcter numèric", t.getFiles(), 1) - 1;
            Interficie.mostrarMissatge("A quina columna (Y:) vols introduir el/s malalt/s: ");
            int y = Utils.validarEnter("Indica una posició per Y: que estigui dins del taulell", "No has introduït un caràcter numèric", t.getColumnes(), 1) - 1;
            if (t.getTaulell()[x][y] != INVALIDPOSITION) {
                if (x <= t.getFiles() && y <= t.getColumnes()) {
                    Interficie.mostrarMissatge("Quants malalts hi ha en aquesta posició: ");
                    positionPatients = Math.abs(scan.nextInt());
                    if (positionPatients + aux <= patients) { // Es suma positionPatients + i per tenir en compte els malalts que ya s'han introduït.
                        t.getTaulell()[x][y] += positionPatients;
                    } else {
                        Interficie.mostrarMissatgeError("No pots especificar més malalts en una posicio que el total" +
                                " de malalts que vols introduir.");
                    }
                } else {
                    Interficie.mostrarMissatgeError("Especifica una columna i fila existents en el taulell.");
                }
            } else {
                Interficie.mostrarMissatgeError("No pots introduir malalts en una posició bloquejada");
            }
            aux += positionPatients;
        }
    }

    /**
     * Funció que ens permet trasnmetre virus aplicant una taxa de contagi.
     *
     * @param t Es pasa el taulell de la classe "Taulell" perquè és on es guarda tota la informació del taulell que utilitzem.
     */
    public void transmetreVirus(Taulell t) {
        Interficie.mostrarMissatge("Diga'm la taxa de contagi: ");
        float infectionRate = Math.abs(scan.nextFloat());
        for (int i = 0; i < t.getFiles(); i++) {
            for (int j = 0; j < t.getColumnes(); j++) {
                if (t.getTaulell()[i][j] != INVALIDPOSITION) {
                    t.getTaulell()[i][j] += t.getTaulell()[i][j] * infectionRate;
                }
            }
        }
    }

    /**
     * Funció que ens permet curar maltalts. Es pot fer de dues maneres: 1 - De forma global, 2 - A una casella completa. En ambdós casos es pot decidir si es vol curar amb % o amb un valor concret.
     *
     * @param t Es pasa el taulell de la classe "Taulell" perquè és on es guarda tota la informació del taulell que utilitzem.
     */
    public void curarMalalts(Taulell t) {
        Interficie.mostrarMenu(2);
        int answerCure = Utils.validarEnter("Introdueix un número de la llista", "No has introduït un caràcter numèric vàlid. Torna a provar.", 2, 1);
        int answerCureValue;
        switch (answerCure) {
            case 1: //Curar malalts de forma global
                Interficie.mostrarMenu(21);
                answerCureValue = Utils.validarEnter("Introdueix un número de la llista", "No has introduït un caràcter numèric vàlid. Torna a provar.", 2, 1);
                int cureNumber;
                switch (answerCureValue) {
                    case 1: //Curar malalts globalment introduïnt un percentatge
                        Interficie.mostrarMissatge("Quin percentatge de malalts vols curar (%): ");
                        cureNumber = Utils.validarEnter("Introdueix un valor entre 0 - 100", "No has introduït un valor numèric", 100, 0);
                        for (int i = 0; i < t.getFiles(); i++) {
                            for (int j = 0; j < t.getColumnes(); j++) {
                                if (t.getTaulell()[i][j] != INVALIDPOSITION) {
                                    totalCured += Math.ceil(t.getTaulell()[i][j] * cureNumber / 100);
                                    t.getTaulell()[i][j] -= t.getTaulell()[i][j] * cureNumber / 100;
                                }
                            }
                        }
                        break;
                    case 2: //Curar malalts globalment introduïnt un valor concret
                        Interficie.mostrarMissatge("Quants malalts vols curar (valor concret): ");
                        cureNumber = scan.nextInt();
                        for (int i = 0; i < t.getFiles(); i++) {
                            for (int j = 0; j < t.getColumnes(); j++) {
                                curarMalaltsValorConcret(t, cureNumber, i, j);
                            }
                        }
                        break;
                }
                break;
            case 2: //Curar malalts de forma individual
                Interficie.mostrarTaulell(t);
                Interficie.mostrarMissatge("A quina fila (X:) vols curar els malalts: ");
                int x = Utils.validarEnter("Indica una posició per X: que estigui dins del taulell", "No has introduït un caràcter numèric", t.getFiles(), 1) - 1;
                Interficie.mostrarMissatge("A quina columna (Y:) vols curar els malalts: ");
                int y = Utils.validarEnter("Indica una posició per Y: que estigui dins del taulell", "No has introduït un caràcter numèric", t.getColumnes(), 1) - 1;
                if (t.getTaulell()[x][y] != INVALIDPOSITION) {
                    Interficie.mostrarMenu(21);
                    answerCureValue = Utils.validarEnter("Introdueix un número de la llista", "No has introduït un caràcter númeric vàlid. Torna a provar.", 2, 1);
                    switch (answerCureValue) {
                        case 1: //Curar malalts de forma individual introduïnt percentatge
                            Interficie.mostrarMissatge("Quin percentatge de malalts vols curar (%): ");
                            cureNumber = Utils.validarEnter("Introdueix un valor entre 0 - 100", "No has introduït un valor numèric", 100, 0);
                            if (t.getTaulell()[x][y] != INVALIDPOSITION) {
                                totalCured += Math.ceil(t.getTaulell()[x][y] * cureNumber / 100);
                                t.getTaulell()[x][y] -= t.getTaulell()[x][y] * cureNumber / 100;
                            }
                            break;
                        case 2: //Curar malalts de forma individual introduïnt un valor concret
                            Interficie.mostrarMissatge("Quants malalts vols curar (valor concret): ");
                            cureNumber = scan.nextInt();
                            curarMalaltsValorConcret(t, cureNumber, x, y);
                            break;
                    }
                } else {
                    Interficie.mostrarMissatgeError("No es pot curar una posició bloquejada");
                }
                break;
        }
    }

    /**
     * Funció que ens verifica que quan curem malalts amb un valor concret no
     * es curin més persones que les que hi ha malaltes en una casella.
     * @param t Es pasa el taulell de la classe "Taulell" perquè és on es guarda tota la informació del taulell que utilitzem.
     * @param cureNumber És el número de persones que es vol curar.
     * @param x És la fila on es troba el malalt.
     * @param y És la columna on es troba el malalt.
     */
    private void curarMalaltsValorConcret(Taulell t, int cureNumber, int x, int y) {
        if (t.getTaulell()[x][y] != INVALIDPOSITION) {
            if ((t.getTaulell()[x][y] - cureNumber) < 0) {
                totalCured += (t.getTaulell()[x][y] - cureNumber) + cureNumber;
                t.getTaulell()[x][y] = 0;
            } else {
                t.getTaulell()[x][y] -= cureNumber;
                totalCured += cureNumber;
            }
        }
    }

    /**
     * Funció que ens serveix per desplaçar malalts d'una cel·la a un altre. Es pot desplaçar a qualsevol cel·la colindant sempre que no sigui una posició bloquejada.
     *
     * @param t Es pasa el taulell de la classe "Taulell" perquè és on es guarda tota la informació del taulell que utilitzem.
     */
    public void desplacarMalalts(Taulell t) {
        Interficie.mostrarTaulell(t);
        Interficie.mostrarMissatge("Indica la fila (X:) del malalt que vols desplaçar: ");
        int x = Utils.validarEnter("Indica una posició per X: que estigui dins del taulell", "No has introduït un caràcter numèric", t.getFiles(), 1) - 1;
        Interficie.mostrarMissatge("Indica la columna (Y:) del malalt que vols desplaçar: ");
        int y = Utils.validarEnter("Indica una posició per Y: que estigui dins del taulell", "No has introduït un caràcter numèric", t.getColumnes(), 1) - 1;
        if (t.getTaulell()[x][y] != INVALIDPOSITION) {
            Interficie.mostrarMissatge("Quants malalts vols desplaçar?: ");
            patients = Utils.validarEnter("Introdueix un valor màxim del malalts de la casella", "No has introduït un caràcter numèric", (int) t.getTaulell()[x][y], 0);
            if (patients <= t.getTaulell()[x][y]) {
                t.getTaulell()[x][y] -= patients;
                Interficie.mostrarMenuDesplacar();
                String answerDisplace = Utils.validarLletraCasella("Introdueix una lletra de la llista", "Has de introduir un caràcter valid.");
                boolean lockedPosition = false;
                switch (answerDisplace) {
                    case "q":
                        // Comprova que x i y quan s'efectiu el desplaçament no quedi fora del taulell o en una posició bloquejada. Cada if s'adapta segons la posicio final dels malalts.
                        lockedPosition = Utils.validarCasellaDesti(t, x - 1, y - 1, "No pots desplaçar els malalts a una posició bloquejada o fora del taulell");
                        if (lockedPosition) {
                            t.getTaulell()[x - 1][y - 1] += GestorTaulell.patients;
                            GestorTaulell.totalDisplaced += GestorTaulell.patients;
                        }
                        break;
                    case "w":
                        lockedPosition = Utils.validarCasellaDesti(t, x - 1, y, "No pots desplaçar els malalts a una posició bloquejada o fora del taulell");
                        if (lockedPosition) {
                            t.getTaulell()[x - 1][y] += GestorTaulell.patients;
                            GestorTaulell.totalDisplaced += GestorTaulell.patients;
                        }
                        break;
                    case "e":
                        lockedPosition = Utils.validarCasellaDesti(t, x - 1, y + 1, "No pots desplaçar els malalts a una posició bloquejada o fora del taulell");
                        if (lockedPosition) {
                            t.getTaulell()[x - 1][y + 1] += GestorTaulell.patients;
                            GestorTaulell.totalDisplaced += GestorTaulell.patients;
                        }
                        break;
                    case "a":
                        lockedPosition = Utils.validarCasellaDesti(t, x, y - 1, "No pots desplaçar els malalts a una posició bloquejada o fora del taulell");
                        if (lockedPosition) {
                            t.getTaulell()[x][y - 1] += GestorTaulell.patients;
                            GestorTaulell.totalDisplaced += GestorTaulell.patients;
                        }
                        break;
                    case "d":
                        lockedPosition = Utils.validarCasellaDesti(t, x, y + 1, "No pots desplaçar els malalts a una posició bloquejada o fora del taulell");
                        if (lockedPosition) {
                            t.getTaulell()[x][y + 1] += GestorTaulell.patients;
                            GestorTaulell.totalDisplaced += GestorTaulell.patients;
                        }
                        break;
                    case "z":
                        lockedPosition = Utils.validarCasellaDesti(t, x + 1, y - 1, "No pots desplaçar els malalts a una posició bloquejada o fora del taulell");
                        if (lockedPosition) {
                            t.getTaulell()[x + 1][y - 1] += GestorTaulell.patients;
                            GestorTaulell.totalDisplaced += GestorTaulell.patients;
                        }
                        break;
                    case "x":
                        lockedPosition = Utils.validarCasellaDesti(t, x + 1, y, "No pots desplaçar els malalts a una posició bloquejada o fora del taulell");
                        if (lockedPosition) {
                            t.getTaulell()[x + 1][y] += GestorTaulell.patients;
                            GestorTaulell.totalDisplaced += GestorTaulell.patients;
                        }
                        break;
                    case "c":
                        lockedPosition = Utils.validarCasellaDesti(t, x + 1, y + 1, "No pots desplaçar els malalts a una posició bloquejada o fora del taulell");
                        if (lockedPosition) {
                            t.getTaulell()[x + 1][y + 1] += GestorTaulell.patients;
                            GestorTaulell.totalDisplaced += GestorTaulell.patients;
                        }
                        break;
                }
                if (!lockedPosition) {
                    t.getTaulell()[x][y] += patients;
                }
            } else {
                Interficie.mostrarMissatgeError("No pots introduïr un número superior als malalts que pertanyen a aquesta posició");
            }
        } else {
            Interficie.mostrarMissatgeError("No pots desplaçar posicions bloquejades");
        }
    }
}