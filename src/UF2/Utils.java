package UF2;

import java.util.Scanner;

/**
 * Classe que serveix per validar les entrades que realitza
 * l'usuari al llarg del programa.
 * @author Daniel Lopez
 * @author Morel Luque
 */

public class Utils {
    final private static Scanner S = new Scanner(System.in);
    private static boolean correcte;

    /**
     * Solicita un enter fins que sigui correcte.
     *
     * @param missatge      Conté el text que es mostrarà per sol·licitar per
     *                      pantalla la introducció del número.
     * @param missatgeError Conté el text que es mostrarà a l'usuari en cas que
     *                      introdueixi un número incorrecte o un caràcter, segons la opció del programa.
     * @param max Valor que ocupa el rang màxim
     * @param min Valor que ocupa el rang mínim
     * @return Valor enter o caràcter (segons la opció del programa) que simbolitza el número o caràcter que s'ha introduït per teclat.
     */
    public static int validarEnter(String missatge, String missatgeError, int max, int min) {
        int ret = 0;
        do {
            correcte = S.hasNextInt();
            if (!correcte) {
                S.next();
                Interficie.mostrarMissatgeError(missatgeError);
            } else {
                ret = S.nextInt();
                correcte = ret <= max && ret >= min;
                if (!correcte) {
                    Interficie.mostrarMissatgeError(missatge);
                }
            }
        } while (!correcte);
        return ret;
    }

    /**
     * Funció que ens permet validar la casella de destí quan desplaçem un malalt.
     *
     * @param t             Es pasa el taulell de la classe "Taulell" perquè és on es guarda tota la informació del taulell que utilitzem.
     * @param x             Representa la fila a on es vol desplaçar el malalt.
     * @param y             Representa la columna a on es vol desplaçar el malalt.
     * @param missatgeError Conté el text que es mostrarà a l'usuari en cas
     *                      que introdueixi una posició que estigui fora del taulell o si està bloquejada.
     * @return Dona "true" o "false" depent si la comprovació que es fa sobre
     * si la posició a on es mou el malalt està fora del taulell, es una posició bloquejada o es una posició vàlida.
     */
    public static boolean validarCasellaDesti(Taulell t, int x, int y, String missatgeError) {
        if ((x != GestorTaulell.INVALIDPOSITION && y != GestorTaulell.INVALIDPOSITION) && (x != t.getFiles() && y != t.getColumnes()) && t.getTaulell()[x][y] != GestorTaulell.INVALIDPOSITION) {
            return true;
        }
        Interficie.mostrarMissatgeError(missatgeError);
        return false;
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
            correcte = !S.hasNextInt();
            if (!correcte) {
                S.next();
                Interficie.mostrarMissatgeError(missatgeError);
            } else {
                ret = String.valueOf(S.next().charAt(0)).toLowerCase();
                correcte = ret.matches("[qweadzxc]{1}");
                if (!correcte) {
                    Interficie.mostrarMissatgeError(missatge);
                }
            }
        } while (!correcte);
        return ret;
    }
}