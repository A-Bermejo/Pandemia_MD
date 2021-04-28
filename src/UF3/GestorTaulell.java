package UF3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

/**
 * En aquesta classe tenim les funcions que fan d'intermediaries entre el Taulell i l'usuari
 *
 * @author Daniel Lopez
 * @author Morel Luque
 */

public class GestorTaulell {
    /**
     * Posició bloquejada al taulell. També actua com límit d'aquest.
     */
    static Scanner scan = new Scanner(System.in);

    /**
     * Funció que serveix per carregar dades al taulell, tant si es vol crear un taulell aleatori com si es vol crear un amb mesures introduïdes per l'usuari.
     * Aquesta funció la utilitzem per crear el primer taulell però es pot tornar a utilitzar durant l'execució del programa.
     *
     * @param t Es passa el taulell de la classe "Taulell" perquè és on es guarda tota la informació del taulell que utilitzem.
     */
    public void carregarDades(Taulell t) {
        Interficie.mostrarMenu(1);
        int answerBoard = Utils.validarEnter("Introdueix un número de la llista", "No has introduït un caràcter númeric vàlid. Torna a provar.", 2, 1);
        int countBlockedPositions = (int) (Math.random() * 4);
        int x;
        int y;
        t.resetVariables();
        switch (answerBoard) {
            //Creació de taulell buit amb mesures especificades per l'usuari
            case 1 -> {
                Interficie.mostrarMissatge("Diga'm les files (X:) que té el taulell: ");
                x = (Utils.validarEnter("No has introduït un número de X: correcte. Introdueix com a mínim 2", "No has introduït un caràcter númeric vàlid. Torna a provar.", 0, 2));
                Interficie.mostrarMissatge("Diga'm les columnes (Y:) que té el taulell: ");
                y = ((Utils.validarEnter("No has introduït un número de Y: correcte. Introdueix com a mínim 2", "No has introduït un caràcter númeric vàlid. Torna a provar.", 0, 2)));
                t.createTaulellBuit(countBlockedPositions, x, y);
            }
            //Creació d'un taulell aleatori
            case 2 -> {
                x = (int) (Math.random() * 9 + 2);
                y = (int) (Math.random() * 9 + 2);
                t.createTaulellRand(countBlockedPositions, x, y);
                Interficie.mostrarMissatge("Es crearà un taulell amb les següents dimensions (x:" + t.getFiles() + " y:" + t.getColumnes() + ")");
            }
        }
        t.startTotalPatients();
        Interficie.mostrarMissatge("Total de posicions bloquejades: " + countBlockedPositions);
        Interficie.mostrarTaulell(t);
    }

    /**
     * Funció que ens permet introduïr malalts al nostre taulell i no només en una sola cel·la, sino que els podem repartir en varies cel·les.
     *
     * @param t Es passa el taulell de la classe "Taulell" perquè és on es guarda tota la informació del taulell que utilitzem.
     */
    public void introduirMalalts(Taulell t) {
        Interficie.mostrarMissatge("Quants malalts vols introduir: ");
        t.setPatients(Utils.validarEnter("Introdueix un número vàlid", "No has introduït un caràcter númeric vàlid. Torna a provar", 0, 0));
        t.setPositionPatients(0);
        Interficie.mostrarTaulell(t);
        int aux = 0;
        while (aux < t.getPatients()) {
            Interficie.mostrarMissatge("A quina fila (X:) vols introduir el/s malalt/s: ");
            int x = Utils.validarEnter("Indica una posició per X: que estigui dins del taulell", "No has introduït un caràcter numèric", t.getFiles(), 1) - 1;
            Interficie.mostrarMissatge("A quina columna (Y:) vols introduir el/s malalt/s: ");
            int y = Utils.validarEnter("Indica una posició per Y: que estigui dins del taulell", "No has introduït un caràcter numèric", t.getColumnes(), 1) - 1;
            Interficie.mostrarMissatge("Quants malalts hi ha en aquesta posició: ");
            switch (t.introduirMalalts(x,y,aux)) {
                case 1 -> Interficie.mostrarMissatgeError("No pots especificar més malalts en una posicio que el total" +
                        " de malalts que vols introduir.");
                case 2 -> Interficie.mostrarMissatgeError("Especifica una columna i fila existents en el taulell.");
                case 3 -> Interficie.mostrarMissatgeError("No pots introduir malalts en una posició bloquejada");
                case 0 -> Interficie.mostrarMissatge("S'han introduït els malalts correctament");
            }
            aux += t.getPositionPatients();
        }
    }

