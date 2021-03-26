package UF2;

    /**
     * Aquesta classe és l'encarregada de mostrar tot el que es vol veure per consola
     * @author Daniel Lopez
     * @author Morel Luque
     */

public class Interficie {

    /**
     * Mostra per pantalla les diferents opcions que formen els menús.
     *
     * @param option Opció que utilitzem dins d'un "switch" per definir un menú o altre
     */
    public static void mostrarMenu(int option) {
        String menu = switch (option) {
            case 0 -> "MENÚ PRINCIPAL\n" +
                    "1. Carregar taulell   4. Curar malalts\n" +
                    "2. Introduir malalts  5. Desplaçar malalts\n" +
                    "3. Transmitir virus   6. Mostrar informació\n" +
                    "0. Sortir";
            case 1 -> "De quina manera vols crear el taulell?:\n" +
                    "1. Crear un taulell buit (introduint les files y columnes)\n" +
                    "2. Taulell amb malalts de forma aleatoria (minim 2 columnes i files)";
            case 2 -> "Introdueix de quina forma vols transmetre la teva cura:\n" +
                    "1. Globalment\n" +
                    "2. Per a una posició concreta";
            case 21 -> "Ho vols fer amb:\n" +
                    "1. Percentatge (%)\n" +
                    "2. Valor concret";
            default -> "";
        };
        System.out.println(Color.WHITE + menu);
    }

    /**
     * Funció que mostra el menú de desplaçament de la opció "Desplaçar malalts" del programa.
     */
    public static void mostrarMenuDesplacar() {
        Interficie.mostrarMissatge("De quina manera vols desplaçar els malalts?:\n" +
                Color.RED + "Q. (Dalt esquerra) " + Color.WHITE + "| " + Color.CYAN + "W. (Dalt mig) " + Color.WHITE + "| " + Color.RED + "E. (Dalt dreta)\n" +
                Color.BLUE + "A. (Esquerra mig)  " + Color.WHITE + "|               " + Color.WHITE + "| " + Color.BLUE + "D. (Dreta mig)\n" +
                Color.YELLOW + "Z. (Baix esquerra) " + Color.WHITE + "| " + Color.CYAN + "X. (Baix mig) " + Color.WHITE + "| " + Color.YELLOW + "C. (Baix dreta)");
    }

    /**
     * Funció que mostra el taulell formatat i personalitzat de la manera que nosaltres volem
     *
     * @param t Es pasa el taulell de la classe "Taulell" perquè és on es guarda tota la informació del taulell que utilitzem
     */
    public static void mostrarTaulell(Taulell t) {
        for (int i = 0; i < t.getFiles(); i++) {
            System.out.print(Color.BLUE + "| ");
            for (int j = 0; j < t.getColumnes(); j++) {
                if (t.getTaulell()[i][j] == GestorTaulell.INVALIDPOSITION) {
                    System.out.printf(Color.RED + "%4s    " + Color.BLUE + "| ", "X");
                } else {
                    System.out.printf(Color.GREEN + "%-7.0f " + Color.BLUE + "| ", Math.floor(t.getTaulell()[i][j]));
                }

            }
            System.out.print("\n");
        }
    }

    /**
     * Mostra per pantalla el missatge simple o normal que nosaltres li passem
     *
     * @param missatge Missatge a mostrar per pantalla
     */
    public static void mostrarMissatge(String missatge) {
        System.out.println(Color.WHITE + missatge);
    }

    /**
     * Mostra per pantalla el missatge de error que nosaltres li passem
     *
     * @param missatge Missatge a mostrar per pantalla
     */
    public static void mostrarMissatgeError(String missatge) {
        System.out.println(Color.YELLOW + missatge);
    }

    /**
     * Mostra per pantalla el missatge de sortida que nosaltres li passem
     */
    public static void mostrarMissatgeSortida() {
        System.out.println(Color.PURPLE + "Adeu! Esperem veure't aviat! ^o^");
    }

    /**
     * Funció que mostra les estadistiques respecte a la situació actual del taulell
     */
    public static void mostrarEstadistiques() {
        System.out.printf(Color.WHITE + "Número total de malalts: %.0f\n" +
                        "Número de persones curades: %d\n" +
                        "Percentatge que no ha complit confinament: %.0f%%\n"
                , GestorTaulell.totalPatients, GestorTaulell.totalCured, GestorTaulell.totalDisplaced * 100 / GestorTaulell.totalPatients);
    }

    /**
     * Funció que ens mostra la situació actual del taulell
     *
     * @param t Es pasa el taulell de la classe "Taulell" perquè és on es guarda tota la informació del taulell que utilitzem
     */
    public static void mostrarInformacio(Taulell t) {
        GestorTaulell.totalPatients = 0;
        for (int i = 0; i < t.getFiles(); i++) {
            for (int j = 0; j < t.getColumnes(); j++) {
                if (t.getTaulell()[i][j] != GestorTaulell.INVALIDPOSITION) {
                    GestorTaulell.totalPatients += t.getTaulell()[i][j];
                }
            }
        }
    }
}