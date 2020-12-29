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
        int[][] board = null; //Especifiquem "null" per poder utilitzar l'array a tot el programa"
        byte answer = scan.nextByte(); //Introduir resposta
        while (answer != 0) {
            switch (answer) {
                case 1:
                    System.out.println("Que vols fer:\n" +
                            "1. Carregar taulell buit\n" +
                            "2. Carregar el taulell amb contingut nou");
                    byte answerCarregar = scan.nextByte();
                    switch (answerCarregar) {
                        case 1:
                            System.out.println("Diga'm les mesures del taulell. Primer les files i després les columnes:");
                            x = scan.nextInt();
                            y = scan.nextInt();
                            board = new int[x][y];
                            for (int i = 0; i < board.length; i++) {
                                System.out.println(Arrays.toString(board[i]));
                            }
                            break;
                        case 2:
                            if (board == null) {
                                System.out.println("Has de crear un taulell");
                            } /*else {
                                System.out.println();
                            }*/
                            break;
                    }
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

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