    /**
     * Funció que ens permet trasnmetre virus aplicant una taxa de contagi.
     *
     * @param t Es passa el taulell de la classe "Taulell" perquè és on es guarda tota la informació del taulell que utilitzem.
     */
    public void transmetreVirus(Taulell t) {
        Interficie.mostrarMissatge("Diga'm la taxa de contagi: ");
        float infectionRate = Utils.validarTaxaContagi("Indica un número vàlid", "No has introduït un caràcter numèric. Torna a provar", 0, 0);
        t.transmitirVirus(infectionRate);
    }

    /**
     * Funció que ens permet curar maltalts. Es pot fer de dues maneres: 1 - De forma global, 2 - A una casella completa. En ambdós casos es pot decidir si es vol curar amb % o amb un valor concret.
     *
     * @param t Es passa el taulell de la classe "Taulell" perquè és on es guarda tota la informació del taulell que utilitzem.
     */
    public void curarMalalts(Taulell t) {
        Interficie.mostrarMenu(2);
        int answerCure = Utils.validarEnter("Introdueix un número de la llista", "No has introduït un caràcter numèric vàlid. Torna a provar.", 2, 1);
        int answerCureValue;
        int cureNumber;
        int aux = (int) t.getCurrentPatients();
        switch (answerCure) {
            //Curar malalts de forma global
            case 1 -> {
                Interficie.mostrarMenu(21);
                answerCureValue = Utils.validarEnter("Introdueix un número de la llista", "No has introduït un caràcter numèric vàlid. Torna a provar.", 2, 1);
                switch (answerCureValue) {
                    //Curar malalts globalment introduïnt un percentatge
                    case 1 -> {
                        Interficie.mostrarMissatge("Quin percentatge de malalts vols curar (%): ");
                        cureNumber = Utils.validarEnter("Introdueix un valor entre 0 - 100", "No has introduït un valor numèric", 100, 0);
                        t.curarMalaltsPercentatgeGlobal(cureNumber);
                    }
                    //Curar malalts globalment introduïnt un valor concret
                    case 2 -> {
                        Interficie.mostrarMissatge("Quants malalts vols curar (valor concret): ");
                        cureNumber = scan.nextInt();
                        t.curarMalaltsValorConcretGlobal(cureNumber);

                    }
                }
            }
            //Curar malalts de forma individual
            case 2 -> {
                Interficie.mostrarTaulell(t);
                Interficie.mostrarMissatge("A quina fila (X:) vols curar els malalts: ");
                int x = Utils.validarEnter("Indica una posició per X: que estigui dins del taulell", "No has introduït un caràcter numèric", t.getFiles(), 1) - 1;
                Interficie.mostrarMissatge("A quina columna (Y:) vols curar els malalts: ");
                int y = Utils.validarEnter("Indica una posició per Y: que estigui dins del taulell", "No has introduït un caràcter numèric", t.getColumnes(), 1) - 1;
                if (t.getCasella(x, y) != t.getInvalidPosition()) {
                    Interficie.mostrarMenu(21);
                    answerCureValue = Utils.validarEnter("Introdueix un número de la llista", "No has introduït un caràcter númeric vàlid. Torna a provar.", 2, 1);
                    switch (answerCureValue) {
                        //Curar malalts de forma individual introduïnt percentatge
                        case 1 -> {
                            Interficie.mostrarMissatge("Quin percentatge de malalts vols curar (%): ");
                            cureNumber = Utils.validarEnter("Introdueix un valor entre 0 - 100", "No has introduït un valor numèric", 100, 0);
                            t.curarMalaltsPercentatgeVC(cureNumber, x, y);
                        }
                        //Curar malalts de forma individual introduïnt un valor concret
                        case 2 -> {
                            Interficie.mostrarMissatge("Quants malalts vols curar (valor concret): ");
                            cureNumber = scan.nextInt();
                            t.curarMalaltsValorConcret(cureNumber, x, y);
                        }
                    }
                } else {
                    Interficie.mostrarMissatgeError("No es pot curar una posició bloquejada");
                }
            }
        }
        t.setCurrentPatients(aux - (aux - (int) t.getCurrentPatients()));

    }

