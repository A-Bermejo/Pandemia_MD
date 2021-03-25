package UF2;

import java.util.Scanner;

/**
 *
 */

public class Utils {
    final private static Scanner S = new Scanner(System.in);
    private static boolean correcte;

    /**
     * Solicita un enter fins que sigui correcte
     * @param missatge Conté el text que es mostrarà per sol·licitar per
     * pantalla la introducció del número.
     * @param missatgeError Conté el text que es mostrarà a l'usuari en cas que
     * introdueixi un número buid o incorrecte
     * @return Valor enter que simbolitza el número que s'ha introduït per teclat
     */
    public static int validarEnterMenu(String missatge, String missatgeError,int max, int min){
        int ret = 0;
        do{
            correcte = S.hasNextInt();
            if(!correcte){
                S.next();
                Interficie.mostrarMissatgeError(missatgeError);
            }else{
                ret = S.nextInt();
                correcte = ret <= max && ret >= min;
                if (!correcte){
                    Interficie.mostrarMissatgeError(missatge);
                }
            }
        }while(!correcte);
        return ret;
    }

    public static boolean validarCasellaDesti(Taulell t, int x, int y, String missatgeError){
        if ((x != GestorTaulell.INVALIDPOSITION && y != GestorTaulell.INVALIDPOSITION) && (x != t.getFiles() && y != t.getColumnes()) && t.getTaulell()[x][y] != GestorTaulell.INVALIDPOSITION) {
            return true;
        }
        Interficie.mostrarMissatgeError(missatgeError);
        return false;
    }
    public static String validarLletraCasella(String missatge, String missatgeError){
        String ret = "";
        do{
            correcte = !S.hasNextInt();
            if(!correcte){
                S.next();
                Interficie.mostrarMissatgeError(missatgeError);
            }else{
                ret = String.valueOf(S.next().charAt(0)).toLowerCase();
                correcte = ret.matches("[qweadzxc]{1}");
                if (!correcte){
                    Interficie.mostrarMissatgeError(missatge);
                }
            }
        }while(!correcte);
        return ret;
    }

}
