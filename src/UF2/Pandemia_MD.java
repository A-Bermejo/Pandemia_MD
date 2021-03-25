
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
        Taulell t = new Taulell();
        GestorTaulell g = new GestorTaulell();
        g.carregarDades(t);
        do {
            Interficie.mostrarMenu(0);
            opcio = Utils.validarEnterMenu("Tria una opció del menú", "No has introduit un caràcter númeric vàlid. Torna a provar.",6,0);
            switch(opcio) {
                case 1:
                    g.carregarDades(t);
                    break;
                case 2:
                    g.introduirMalalts(t.getTaulell());
                    break;
                case 3:
                    g.transmetreVirus(t);
                    break;
                case 4:
                    g.curarMalalts(t);
                    break;
                case 5:
                    g.desplacarMalalts(t);
                    break;
                case 6:
                    Interficie.mostrarInformacio(t);
                    Interficie.mostrarEstadistiques();
                    Interficie.mostrarTaulell(t);
                    break;
                case 0:
                    Interficie.mostrarMissatgeSortida();
                    break;
            }
        } while (opcio!=0);
    }
}
