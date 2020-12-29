package cat.sapa;

import java.util.Arrays;
import java.util.Scanner;

public class Pandemia {

    public static void main(String[] args) {
        int files = 0;
        int columnes = 0;
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
                    if (board == null){
                        System.out.print("No hi ha un taulell creat.\nDiga'm les mesures del taulell. Primer les files i després les columnes: ");
                        x = scan.nextInt();
                        y = scan.nextInt();
                        board = new int[x][y];
                        files = x;
                        columnes = y;
                        for (int i = 0; i < board.length; i++) {
                            System.out.println(Arrays.toString(board[i]));
                        }
                    }else{
                        System.out.println("Ja hi ha un taulell creat: ");
                        for (int i = 0; i < board.length; i++) {
                            System.out.println(Arrays.toString(board[i]));
                        }
                    }
                    break;
                case 2:
                    System.out.print("Quants malalts vols introduir: ");
                    int malalts = scan.nextInt();
                    for (int i = 0; i < malalts;) {
                        System.out.print("A quina fila i columna vols introduir el malalt: ");
                        x = scan.nextInt();
                        y = scan.nextInt();
                        System.out.print("Quants malalts hi han en aquesta posició: ");
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

                    break;
                case 4:

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