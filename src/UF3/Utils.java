package UF3;

import java.util.Scanner;

/**
 * Classe que serveix per validar les entrades que realitza
 * l'usuari al llarg del programa.
 *
 * @author Daniel Lopez
 * @author Morel Luque
 */

public class Utils {
    static Scanner scan = new Scanner(System.in);
    /**
     * Per comprovar que el valor introduït és correcte.
     */
    private static boolean correcte;

    /**
     * Solicita un enter fins que sigui correcte.
     *
     * @param missatge      Conté el text que es mostrarà per sol·licitar per
     *                      pantalla la introducció del número.
     * @param missatgeError Conté el text que es mostrarà a l'usuari en cas que
     *                      introdueixi un número incorrecte o un caràcter, segons la opció del programa.
     * @param max           Valor que ocupa el rang màxim
     * @param min           Valor que ocupa el rang mínim
     * @return Valor enter o caràcter (segons la opció del programa) que simbolitza el número o caràcter que s'ha introduït per teclat.
     */
    public static int validarEnter(String missatge, String missatgeError, int max, int min) {
        int ret = 0;
        do {
            correcte = scan.hasNextInt();
            if (!correcte) {
                scan.next();
                Interficie.mostrarMissatgeError(missatgeError);
            } else {
                ret = scan.nextInt();
                if (max == 0) {
                    correcte = ret >= min;
                } else {
                    correcte = ret <= max && ret >= min;
                }
                if (!correcte) {
                    Interficie.mostrarMissatgeError(missatge);
                }
            }
        } while (!correcte);
        return ret;
    }

    /**
     * Solicita un float fins que sigui correcte.
     *
     * @param missatge      Conté el text que es mostrarà per sol·licitar per
     *                      pantalla la introducció del número.
     * @param missatgeError Conté el text que es mostrarà a l'usuari en cas que
     *                      introdueixi un número incorrecte o un caràcter, segons la opció del programa.
     * @param max           Valor que ocupa el rang màxim
     * @param min           Valor que ocupa el rang mínim
     * @return Valor amb decimals
     */
    public static float validarTaxaContagi(String missatge, String missatgeError, int max, int min) {
        float ret = 0;
        do {
            correcte = scan.hasNextFloat();
            if (!correcte) {
                scan.next();
                Interficie.mostrarMissatgeError(missatgeError);
            } else {
                ret = scan.nextFloat();
                if (max == 0) {
                    correcte = ret >= min;
                } else {
                    correcte = ret <= max && ret >= min;
                }
                if (!correcte) {
                    Interficie.mostrarMissatgeError(missatge);
                }
            }
        } while (!correcte);
        return ret;
    }

    /**
     * Funció que ens permet validar si la lletra que escriu l'usuari per moure malalts
     * no és un número i està dins de les lletres que es demanen.
     *
     * @param missatge      Conté el text que es mostrarà a l'usuari en cas que
     *                      introdueixi una lletra que no sigui una de les que es demanen
     * @param missatgeError Conté el text que es mostrarà a l'usuari en cas que
     *                      introdueixi un valor que no sigui una lletra
     * @return Caràcter que simbolitza el caràcter que s'ha introduït per teclat.
     */
    public static String validarLletraCasella(String missatge, String missatgeError) {
        String ret = "";
        do {
            correcte = !scan.hasNextInt();
            if (!correcte) {
                scan.next();
                Interficie.mostrarMissatgeError(missatgeError);
            } else {
                ret = String.valueOf(scan.next().charAt(0)).toLowerCase();
                correcte = ret.matches("[qweadzxc]");
                if (!correcte) {
                    Interficie.mostrarMissatgeError(missatge);
                }
            }
        } while (!correcte);
        return ret;
    }
}