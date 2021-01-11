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

    // Creació d'un method per evitar codi redundant
    private static void Menu(int files, int columnes, float[][] board) {
        for (int i = 0; i < files; i++) {
            System.out.print(BLUE + "| ");
            for (int j = 0; j < columnes; j++) {
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
        int files = 0;
        int columnes = 0;
        int malalts = 0;
        int malaltsPosicio = 0;
        float taxaContagi;
        float totalMalalts = 0;
        int totalCurats = 0;
        int totalDesplacats = 0;
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
                    byte answerTaulell = scan.nextByte();
                    switch (answerTaulell) {
                        case 1: //Creació de taulell buit amb mesures especificades per l'usuari
                            System.out.print("Diga'm les mesures del taulell. Primer les files i després les columnes: ");
                            x = scan.nextInt();
                            y = scan.nextInt();
                            board = new float[x][y];
                            files = x;
                            columnes = y;
                            totalCurats = 0;
                            totalMalalts = 0;
                            break;
                        case 2: //Creació d'un taulell aleatori
                            x = (int) (Math.random() * 9 + 2);
                            y = (int) (Math.random() * 9 + 2);
                            System.out.printf("Es creará un taulell amb les següents dimensions (x: %d, y: %d)\n", x, y);
                            board = new float[x][y];
                            files = x;
                            columnes = y;
                            for (int i = 0; i < files; i++) {
                                for (int j = 0; j < columnes; j++) {
                                    board[i][j] = (int) (Math.random() * 10);
                                }
                            }
                            totalCurats = 0;
                            totalMalalts = 0;
                            break;
                        default:
                            noError = false;
                            System.out.println(defaultMessage);
                            break;
                    }
                    if (noError) {
                        int numPosicioRand = (int) (Math.random() * 4);
                        for (int i = 0; i < numPosicioRand; i++) {
                            x = (int) (Math.random() * files);
                            y = (int) (Math.random() * columnes);
                            board[x][y] = -1;
                        }
                        System.out.printf("Total de posicions bloquejades: %d\n", numPosicioRand);
                        Menu(files, columnes, board);
                    }
                    break;
                case 2: //Introduïr malalts
                    if (board != null) {
                        System.out.print(WHITE + "Quants malalts vols introduir: ");
                        malalts = Math.abs(scan.nextInt());
                        malaltsPosicio = 0;
                        Menu(files, columnes, board);
                        for (int i = 0; i < malalts; ) {
                            System.out.print(WHITE + "A quina fila i columna vols introduir el malalt: ");
                            x = Math.abs(scan.nextInt());
                            y = Math.abs(scan.nextInt());
                            if (x <= files && y <= columnes) {
                                System.out.print("Quants malalts hi ha en aquesta posició: ");
                                malaltsPosicio = Math.abs(scan.nextInt());
                                if (malaltsPosicio + i <= malalts && board[x][y] != -1) {
                                    board[x][y] += malaltsPosicio;
                                } else {
                                    System.out.println(YELLOW + "No pots especificar més malalts en una posicio que el total " +
                                            "de malalts que vols introduir.");
                                }
                            } else {
                                System.out.println("Especifica una columna i fila existents en el taulell.");
                            }
                            i += malaltsPosicio;
                        }
                    } else {
                        System.out.println(errorMessage);
                    }
                    break;
                case 3: //Transmitir virus
                    if (board != null) {
                        System.out.println(WHITE + "Diga'm la taxa de contagi");
                        taxaContagi = scan.nextFloat();
                        for (int i = 0; i < files; i++) {
                            for (int j = 0; j < columnes; j++) {
                                if (board[i][j] != -1) {
                                    board[i][j] += board[i][j] * taxaContagi;
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
                        byte answerCura = scan.nextByte();
                        byte answerCuraValor;
                        switch (answerCura) {
                            case 1: //Curar malalts de forma global
                                System.out.println("Ho vols fer amb:\n" +
                                        "1. Percentatge (%)\n" +
                                        "2. Valor concret");
                                answerCuraValor = scan.nextByte();
                                int numeroCura;
                                switch (answerCuraValor) {
                                    case 1: //Curar malalts globalment introduïnt un percentatge
                                        System.out.println("Quin percentatge de malalts vols curar (%)");
                                        numeroCura = scan.nextInt();
                                        for (int i = 0; i < files; i++) {
                                            for (int j = 0; j < columnes; j++) {
                                                if (board[i][j] != -1) {
                                                    totalCurats += Math.ceil(board[i][j] * numeroCura / 100); // algo falla aqui ಠಿ_ಠ
                                                    board[i][j] -= board[i][j] * numeroCura / 100;
                                                }
                                            }
                                        }
                                        break;
                                    case 2: //Curar malalts globalment introduïnt un valor concret
                                        System.out.println("Quants malalts vols curar (valor concret)");
                                        numeroCura = scan.nextInt();
                                        for (int i = 0; i < files; i++) {
                                            for (int j = 0; j < columnes; j++) {
                                                if (board[i][j] != -1) {
                                                    if ((board[i][j] - numeroCura) < 0) {
                                                        totalCurats += (board[i][j] - numeroCura) + numeroCura;
                                                        board[i][j] = 0;
                                                    } else {
                                                        board[i][j] -= numeroCura;
                                                        totalCurats += numeroCura;
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
                                Menu(files, columnes, board);
                                System.out.println("A quina posicio vols curar els malalts: ");
                                x = scan.nextInt();
                                y = scan.nextInt();
                                System.out.println("Ho vols fer amb:\n" +
                                        "1. Percentatge (%)\n" +
                                        "2. Valor concret");
                                answerCuraValor = scan.nextByte();
                                switch (answerCuraValor) {
                                    case 1: //Curar malalts de forma individual introduïnt percentatge
                                        System.out.println("Quin percentatge de malalts vols curar (%)");
                                        numeroCura = scan.nextInt();
                                        if (board[x][y] != -1) {
                                            totalCurats += Math.ceil(board[x][y] * numeroCura / 100);
                                            board[x][y] -= board[x][y] * numeroCura / 100;
                                        }
                                        break;
                                    case 2: //Curar malalts de forma individual introduïnt un valor concret
                                        System.out.println("Quants malalts vols curar (valor concret)");
                                        numeroCura = scan.nextInt();
                                        if (board[x][y] != -1) {
                                            if ((board[x][y] - numeroCura) < 0) {
                                                totalCurats += (board[x][y] - numeroCura) + numeroCura;
                                                board[x][y] = 0;
                                            } else {
                                                board[x][y] -= numeroCura;
                                                totalCurats += numeroCura;
                                            }
                                        }
                                        break;
                                    default:
                                        System.out.println(YELLOW + "Només es pot introduir un número corresponent a les opcions del menú");
                                        break;
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
                        Menu(files, columnes, board);
                        System.out.println(WHITE + "Indica la posició del malalt que vols desplaçar");
                        x = scan.nextInt();
                        y = scan.nextInt();
                        if (board[x][y] != -1) {
                            System.out.println("Quants malalts vols desplaçar?");
                            malalts = scan.nextInt();
                            if (malalts <= board[x][y]) {
                                board[x][y] -= malalts;
                                System.out.println("De quina manera vols desplaçar els malalts?\n" +
                                        RED + "Q. (Dalt esquerra) " + WHITE + "| " + CYAN + "W. (Dalt mig) " + WHITE + "| " + RED + "E. (Dalt dreta)\n" +
                                        BLUE + "A. (Esquerra mig)  " + WHITE + "|               " + WHITE + "| " + BLUE +"D. (Dreta mig)\n" +
                                        YELLOW + "Z. (Baix esquerra) " + WHITE + "| " + CYAN + "X. (Baix mig) " + WHITE + "| " + YELLOW + "C. (Baix dreta)");
                                String answerDesplacar = scan.next();
                                answerDesplacar = answerDesplacar.toLowerCase();
                                boolean posicioBloquejada = false;
                                switch (answerDesplacar) {
                                    case "q":
                                        if ((x - 1 != -1 && y - 1 != -1) && board[x - 1][y - 1] != -1) {
                                            board[x - 1][y - 1] += malalts;
                                            totalDesplacats += malalts;
                                        } else {
                                            posicioBloquejada = true;
                                        }
                                        break;
                                    case "w":
                                        if (x - 1 != -1 && board[x - 1][y] != -1) {
                                            board[x - 1][y] += malalts;
                                            totalDesplacats += malalts;
                                        } else {
                                            posicioBloquejada = true;
                                        }
                                        break;
                                    case "e":
                                        if ((x - 1 != -1 && y + 1 != columnes) && board[x - 1][y + 1] != -1) {
                                            board[x - 1][y + 1] += malalts;
                                            totalDesplacats += malalts;
                                        } else {
                                            posicioBloquejada = true;
                                        }
                                        break;
                                    case "a":
                                        if (y - 1 != -1 && board[x][y - 1] != -1) {
                                            board[x][y - 1] += malalts;
                                            totalDesplacats += malalts;
                                        } else {
                                            posicioBloquejada = true;
                                        }
                                        break;
                                    case "d":
                                        if (y + 1 != columnes && board[x][y + 1] != -1) {
                                            board[x][y + 1] += malalts;
                                            totalDesplacats += malalts;
                                        } else {
                                            posicioBloquejada = true;
                                        }
                                        break;
                                    case "z":
                                        if ((x + 1 != files && y - 1 != -1) && board[x + 1][y - 1] != -1) {
                                            board[x + 1][y - 1] += malalts;
                                            totalDesplacats += malalts;
                                        } else {
                                            posicioBloquejada = true;
                                        }
                                        break;
                                    case "x":
                                        if (x + 1 != files && board[x + 1][y] != -1) {
                                            board[x + 1][y] += malalts;
                                            totalDesplacats += malalts;
                                        } else {
                                            posicioBloquejada = true;
                                        }
                                        break;
                                    case "c":
                                        if ((x + 1 != files && y + 1 != columnes) && board[x + 1][y + 1] != -1) {
                                            board[x + 1][y + 1] += malalts;
                                            totalDesplacats += malalts;
                                        } else {
                                            posicioBloquejada = true;
                                        }
                                        break;
                                    default:
                                        System.out.println(YELLOW + "Només es pot introduir un número corresponent a les opcions del menú");
                                        break;
                                }
                                if (posicioBloquejada) {
                                    board[x][y] += malalts;
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
                        System.out.printf(WHITE + "Número total de malalts: %.0f\n" +
                                        "Número de persones curades: %d\n" +
                                        "Percentatge que no ha complit confinament: %.0f%%\n"
                                , totalMalalts, totalCurats, totalDesplacats * 100 / totalMalalts);
                        Menu(files, columnes, board);
                        for (int i = 0; i < files; i++) {
                            for (int j = 0; j < columnes; j++) {
                                if (board[i][j] != -1){
                                    totalMalalts += board[i][j];
                                }
                            }
                        }
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