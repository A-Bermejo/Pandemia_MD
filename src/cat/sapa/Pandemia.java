package cat.sapa;

import java.util.Arrays;
import java.util.Scanner;

public class Pandemia {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String menu = ("1. Carregar taulell\n" +
                "2. Introduir malalts\n" +
                "3. Transmetre virus\n" +
                "4. Curar malalts\n" +
                "5. Desplaçar malalts\n" +
                "6. Mostrar informació\n" +
                "0. Sortir");
        System.out.println(menu);
        //Declaració variables
        int x;
        int y;
        int files = 0;
        int columnes = 0;
        float taxaContagi;
        float[][] board = null; //Especifiquem "null" per poder utilitzar l'array a tot el programa"
        byte answer = scan.nextByte(); //Introduir resposta
        while (answer != 0) {
            switch (answer) {
                case 1:
                    System.out.println("Que vols fer:\n" +
                            "1. Crear un taulell buit (introduint les files y columnes)\n" +
                            "2. Taulell amb malalts de forma aleatoria (minim 2 columnes i files)");
                    byte answerTaulell = scan.nextByte();
                    switch(answerTaulell){
                        case 1:
                            System.out.print("Diga'm les mesures del taulell. Primer les files i després les columnes: ");
                            x = scan.nextInt();
                            y = scan.nextInt();
                            board = new float[x][y];
                            files = x;
                            columnes = y;
                            for (int i = 0; i < files; i++) {
                                System.out.printf("| ");
                                for (int j = 0; j < columnes; j++) {
                                    if (board[i][j] == -1){
                                        System.out.print("X | ");
                                    }else{
                                        System.out.printf("%.0f | ", board[i][j]);
                                    }

                                }
                                System.out.printf("\n");
                            }
                            break;
                        case  2:
                            x = (int)(Math.random()*9+2);
                            y = (int)(Math.random()*9+2);
                            System.out.printf("Es creara un taulell amb les següents dimensions (x: %d, y: %d)\n",x, y);
                            board = new float[x][y];
                            files = x;
                            columnes = y;
                            for (int i = 0; i < files; i++) {
                                for (int j = 0; j < columnes; j++) {
                                    board[i][j] = (int)(Math.random()*10);
                                }
                            }
                            System.out.println("S'ha creat el taulell");
                            for (int i = 0; i < files; i++) {
                                System.out.printf("| ");
                                for (int j = 0; j < columnes; j++) {
                                    if (board[i][j] == -1){
                                        System.out.print("X | ");
                                    }else{
                                        System.out.printf("%.0f | ", board[i][j]);
                                    }

                                }
                                System.out.printf("\n");
                            }
                            break;
                        default:
                            System.out.println("Només es pot introduir un número corresponent a les opcions del menú");
                            break;
                    }
                    int numPosicioRand = (int)(Math.random() * 4);
                    for (int i = 0; i < numPosicioRand; i++) {
                        x = (int)(Math.random() * files);
                        y = (int)(Math.random() * columnes);
                        board[x][y] = -1;
                    }
                    System.out.printf("Total de posicions bloquejades: %d\n", numPosicioRand);
                    break;
                case 2:
                    System.out.print("Quants malalts vols introduir: ");
                    int malalts = Math.abs(scan.nextInt());
                    int malaltsPosicio = 0;
                    for (int i = 0; i < files; i++) {
                        System.out.printf("| ");
                        for (int j = 0; j < columnes; j++) {
                            if (board[i][j] == -1){
                                System.out.print("X | ");
                            }else{
                                System.out.printf("%.0f | ", board[i][j]);
                            }

                        }
                        System.out.printf("\n");
                    }
                    for (int i = 0; i < malalts;) {
                        System.out.print("A quina fila i columna vols introduir el malalt: ");
                        x = Math.abs(scan.nextInt());
                        y = Math.abs(scan.nextInt());
                        if (x <= files && y <= columnes){
                            System.out.print("Quants malalts hi ha en aquesta posició: ");
                            malaltsPosicio = Math.abs(scan.nextInt());
                            if (malaltsPosicio + i <= malalts && board[x][y] != -1){
                                board[x][y] = malaltsPosicio;
                            }else{
                                System.out.println("No pots especificar més malalts en una posicio que el total " +
                                        "de malalts que vols introduir.");
                            }
                        }else{
                            System.out.println("Especifica una columna i fila existents en el taulell.");
                        }
                        i += malaltsPosicio;
                    }
                    break;
                case 3:
                    System.out.println("Diga'm la taxa de contagi");
                    taxaContagi = scan.nextFloat();
                    for (int i = 0; i < files; i++) {
                        for (int j = 0; j < columnes; j++) {
                            if(board[i][j] != -1){
                                board[i][j] += board[i][j] * taxaContagi;
                            }
                        }
                    }
                    break;
                case 4:
                    System.out.println("Introdueix de quina forma vols transmetre la teva cura:\n" +
                            "1. Globalment\n" +
                            "2. Per a una posició concreta");
                    byte answerCura = scan.nextByte();
                    byte answerCuraValor;
                    switch (answerCura) {
                        case 1:
                            System.out.println("Ho vols fer amb:\n" +
                                    "1. Percentatge (%)\n" +
                                    "2. Valor concret");
                            answerCuraValor = scan.nextByte();
                            int numeroCura;
                            switch (answerCuraValor){
                                case 1:
                                    System.out.println("Quin percentatge de malalts vols curar (%)");
                                    numeroCura = scan.nextInt();
                                    for (int i = 0; i < files; i++) {
                                        for (int j = 0; j < columnes; j++) {
                                            if(board[i][j] != -1) {
                                                board[i][j] -= board[i][j] * numeroCura / 100;
                                            }
                                        }
                                    }
                                    break;
                                case 2:
                                    System.out.println("Quants malalts vols curar (valor concret)");
                                    numeroCura = scan.nextInt();
                                    for (int i = 0; i < files; i++) {
                                        for (int j = 0; j < columnes; j++) {
                                            if(board[i][j] != -1) {
                                                if ((board[i][j] - numeroCura) < 0) {
                                                    board[i][j] = 0;
                                                } else {
                                                    board[i][j] -= numeroCura;
                                                }
                                            }
                                        }
                                    }
                                    break;
                                default:
                                    System.out.println("Només es pot introduir un número corresponent a les opcions del menú");
                                    break;
                            }
                            break;
                        case 2:
                            for (int i = 0; i < files; i++) {
                                System.out.printf("| ");
                                for (int j = 0; j < columnes; j++) {
                                    if (board[i][j] == -1){
                                        System.out.print("X | ");
                                    }else{
                                        System.out.printf("%.0f | ", board[i][j]);
                                    }

                                }
                                System.out.printf("\n");
                            }
                            System.out.println("A quina posicio vols curar els malalts: ");
                            x = scan.nextInt();
                            y = scan.nextInt();
                            System.out.println("Ho vols fer amb:\n" +
                                    "1. Percentatge (%)\n" +
                                    "2. Valor concret");
                            answerCuraValor = scan.nextByte();
                            switch (answerCuraValor){
                                case 1:
                                    System.out.println("Quin percentatge de malalts vols curar (%)");
                                    numeroCura = scan.nextInt();
                                    if(board[x][y] != -1) {
                                        board[x][y] -= board[x][y] * numeroCura / 100;
                                    }
                                    break;
                                case 2:
                                    System.out.println("Quants malalts vols curar (valor concret)");
                                    numeroCura = scan.nextInt();
                                    if(board[x][y] != -1) {
                                        if ((board[x][y] - numeroCura) < 0) {
                                            board[x][y] = 0;
                                        } else {
                                            board[x][y] -= numeroCura;
                                        }
                                    }
                                    break;
                                default:
                                    System.out.println("Només es pot introduir un número corresponent a les opcions del menú");
                                    break;
                            }
                            break;
                        default:
                            System.out.println("Només es pot introduir un número corresponent a les opcions del menú");
                            break;
                    }
                    break;
                case 5:

                    break;
                case 6:
                    for (int i = 0; i < files; i++) {
                        System.out.printf("| ");
                        for (int j = 0; j < columnes; j++) {
                            if (board[i][j] == -1){
                                System.out.print("X | ");
                            }else{
                                System.out.printf("%.0f | ", board[i][j]);
                            }

                        }
                        System.out.printf("\n");
                    }
                    break;
                default:
                    System.out.println("Només es pot introduir un número corresponent a les opcions del menú");
                    break;
            }
            System.out.println(menu);
            answer = scan.nextByte();
        }
        System.out.println("Adeu");
    }
}