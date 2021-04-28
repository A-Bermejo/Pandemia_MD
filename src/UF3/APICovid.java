package UF3;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

/**
 * Aquesta és la classe encarregada de gestionar la connexió amb l'API de Covid
 *
 * @author Daniel Lopez
 * @author Morel Luque
 */
public class APICovid {

    /**
     * Funció que ens retorna el JSONObject de Catalunya.
     * @param regionsSpain JSONObject que conté les regions d'Espanya
     * @return JSONObject que conté Catalunya
     * @throws Exception En el cas de que el programa no funcioni correctament és llençarà una Exception
     */
    public JSONObject queryAPICat(JSONArray regionsSpain) throws Exception {
        JSONObject cataluna = null;
        boolean isCataluna = false;
        for (int i = 0; i < regionsSpain.size() && !isCataluna; i++) {
            cataluna = (JSONObject) regionsSpain.get(i);
            if (cataluna.get("id").equals("cataluna")) {
                isCataluna = true;
            }
        }
        return cataluna;
    }

    /**
     * Funció que ens retorna el JSONObject de Girona.
     * @param regionsCataluna JSONObject que conté les regions d'Espanya
     * @return JSONObject que conté Girona
     * @throws Exception En el cas de que el programa no funcioni correctament és llençarà una Exception
     */
    public JSONObject queryAPIGir(JSONArray regionsCataluna) throws Exception {
        JSONObject girona = null;
        boolean isGirona = false;
        for (int i = 0; i < regionsCataluna.size() && !isGirona; i++) {
            girona = (JSONObject) regionsCataluna.get(i);
            if (girona.get("id").equals("gerona")) {
                isGirona = true;
            }
        }
        return girona;
    }

    /**
     * Funció que ens mostra totes les regions d'Espanya que no tenen morts en el dia d'ahir
     * @param regionsSpain JSONObject que conté les regions d'Espanya
     * @throws Exception En el cas de que el programa no funcioni correctament és llençarà una Exception
     */
    public void queryAPIRNoDeaths(JSONArray regionsSpain) throws Exception {
        ArrayList<String> regionsNoDeaths = new ArrayList<String>();
        for (int i = 0; i < regionsSpain.size(); i++) {
            JSONObject regionsAux = (JSONObject) regionsSpain.get(i);
            if (Integer.parseInt(regionsAux.get("today_new_deaths").toString()) == 0) {
                regionsNoDeaths.add(regionsAux.get("name").toString());
            }
        }
        regionsNoDeaths.sort(Comparator.naturalOrder());
        String text = "Regions d'Espanya amb 0 morts ahir: ";
        for (int i = 0; i < regionsNoDeaths.size(); i++) {
            text += "\n\t- " + regionsNoDeaths.get(i);
        }
        Interficie.mostrarMissatge(text);
    }

    /**
     * Funció que ens mostra totes les regions d'Espanya que tenen més de 10 morts en el dia d'ahir
     * @param regionsSpain
     * @throws Exception En el cas de que el programa no funcioni correctament és llençarà una Exception
     */
    public void queryAPIRDeaths(JSONArray regionsSpain) throws Exception {
        ArrayList<String> regionsSDeaths = new ArrayList<String>();
        for (int i = 0; i < regionsSpain.size(); i++) {
            JSONObject regionsAux = (JSONObject) regionsSpain.get(i);
            if (Integer.parseInt(regionsAux.get("today_new_deaths").toString()) > 10) {
                regionsSDeaths.add(regionsAux.get("name").toString());
            }
        }
        regionsSDeaths.sort(Comparator.naturalOrder());
        String text = "Regions d'Espanya amb més de 10 morts ahir: ";
        for (int i = 0; i < regionsSDeaths.size(); i++) {
            text += "\n\t- " + regionsSDeaths.get(i);
        }
        Interficie.mostrarMissatge(text);
    }

    /**
     * Funció que llegeix l'API i ens mostra els resultats segons l'opció escollida
     * @throws Exception En el cas de que el programa no funcioni correctament és llençarà una Exception
     */
    public void consultesAPICovid() throws Exception {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = LocalDate.of(today.getYear(), today.getMonth(), today.getDayOfMonth() - 1);
        URL dadesCovid = new URL("https://api.covid19tracking.narrativa.com/api/" + yesterday.toString());
        HttpURLConnection con = (HttpURLConnection) dadesCovid.openConnection();
        JSONParser parser = new JSONParser();
        JSONObject dataYesterday = (JSONObject) parser.parse(new InputStreamReader(con.getInputStream()));
        JSONObject dates = (JSONObject) dataYesterday.get("dates");
        JSONObject ahir = (JSONObject) dates.get(yesterday.toString());
        JSONObject countries = (JSONObject) ahir.get("countries");
        JSONObject spain = (JSONObject) countries.get("Spain");
        JSONArray regionsSpain = (JSONArray) spain.get("regions");
        JSONArray regionsCataluna = (JSONArray) queryAPICat(regionsSpain).get("sub_regions");
        Interficie.mostrarMissatge("Dades Covid dia: " + yesterday.toString());
        Interficie.mostrarMenu(9);
        int opcioCovid = Utils.validarEnter("Tria una opció del menú", "No has introduït un caràcter númeric vàlid. Torna a provar", 4, 0);
        while (opcioCovid != 0) {
            switch (opcioCovid) {
                case 1 -> Interficie.mostrarMissatge("Nous contagiats ahir a Catalunya: " + queryAPICat(regionsSpain).get("today_new_confirmed").toString());
                case 2 -> Interficie.mostrarMissatge("Nous contagiats ahir a Girona: " + queryAPIGir(regionsCataluna).get("today_new_confirmed").toString());
                case 3 -> queryAPIRNoDeaths(regionsSpain);
                case 4 -> queryAPIRDeaths(regionsSpain);
                case 0 -> Interficie.mostrarMissatge("Gràcies per consultar la API de \"Proyecto COVID-19\"");
            }
            Interficie.mostrarMissatge("Dades Covid dia: " + yesterday.toString());
            Interficie.mostrarMenu(9);
            opcioCovid = Utils.validarEnter("Tria una opció del menú", "No has introduït un caràcter númeric vàlid. Torna a provar", 4, 0);
        }
    }
}
