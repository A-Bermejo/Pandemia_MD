
package UF2;

import java.util.Scanner;

public class Pandemia_MD {
static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        Pandemia_MD solucio = new Pandemia_MD();
        Interficie.mostrarMissatge("BENVINGUTS AL NOSTRE PROGRAMA PANEDMIA_MD\n" +
                "Per començar hauràs de crear un taulell\n");
        solucio.inici();
    }
    
    /**
     * Programa principal cridat des del main de la classe. Mostra el menú
     * per pantalla i segons la opció triada executa una o altre instrucció fins
     * que aquesta opció sigui la de sortir.
     */
    public void inici(){
        String[] menu = {
                "MENÚ PRINCIPAL",
                "1. Carregar taulell", "2. Introduir malalts", "3. Transmitir virus",
                "4. Curar malalts", "5. Desplaçar malalts", "6. Mostrar informació","0. Sortir"
        };
        int opcio;
        GestorTaulell g = new GestorTaulell();
        int x = scan.nextInt();
        int y = scan.nextInt();
        float[][] taulell = new float[x][y];
        //g.carregarDades(taulell);
        do {
            Interficie.mostrarMenu(0);
            opcio = Utils.validarEnter("Tria una opció", "Error");
            switch(opcio) {
                case 1:
                    g.carregarDades(taulell);
                    break;
                case 2:
                    g.introduirMalalts(taulell);
                    break;
                case 6:
                    g.mostrarInformació(taulell);
                    Interficie.mostrarEstadistiques();
                    Interficie.mostrarTaulell(taulell);
                    break;
                case 0:
                    Interficie.mostrarMissatgeSortida("Adeu! Esperem veure't aviat! ^o^");
                    break;
            }
        } while (opcio!=0);
    }
}
