package cat.sapa;

import java.util.Scanner;

public class Pandemia {

    //Declaració de Constants (Colors per aplicar al text)
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[38m";

    //Creació d'un method per evitar codi redundant
    private static void menu(int rows, int columns, float[][] board) {
        for (int i = 0; i < rows; i++) {
            System.out.print(BLUE + "| ");
            for (int j = 0; j < columns; j++) {
                if (board[i][j] == -1) {
                    System.out.printf(RED + "%4s    " + BLUE + "| ", "X");
                } else {
                    System.out.printf(GREEN + "%-7.0f " + BLUE + "| ", Math.floor(board[i][j]));
                }

            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String menu = (WHITE + "1. Carregar taulell   4. Curar malalts\n" +
                "2. Introduir malalts  5. Desplaçar malalts\n" +
                "3. Transmetre virus   6. Mostrar informació\n" +
                "0. Sortir");
        System.out.println(menu);
        //Declaració variables
        int x;
        int y;
        int rows = 0;
        int columns = 0;
        int patients = 0;
        int positionPatients = 0;
        float infectionRate;
        float totalPatients = 0;
        int totalCured = 0;
        int totalDisplaced = 0;
        //Declaració de variables Constants
        final String errorMessage = YELLOW + "No pots utilitzar aquesta opció fins que no hi hagi un taulell creat";
        final String defaultMessage = YELLOW + "Només es pot introduir un número corresponent a les opcions del menú";
        boolean noError = true;
        float[][] board = null; //Especifiquem "null" per poder utilitzar l'array a tot el programa"
        byte answer = scan.nextByte(); //Introduïr resposta
        while (answer != 0) { //Bucle per repetir tot el programa a no ser que la resposta sigui 0
            switch (answer) {
                case 1: //Carregar Taulell
                    System.out.println(WHITE + "Que vols fer:\n" +
                            "1. Crear un taulell buit (introduint les files y columnes)\n" +
                            "2. Taulell amb malalts de forma aleatoria (minim 2 columnes i files)");
                    byte answerBoard = scan.nextByte();
                    switch (answerBoard) {
                        case 1: //Creació de taulell buit amb mesures especificades per l'usuari
                            System.out.print("Diga'm les mesures del taulell en números. Primer les files i després les columnes: ");
                            x = scan.nextInt();
                            y = scan.nextInt();
                            board = new float[x][y];
                            rows = x;
                            columns = y;
                            totalCured = 0;
                            totalPatients = 0;
                            totalDisplaced = 0;
                            break;
                        case 2: //Creació d'un taulell aleatori
                            x = (int) (Math.random() * 9 + 2);
                            y = (int) (Math.random() * 9 + 2);
                            System.out.printf("Es creará un taulell amb les següents dimensions (x: %d, y: %d)\n", x, y);
                            board = new float[x][y];
                            rows = x;
                            columns = y;
                            totalCured = 0;
                            totalPatients = 0;
                            totalDisplaced = 0;
                            for (int i = 0; i < rows; i++) {
                                for (int j = 0; j < columns; j++) {
                                    board[i][j] = (int) (Math.random() * 10);
                                }
                            }
                            break;
                        default:
                            noError = false;
                            System.out.println(defaultMessage);
                            break;
                    }
                    if (noError) {
                        int numPositionRand = (int) (Math.random() * 4);
                        for (int i = 0; i < numPositionRand; i++) {
                            x = (int) (Math.random() * rows);
                            y = (int) (Math.random() * columns);
                            board[x][y] = -1;
                        }
                        System.out.printf("Total de posicions bloquejades: %d\n", numPositionRand);
                        menu(rows, columns, board);
                    }
                    break;
                case 2: //Introduïr malalts
                    if (board != null) {
                        System.out.print(WHITE + "Quants malalts vols introduir: ");
                        patients = Math.abs(scan.nextInt());
                        positionPatients = 0;
                        menu(rows, columns, board);
                        for (int i = 0; i < patients; ) {
                            System.out.print(WHITE + "A quina fila i columna vols introduir el malalt: ");
                            x = Math.abs(scan.nextInt());
                            y = Math.abs(scan.nextInt());
                            if (x <= rows && y <= columns) {
                                System.out.print("Quants malalts hi ha en aquesta posició: ");
                                positionPatients = Math.abs(scan.nextInt());
                                if (positionPatients + i <= patients && board[x][y] != -1) {
                                    board[x][y] += positionPatients;
                                } else {
                                    System.out.println(YELLOW + "No pots especificar més malalts en una posicio que el total " +
                                            "de malalts que vols introduir.");
                                }
                            } else {
                                System.out.println("Especifica una columna i fila existents en el taulell.");
                            }
                            i += positionPatients;
                        }
                    } else {
                        System.out.println(errorMessage);
                    }
                    break;
                case 3: //Transmitir virus
                    if (board != null) {
                        System.out.print (WHITE + "Diga'm la taxa de contagi: ");
                        infectionRate = Math.abs(scan.nextFloat());
                        for (int i = 0; i < rows; i++) {
                            for (int j = 0; j < columns; j++) {
                                if (board[i][j] != -1) {
                                    board[i][j] += board[i][j] * infectionRate;
                                }
                            }
                        }
                    } else {
                        System.out.println(errorMessage);
                    }
                    break;
                case 4: //Curar malalts
                    if (board != null) {
                        System.out.println(WHITE + "Introdueix de quina forma vols transmetre la teva cura:\n" +
                                "1. Globalment\n" +
                                "2. Per a una posició concreta");
                        byte answerCure = scan.nextByte();
                        byte answerCureValue;
                        switch (answerCure) {
                            case 1: //Curar malalts de forma global
                                System.out.println("Ho vols fer amb:\n" +
                                        "1. Percentatge (%)\n" +
                                        "2. Valor concret");
                                answerCureValue = scan.nextByte();
                                int cureNumber;
                                switch (answerCureValue) {
                                    case 1: //Curar malalts globalment introduïnt un percentatge
                                        System.out.print("Quin percentatge de malalts vols curar (%): ");
                                        cureNumber = scan.nextInt();
                                        for (int i = 0; i < rows; i++) {
                                            for (int j = 0; j < columns; j++) {
                                                if (board[i][j] != -1) {
                                                    totalCured += Math.ceil(board[i][j] * cureNumber / 100); // algo falla aqui ಠಿ_ಠ
                                                    board[i][j] -= board[i][j] * cureNumber / 100;
                                                }
                                            }
                                        }
                                        break;
                                    case 2: //Curar malalts globalment introduïnt un valor concret
                                        System.out.print("Quants malalts vols curar (valor concret): ");
                                        cureNumber = scan.nextInt();
                                        for (int i = 0; i < rows; i++) {
                                            for (int j = 0; j < columns; j++) {
                                                if (board[i][j] != -1) {
                                                    if ((board[i][j] - cureNumber) < 0) {
                                                        totalCured += (board[i][j] - cureNumber) + cureNumber;
                                                        board[i][j] = 0;
                                                    } else {
                                                        board[i][j] -= cureNumber;
                                                        totalCured += cureNumber;
                                                    }
                                                }
                                            }
                                        }
                                        break;
                                    default:
                                        System.out.println(YELLOW + "Només es pot introduir un número corresponent a les opcions del menú");
                                        break;
                                }
                                break;
                            case 2: //Curar malalts de forma individual
                                menu(rows, columns, board);
                                System.out.print(WHITE + "A quina posicio vols curar els malalts: ");
                                x = scan.nextInt();
                                y = scan.nextInt();
                                if (board[x][y] != -1) {
                                    System.out.println("Ho vols fer amb:\n" +
                                            "1. Percentatge (%)\n" +
                                            "2. Valor concret");
                                    answerCureValue = scan.nextByte();
                                    switch (answerCureValue) {
                                        case 1: //Curar malalts de forma individual introduïnt percentatge
                                            System.out.print("Quin percentatge de malalts vols curar (%): ");
                                            cureNumber = scan.nextInt();
                                            if (board[x][y] != -1) {
                                                totalCured += Math.ceil(board[x][y] * cureNumber / 100);
                                                board[x][y] -= board[x][y] * cureNumber / 100;
                                            }
                                            break;
                                        case 2: //Curar malalts de forma individual introduïnt un valor concret
                                            System.out.print("Quants malalts vols curar (valor concret): ");
                                            cureNumber = scan.nextInt();
                                            if (board[x][y] != -1) {
                                                if ((board[x][y] - cureNumber) < 0) {
                                                    totalCured += (board[x][y] - cureNumber) + cureNumber;
                                                    board[x][y] = 0;
                                                } else {
                                                    board[x][y] -= cureNumber;
                                                    totalCured += cureNumber;
                                                }
                                            }
                                            break;
                                        default:
                                            System.out.println(YELLOW + "Només es pot introduir un número corresponent a les opcions del menú");
                                            break;
                                    }
                                } else {
                                    System.out.println(YELLOW + "No es pot curar una posició bloquejada");
                                }
                                break;
                            default:
                                System.out.println(YELLOW + "Només es pot introduir un número corresponent a les opcions del menú");
                                break;
                        }
                    } else {
                        System.out.println(errorMessage);
                    }
                    break;
                case 5: //Desplaçar malalts
                    if (board != null) {
                        menu(rows, columns, board);
                        System.out.print(WHITE + "Indica la posició del malalt que vols desplaçar: ");
                        x = scan.nextInt();
                        y = scan.nextInt();
                        if (board[x][y] != -1) {
                            System.out.print("Quants malalts vols desplaçar?: ");
                            patients = scan.nextInt();
                            if (patients <= board[x][y]) {
                                board[x][y] -= patients;
                                System.out.println("De quina manera vols desplaçar els malalts?:\n" +
                                        RED + "Q. (Dalt esquerra) " + WHITE + "| " + CYAN + "W. (Dalt mig) " + WHITE + "| " + RED + "E. (Dalt dreta)\n" +
                                        BLUE + "A. (Esquerra mig)  " + WHITE + "|               " + WHITE + "| " + BLUE + "D. (Dreta mig)\n" +
                                        YELLOW + "Z. (Baix esquerra) " + WHITE + "| " + CYAN + "X. (Baix mig) " + WHITE + "| " + YELLOW + "C. (Baix dreta)");
                                String answerDisplace = scan.next();
                                answerDisplace = answerDisplace.toLowerCase();
                                boolean lockedPosition = false;
                                switch (answerDisplace) {
                                    case "q":
                                        if ((x - 1 != -1 && y - 1 != -1) && board[x - 1][y - 1] != -1) {
                                            board[x - 1][y - 1] += patients;
                                            totalDisplaced += patients;
                                        } else {
                                            lockedPosition = true;
                                        }
                                        break;
                                    case "w":
                                        if (x - 1 != -1 && board[x - 1][y] != -1) {
                                            board[x - 1][y] += patients;
                                            totalDisplaced += patients;
                                        } else {
                                            lockedPosition = true;
                                        }
                                        break;
                                    case "e":
                                        if ((x - 1 != -1 && y + 1 != columns) && board[x - 1][y + 1] != -1) {
                                            board[x - 1][y + 1] += patients;
                                            totalDisplaced += patients;
                                        } else {
                                            lockedPosition = true;
                                        }
                                        break;
                                    case "a":
                                        if (y - 1 != -1 && board[x][y - 1] != -1) {
                                            board[x][y - 1] += patients;
                                            totalDisplaced += patients;
                                        } else {
                                            lockedPosition = true;
                                        }
                                        break;
                                    case "d":
                                        if (y + 1 != columns && board[x][y + 1] != -1) {
                                            board[x][y + 1] += patients;
                                            totalDisplaced += patients;
                                        } else {
                                            lockedPosition = true;
                                        }
                                        break;
                                    case "z":
                                        if ((x + 1 != rows && y - 1 != -1) && board[x + 1][y - 1] != -1) {
                                            board[x + 1][y - 1] += patients;
                                            totalDisplaced += patients;
                                        } else {
                                            lockedPosition = true;
                                        }
                                        break;
                                    case "x":
                                        if (x + 1 != rows && board[x + 1][y] != -1) {
                                            board[x + 1][y] += patients;
                                            totalDisplaced += patients;
                                        } else {
                                            lockedPosition = true;
                                        }
                                        break;
                                    case "c":
                                        if ((x + 1 != rows && y + 1 != columns) && board[x + 1][y + 1] != -1) {
                                            board[x + 1][y + 1] += patients;
                                            totalDisplaced += patients;
                                        } else {
                                            lockedPosition = true;
                                        }
                                        break;
                                    default:
                                        System.out.println(YELLOW + "Només es pot introduir un número corresponent a les opcions del menú");
                                        break;
                                }
                                if (lockedPosition) {
                                    board[x][y] += patients;
                                    System.out.println(YELLOW + "No pots desplaçar els malalts a una posició bloquejada o fora del taulell");
                                }
                            } else {
                                System.out.println(YELLOW + "No pots introduïr un número superior als malalts que pertanyen a aquesta posició");
                            }
                        } else {
                            System.out.println(YELLOW + "No pots desplaçar posicions bloquejades");
                        }
                    } else {
                        System.out.println(errorMessage);
                    }
                    break;
                case 6: //Mostrar informació
                    if (board != null) {
                        totalPatients = 0;
                        for (int i = 0; i < rows; i++) {
                            for (int j = 0; j < columns; j++) {
                                if (board[i][j] != -1) {
                                    totalPatients += board[i][j];
                                }
                            }
                        }
                        System.out.printf(WHITE + "Número total de malalts: %.0f\n" +
                                        "Número de persones curades: %d\n" +
                                        "Percentatge que no ha complit confinament: %.0f%%\n"
                                , totalPatients, totalCured, totalDisplaced * 100 / totalPatients);
                        menu(rows, columns, board);
                        break;
                    } else {
                        System.out.println(errorMessage);
                    }
                default:
                    System.out.println(YELLOW + "Només es pot introduir un número corresponent a les opcions del menú");
                    break;
            }
            System.out.println(menu);
            answer = scan.nextByte();
        }
        System.out.println(PURPLE + "Adeu! Esperem veure't aviat! ^o^");
    }
}