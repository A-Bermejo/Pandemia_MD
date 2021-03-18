package projecte.uf2;

import java.util.Scanner;

public class Utils {
    /**
     * Solicita un enter fins que sigui correcte
     * @param missatge Conté el text que es mostrarà per sol·licitar per
     * pantalla la introducció del número.
     * @param missatgeError Conté el text que es mostrarà a l'usuari en cas que
     * introdueixi un número buid o incorrecte
     * @return Valor enter que simbolitza el número que s'ha introduït per teclat
     */
    public static int validarEnter(String missatge, String missatgeError){
        Scanner scanner = new Scanner(System.in);
        int ret;
        boolean correcte;
        do{
            System.out.println(missatge);
            correcte=scanner.hasNextInt();
            if(!correcte){
                scanner.next();
                System.out.println(missatgeError);
            }
        }while(!correcte);
        ret = scanner.nextInt();
        scanner.nextLine();
        return ret;
    }
}
