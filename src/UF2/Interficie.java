package UF2;


public class Interficie {
    enum colors{
        RED,
        GREEN,
        YELLOW,
        BLUE,
        PURPLE,
        CYAN,
        WHITE
    }

    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";
    private static final String WHITE = "\u001B[38m";

    /**
     * Mostra la informació d'un array per pantalla de forma simple
     *
     * @param dades Array d'string amb informació
     */
    public static void mostrarDades(String[] dades) {

    }

    /**
     * Mostra per pantalla les diferents opcions que formen el menú
     * que es rep com a paràmetre. La primera posició de l'array menú és el
     * títol del mateix mentre que la resta són les opcions. Aquesta variable
     * ja ve inicialitzada des del codi que en fa la crida.
     *
     */
    public static void mostrarMenu(int opcion) {
        String menu = "";
        switch (opcion) {
            case 0:
                menu = "MENÚ PRINCIPAL\n" +
                        "1. Carregar taulell   4. Curar malalts\n" +
                        "2. Introduir malalts  5. Desplaçar malalts\n" +
                        "3. Transmitir virus   6. Mostrar informació\n" +
                        "0. Sortir";
                break;
            case 1:
                menu = "De quina manera vols crear el taulell?:\n" +
                        "1. Crear un taulell buit (introduint les files y columnes)\n" +
                        "2. Taulell amb malalts de forma aleatoria (minim 2 columnes i files)";
                break;
            case 2:
                menu = "Introdueix de quina forma vols transmetre la teva cura:\n" +
                        "1. Globalment\n" +
                        "2. Per a una posició concreta";
                break;
            case 21:
                menu = "Ho vols fer amb:\n" +
                        "1. Percentatge (%)\n" +
                        "2. Valor concret";
                break;

        }
        System.out.println(WHITE + menu);
    }
    public static void mostrarMenuDesplacar(){
        System.out.println("De quina manera vols desplaçar els malalts?:\n" +
                RED + "Q. (Dalt esquerra) " + WHITE + "| " + CYAN + "W. (Dalt mig) " + WHITE + "| " + RED + "E. (Dalt dreta)\n" +
                BLUE + "A. (Esquerra mig)  " + WHITE + "|               " + WHITE + "| " + BLUE + "D. (Dreta mig)\n" +
                YELLOW + "Z. (Baix esquerra) " + WHITE + "| " + CYAN + "X. (Baix mig) " + WHITE + "| " + YELLOW + "C. (Baix dreta)");
    }

    public static void mostrarTaulell(Taulell t) {
        for (int i = 0; i < t.getFiles(); i++) {
            System.out.print(BLUE + "| ");
            for (int j = 0; j < t.getColumnes(); j++) {
                if (t.getTaulell()[i][j] == GestorTaulell.INVALIDPOSITION) {
                    System.out.printf(RED + "%4s    " + BLUE + "| ", "X");
                } else {
                    System.out.printf(GREEN + "%-7.0f " + BLUE + "| ", Math.floor(t.getTaulell()[i][j]));
                }

            }
            System.out.print("\n");
        }
    }

    /**
     * Mostra la informació per pantalla de forma simple
     *
     * @param missatge Missatge a mostrar per pantalla
     */
    public static void mostrarMissatge(String missatge) {
        System.out.println(WHITE + missatge);
    }

    public static void mostrarMissatgeError(String missatge) {
        System.out.println(YELLOW + missatge);
    }

    public static void mostrarMissatgeSortida() { System.out.println(PURPLE + "Adeu! Esperem veure't aviat! ^o^");
    }

    public static void mostrarEstadistiques() {
        System.out.printf(WHITE + "Número total de malalts: %.0f\n" +
                        "Número de persones curades: %d\n" +
                        "Percentatge que no ha complit confinament: %.0f%%\n"
                , GestorTaulell.totalPatients, GestorTaulell.totalCured, GestorTaulell.totalDisplaced * 100 / GestorTaulell.totalPatients);
    }

    /**
     * Mostra la informació per pantalla en format capçalera
     *
     * @param missatge Missatge a mostrar per pantalla en format capçalera
     */
}
