package UF2;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @org.junit.jupiter.api.Test
    void validarEnter() {
        assertEquals(0,Utils.validarEnter("Tria una opció del menú", "No has introduit un caràcter númeric vàlid. Torna a provar.",6,0));
    }

    @org.junit.jupiter.api.Test
    void validarCasellaDesti() {
        Taulell t = new Taulell(5,5);
        t.getTaulell()[0][0] = -1;
        assertTrue(Utils.validarCasellaDesti(t, 3, 4, "No pots desplaçar els malalts a una posició bloquejada o fora del taulell"));
        assertFalse(Utils.validarCasellaDesti(t, 0, 0, "No pots desplaçar els malalts a una posició bloquejada o fora del taulell"));
        assertFalse(Utils.validarCasellaDesti(t, 0, 5, "No pots desplaçar els malalts a una posició bloquejada o fora del taulell"));
    }

    @org.junit.jupiter.api.Test
    void validarLletraCasella() {

    }
}