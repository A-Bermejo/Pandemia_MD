package cat.sapa;

import java.util.Scanner;

public class Pandemia {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String menu = ("1. Carregar taulell\n2. Introduir malalts\n3. Transmetre virus\n4. Curar malalts\n5. Desplaçar malalts\n6. Mostrar informació\n0. Sortir");
        System.out.println(menu);
        //Introduir resposta
        byte answer = scan.nextByte();
        switch (answer) {
            case 0:
                System.out.println("Adeu");
                break;
            case 1:

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
        }
    }
}