
package UF2;

    /**
     * Aquesta és la classe principal, on es crea el main i on es
     * crida a la resta de classes per fer les operacions necessàries.
     * @author Daniel Lopez & Morel Luque
     */

public class Pandemia_MD {
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
        int opcio;
        Taulell t = new Taulell();
        GestorTaulell g = new GestorTaulell();
        g.carregarDades(t);
        do {
            Interficie.mostrarMenu(0);
            opcio = Utils.validarEnter("Tria una opció del menú", "No has introduit un caràcter númeric vàlid. Torna a provar.",6,0);
            switch(opcio) {
                case 1:
                    g.carregarDades(t);
                    break;
                case 2:
                    g.introduirMalalts(t);
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