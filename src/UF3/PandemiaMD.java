package UF3;

/**
 * Aquesta és la classe principal, on es crea el main i on es
 * crida a la resta de classes per fer les operacions necessàries.
 *
 * @author Daniel Lopez
 * @author Morel Luque
 */

public class PandemiaMD {
    /**
     * Donem la benvinguda al programa i cridem a la funció inici
     * que és la funció principal del programa.
     *
     * @param args No s'utilitza
     */
    public static void main(String[] args) {
        PandemiaMD solucio = new PandemiaMD();
        Interficie.mostrarMissatge("BENVINGUTS AL NOSTRE PROGRAMA PANEDMIA_MD\n" +
                "Per començar hauràs de crear un taulell\n");
        solucio.inici();
    }

    /**
     * Programa principal cridat des del main de la classe. Mostra el menú
     * per pantalla i segons la opció triada executa una o altre instrucció fins
     * que aquesta opció sigui la de sortir.
     */
    public void inici() {
        int opcio;
        Taulell t = new Taulell();
        GestorTaulell g = new GestorTaulell();
        g.carregarDades(t);
        do {
            Interficie.mostrarMenu(0);
            opcio = Utils.validarEnter("Tria una opció del menú", "No has introduït un caràcter númeric vàlid. Torna a provar", 8, 0);
            switch (opcio) {
                case 1 -> g.carregarDades(t);
                case 2 -> g.introduirMalalts(t);
                case 3 -> g.transmetreVirus(t);
                case 4 -> g.curarMalalts(t);
                case 5 -> g.desplacarMalalts(t);
                case 6 -> {
                    Interficie.mostrarEstadistiques(t);
                    Interficie.mostrarTaulell(t);
                }
                case 7 -> g.llegirFitxer(t);
                case 8 -> g.escriureFitxer(t);
                case 0 -> Interficie.mostrarMissatgeSortida();
            }
        } while (opcio != 0);
    }
}