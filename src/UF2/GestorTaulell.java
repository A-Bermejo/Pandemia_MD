package UF2;

import java.util.Scanner;

public class GestorTaulell {

    //private Taulell t;

    //public GestorTaulell(int f, int c) {
    //  t = new Taulell(f,c);
    //}
    static Scanner scan = new Scanner(System.in);
    public static int rows;
    public static int columns;
    public static int patients;
    public static int totalCured;
    public static int totalPatients;
    public static int totalDisplaced;

    public void carregarDades(float[][] board) {
        int x;
        int y;
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
                board = new float[rows][columns];
                break;
            case 2: //Creació d'un taulell aleatori
                rows = (int) (Math.random() * 9 + 2);
                columns = (int) (Math.random() * 9 + 2);
                Interficie.mostrarMissatge("Es crearà un taulell amb les següents dimensions (x:" + rows + " y:" + columns + ")");
                board = new float[rows][columns];
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        board[i][j] = (int) (Math.random() * 10);
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
        int numPositionRand = (int) (Math.random() * 4);
        for (int i = 0; i < numPositionRand; i++) {
            x = (int) (Math.random() * rows);
            y = (int) (Math.random() * columns);
            board[x][y] = -1;
        }
        Interficie.mostrarMissatge("Total de posicions bloquejades: " + numPositionRand);
        Interficie.mostrarTaulell(board);
    }

    public void introduirMalalts(float[][] board) {
        Interficie.mostrarMissatge("Quants malalts vols introduir: ");
        patients = Math.abs(scan.nextInt());
        int positionPatients = 0;
        Interficie.mostrarTaulell(board);
        for (int i = 0; i < patients; ) {
            Interficie.mostrarMissatge("A quina fila i columna vols introduir el malalt: ");
            int x = Math.abs(scan.nextInt());
            int y = Math.abs(scan.nextInt());
            if (board[x][y] != -1) {
                if (x <= rows && y <= columns) {
                    Interficie.mostrarMissatge("Quants malalts hi ha en aquesta posició: ");
                    positionPatients = Math.abs(scan.nextInt());
                    if (positionPatients + i <= patients) { // Es suma positionPatients + i per tenir en compte els malalts que ya s'han introduit.
                        board[x][y] += positionPatients;
                    } else {
                        Interficie.mostrarMissatgeError("No pots especificar més malalts en una posicio que el total" +
                                "de malalts que vols introduir.");
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

    public void mostrarInformació(float[][] board) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j] != -1) {
                    totalPatients += board[i][j];
                }
            }
        }
    }
}
