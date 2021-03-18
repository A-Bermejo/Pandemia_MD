
package projecte.uf2;

public class M3_UF2_ActAvaluable {

    
    public static void main(String[] args) {
        M3_UF2_ActAvaluable solucio = new M3_UF2_ActAvaluable();
        solucio.inici();
    }
    
    /**
     * Programa principal cridat des del main de la classe. Mostra el menú
     * per pantalla i segons la opció triada executa una o altre instrucció fins
     * que aquesta opció sigui la de sortir.
     */
    public void inici(){
        String[] menu = {
                "MENÚ",
                "1. XXX", "2. XXX", "3. XXX", "4. XXX", "5. XXX", "0. Sortir"
        };
        int opcio = 0;
        GestorTaulell g = new GestorTaulell();
        //Taulell d = new Taulell();
        float[][] taulell = new float[0][0];
        g.carregarDades(taulell);

        do {
            Interficie.mostrarMenu(menu);
            opcio = Utils.validarEnter("Tria una opció", "Error");
            switch(opcio) {
                case 1: break;
                case 2: break;
                case 0: break;
            }
        } while (opcio!=0);



    }
}