    /**
     * Funció que crida al Taullel per gestionar aquesta part del programa
     * @param t Es passa el taulell de la classe "Taulell" perquè és on es guarda tota la informació del taulell que utilitzem.
     */
    public void desplacarMalalts(Taulell t) {
        Interficie.mostrarTaulell(t);
        Interficie.mostrarMissatge("Indica la fila (X:) del malalt que vols desplaçar: ");
        int x = Utils.validarEnter("Indica una posició per X: que estigui dins del taulell", "No has introduït un caràcter numèric", t.getFiles(), 1) - 1;
        Interficie.mostrarMissatge("Indica la columna (Y:) del malalt que vols desplaçar: ");
        int y = Utils.validarEnter("Indica una posició per Y: que estigui dins del taulell", "No has introduït un caràcter numèric", t.getColumnes(), 1) - 1;
        if (t.getCasella(x, y) != t.getInvalidPosition()) {
            Interficie.mostrarMissatge("Quants malalts vols desplaçar?: ");
            t.setPatients(Utils.validarEnter("Introdueix un valor màxim del malalts de la casella", "No has introduït un caràcter numèric", (int) t.getCasella(x, y), 0));
            if (t.getPatients() <= t.getCasella(x, y)) {
                t.subtractCasella(x, y, t.getPatients());
                Interficie.mostrarMenuDesplacar();
                String answerDisplace = Utils.validarLletraCasella("Introdueix una lletra de la llista", "Has de introduir un caràcter valid.");
                boolean lockedPosition = t.validarCasellaDesti(x - 1, y - 1);
                if (lockedPosition) {
                    t.desplacarMalalts(lockedPosition, answerDisplace, x, y);
                } else {
                    Interficie.mostrarMissatgeError("No pots desplaçar els malalts a una posició bloquejada o fora del taulell");
                }


            } else {
                Interficie.mostrarMissatgeError("No pots introduïr un número superior als malalts que pertanyen a aquesta posició");
            }
        } else {
            Interficie.mostrarMissatgeError("No pots desplaçar posicions bloquejades");
        }
    }

    /**
     * Funció que mostra els taulells que tenim al fitxer i crida a la funció corresponent per importar el taulel
     * @param t Es passa el taulell de la classe "Taulell" perquè és on es guarda tota la informació del taulell que utilitzem.
     */
    public void llegirFitxer(Taulell t) {
        Interficie.mostrarMissatge("Ara es mostraran tots els taulells disponibles per importar amb les seves dimensions: ");
        try {
            File origin = new File("res/Taulells.txt");
            Scanner reader = new Scanner(origin);
            int i = 0;
            while (reader.hasNextLine()) {
                int nTaulellFile = reader.nextInt();
                reader.nextLine();
                reader.nextLine();
                int x = reader.nextInt();
                int y = reader.nextInt();
                reader.nextLine();
                reader.nextLine();
                i += 4;
                Interficie.mostrarMissatge("Taulell " + nTaulellFile + ": files(" + x + ") columnes(" + y + ")");
            }
            int nTaulell = Utils.validarEnter("Introdueix un número corresponent a un taulell", "Introdueix un caràcter númeric vàlid", i / 4, 1);
            t.llegirTaulell(nTaulell);
            reader.close();
        } catch (Exception e) {
            Interficie.mostrarMissatge(e.getMessage());
        }

    }

    /**
     * Funció que escriu les dades del taulell actual al fitxer
     * @param t Es passa el taulell de la classe "Taulell" perquè és on es guarda tota la informació del taulell que utilitzem.
     */
    public void escriureFitxer(Taulell t) {
        try {
            FileWriter desti = new FileWriter("res/Taulells.txt", true);
            Date data = new Date();
            File aux = new File("res/Taulells.txt");
            Scanner reader = new Scanner(aux);
            int i = 0;
            while (reader.hasNextLine()) {
                reader.nextLine();
                i++;
            }
            if (i / 4 + 1 <= 10) {
                reader.close();
                desti.append(i / 4 + 1 + " " + data.toString() + "\n"); //Id i Data
                desti.append(t.getCurrentPatients() + " " + t.getTotalPatients() + " " + t.getTotalCured() + "\n"); // Malalts actuals, malalts totals i curats totals
                desti.append(t.getFiles() + " " + t.getColumnes() + "\n"); // Dimensions taulell actual
                desti.append(t.taulellToString() + "\n"); // Taulell actual
                desti.close();
            } else {
                Interficie.mostrarMissatgeError("No es poden guardar més de 10 taulells.");
            }

        } catch (IOException e) {
            Interficie.mostrarMissatgeError(e.getMessage());
        }
    }
}