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
                            for (int i = 0; i < board.length; i++) {
                                System.out.println(Arrays.toString(board[i]));
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
                            for (int i = 0; i < board.length; i++) {
                                System.out.println(Arrays.toString(board[i]));
                            }
                            break;
                        default:
                            System.out.println("Només es pot introduir un número corresponent a les opcions del menú");
                            break;
                    }
                    // posicions bloquejades es representarana amb null per mostrar una X
                    break;
                case 2:
                    System.out.print("Quants malalts vols introduir: ");
                    int malalts = scan.nextInt();
                    for (int i = 0; i < malalts;) {
                        System.out.print("A quina fila i columna vols introduir el malalt: ");
                        x = scan.nextInt();
                        y = scan.nextInt();
                        System.out.print("Quants malalts hi ha en aquesta posició: ");
                        int malaltsPosicio = scan.nextInt();
                        if (x <= files && y <= columnes){
                            board[x][y] =  malaltsPosicio;
                        }else{
                            System.out.println();
                        }
                        i += malaltsPosicio;
                    }
                    break;
                case 3:
                    System.out.println("Diga'm la taxa de contagi");
                    taxaContagi = scan.nextFloat();
                    for (int i = 0; i < files; i++) {
                        for (int j = 0; j < columnes; j++) {
                            board[i][j] += board[i][j] * taxaContagi;
                        }
                    }
                    break;
                case 4:
                    System.out.println("Introdueix de quina forma vols transmetre la teva cura:\n" +
                            "1. Globalment\n" +
                            "2. Per a una posició concreta");
                    byte answerCura = scan.nextByte();
                    switch (answerCura) {
                        case 1:
                            break;
                        case 2:
                            break;
                        default:
                            System.out.println("Només es pot introduir un número corresponent a les opcions del menú");
                            break;
                    }
                    break;
                case 5:

                    break;
                case 6:
                    for (int i = 0; i < board.length; i++) {
                        System.out.println(Arrays.toString(board[i]));
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