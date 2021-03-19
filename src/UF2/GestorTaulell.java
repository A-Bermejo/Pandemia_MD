package UF2;

import java.util.Scanner;

public class GestorTaulell {

    Taulell t = new Taulell();

    //public GestorTaulell(int f, int c) {
    //  t = new Taulell(f,c);
    //}
    static Scanner scan = new Scanner(System.in);
    private static int rows;
    private static int columns;
    public static int patients;
    public static int totalCured;
    public static float totalPatients;
    public static int totalDisplaced;

    public void carregarDades(Taulell t) {
        Interficie.mostrarMenu(1);
        byte answerBoard = scan.nextByte();
        totalCured = 0;
        totalPatients = 0;
        totalDisplaced = 0;
        switch (answerBoard) {
            case 1: //Creació de taulell buit amb mesures especificades per l'usuari
                Interficie.mostrarMissatge("Diga'm les mesures del taulell en números. Primer les files i després les columnes: ");
                rows = scan.nextInt();
                columns = scan.nextInt();
                t.setFiles(rows);
                t.setColumnes(columns);
                t.setTaulell(new float[t.getFiles()][t.getColumnes()]);
                break;
            case 2: //Creació d'un taulell aleatori
                rows = (int) (Math.random() * 9 + 2);
                columns = (int) (Math.random() * 9 + 2);
                t.setFiles(rows);
                t.setColumnes(columns);
                t.setTaulell(new float[t.getFiles()][t.getColumnes()]);
                Interficie.mostrarMissatge("Es crearà un taulell amb les següents dimensions (x:" + rows + " y:" + columns + ")");
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        t.getTaulell()[i][j] = (int) (Math.random() * 10);
                    }
                }
                break;
            /*default:
                // Validar en la clase Utils
                noError = false;
                System.out.println(defaultMessage);
                break;*/
        }
        // Despues de validar
        int x;
        int y;
        int numPositionRand = (int) (Math.random() * 4);
        for (int i = 0; i < numPositionRand; i++) {
            x = (int) (Math.random() * rows);
            y = (int) (Math.random() * columns);
            t.getTaulell()[x][y] = -1;
        }
        Interficie.mostrarMissatge("Total de posicions bloquejades: " + numPositionRand);
        Interficie.mostrarTaulell(t);
    }

    public void introduirMalalts(float[][] board) {
        Interficie.mostrarMissatge("Quants malalts vols introduir: ");
        patients = Math.abs(scan.nextInt());
        int positionPatients = 0;
        Interficie.mostrarTaulell(t);
        for (int i = 0; i < patients; ) {
            Interficie.mostrarMissatge("A quina fila i columna vols introduir el malalt: ");
            int x = Math.abs(scan.nextInt()) - 1; // Validar que no sigui 0
            int y = Math.abs(scan.nextInt()) - 1; // Validar que no sigui 0
            // Cambiar els dos ifs a funcio en utils
            if (board[x][y] != -1) {
                if (x <= rows && y <= columns) {
                    Interficie.mostrarMissatge("Quants malalts hi ha en aquesta posició: ");
                    positionPatients = Math.abs(scan.nextInt());
                    if (positionPatients + i <= patients) { // Es suma positionPatients + i per tenir en compte els malalts que ya s'han introduit.
                        board[x][y] += positionPatients;
                    } else {
                        Interficie.mostrarMissatgeError("No pots especificar més malalts en una posicio que el total" +
                                " de malalts que vols introduir.");
                    }
                } else {
                    System.out.println("Especifica una columna i fila existents en el taulell.");
                }
            } else {
                Interficie.mostrarMissatgeError("No pots introduir malalts en una posició bloquejada");
            }
            i += positionPatients;
        }
    }

    public void transmetreVirus(Taulell t){
        Interficie.mostrarMissatge("Diga'm la taxa de contagi: ");
        float infectionRate = Math.abs(scan.nextFloat());
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (t.getTaulell()[i][j] != -1) {
                    t.getTaulell()[i][j] += t.getTaulell()[i][j] * infectionRate;
                }
            }
        }
    }
    public void curarMalalts(Taulell t){
        Interficie.mostrarMenu(2);
        byte answerCure = scan.nextByte();
        byte answerCureValue;
        switch (answerCure) {
            case 1: //Curar malalts de forma global
                Interficie.mostrarMenu(21);
                answerCureValue = scan.nextByte();
                int cureNumber;
                switch (answerCureValue) {
                    case 1: //Curar malalts globalment introduïnt un percentatge
                        Interficie.mostrarMissatge("Quin percentatge de malalts vols curar (%): ");
                        cureNumber = scan.nextInt();
                        // Validar que no sigui major a 100
                        if (cureNumber > 100) {
                            Interficie.mostrarMissatgeError("Ja que no es pot curar més d'un 100% i s'ha introduït un número més gran que aquest, la cura es limitarà a 100%.");
                            cureNumber = 100;
                        }
                        for (int i = 0; i < rows; i++) {
                            for (int j = 0; j < columns; j++) {
                                if (t.getTaulell()[i][j] != -1) {
                                    totalCured += Math.ceil(t.getTaulell()[i][j] * cureNumber / 100);
                                    t.getTaulell()[i][j] -= t.getTaulell()[i][j] * cureNumber / 100;
                                }
                            }
                        }
                        break;
                    case 2: //Curar malalts globalment introduïnt un valor concret
                        System.out.print("Quants malalts vols curar (valor concret): ");
                        cureNumber = scan.nextInt();
                        for (int i = 0; i < rows; i++) {
                            for (int j = 0; j < columns; j++) {
                                if (t.getTaulell()[i][j] != -1) {
                                    if ((t.getTaulell()[i][j] - cureNumber) < 0) {
                                        totalCured += (t.getTaulell()[i][j] - cureNumber) + cureNumber;
                                        t.getTaulell()[i][j] = 0;
                                    } else {
                                        t.getTaulell()[i][j] -= cureNumber;
                                        totalCured += cureNumber;
                                    }
                                }
                            }
                        }
                        break;
                    default:
                        Interficie.mostrarMissatgeError("Només es pot introduir un número corresponent a les opcions del menú");
                        break;
                }
                break;
            case 2: //Curar malalts de forma individual
                Interficie.mostrarTaulell(t);
                Interficie.mostrarMissatge("A quina posicio vols curar els malalts: ");
                int x = scan.nextInt() - 1; // Validar que no sigui 0
                int y = scan.nextInt() - 1; // Validar que no sigui 0
                if (t.getTaulell()[x][y] != -1) {
                    Interficie.mostrarMenu(21);
                    answerCureValue = scan.nextByte();
                    switch (answerCureValue) {
                        case 1: //Curar malalts de forma individual introduïnt percentatge
                            Interficie.mostrarMissatge("Quin percentatge de malalts vols curar (%): ");
                            cureNumber = scan.nextInt();
                            if (cureNumber > 100) {
                                Interficie.mostrarMissatgeError("Ja que no es pot curar més d'un 100% i s'ha introduït un número més gran que aquest, la cura es limitarà a 100%.");
                                cureNumber = 100;
                            }
                            if (t.getTaulell()[x][y] != -1) {
                                totalCured += Math.ceil(t.getTaulell()[x][y] * cureNumber / 100);
                                t.getTaulell()[x][y] -= t.getTaulell()[x][y] * cureNumber / 100;
                            }
                            break;
                        case 2: //Curar malalts de forma individual introduïnt un valor concret
                            System.out.print("Quants malalts vols curar (valor concret): ");
                            cureNumber = scan.nextInt();
                            if (t.getTaulell()[x][y] != -1) {
                                if ((t.getTaulell()[x][y] - cureNumber) < 0) {
                                    totalCured += (t.getTaulell()[x][y] - cureNumber) + cureNumber;
                                    t.getTaulell()[x][y] = 0;
                                } else {
                                    t.getTaulell()[x][y] -= cureNumber;
                                    totalCured += cureNumber;
                                }
                            }
                            break;
                        default:
                            Interficie.mostrarMissatgeError("Només es pot introduir un número corresponent a les opcions del menú");
                            break;
                    }
                } else {
                    Interficie.mostrarMissatgeError("No es pot curar una posició bloquejada");
                }
                break;
            default:
                Interficie.mostrarMissatgeError("Només es pot introduir un número corresponent a les opcions del menú");
                break;
        }
    }
    public void mostrarInformació(Taulell t) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (t.getTaulell()[i][j] != -1) {
                    totalPatients += t.getTaulell()[i][j];
                }
            }
        }
    }
}
