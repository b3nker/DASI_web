package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Employe;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DASI Team
 */
public class ProfilClientSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Client client = (Client)request.getAttribute("client");
        Employe employe = (Employe)request.getAttribute("employe");
        
        JsonObject container = new JsonObject();

        Boolean connexion = (client != null || employe != null);
        container.addProperty("connexion", connexion);

        if (client != null) {
            JsonObject jsonClient = new JsonObject();
            jsonClient.addProperty("id", client.getId());
            jsonClient.addProperty("nom", client.getNom());
            jsonClient.addProperty("prenom", client.getPrenom());
            jsonClient.addProperty("mail", client.getAdresseEmail());

            container.add("client", jsonClient);
        } else if (employe != null) {
            JsonObject jsonEmploye = new JsonObject();
            jsonEmploye.addProperty("id", employe.getId());
            jsonEmploye.addProperty("nom", employe.getNom());
            jsonEmploye.addProperty("numero de telephone", employe.getNumeroTelephone());
            jsonEmploye.addProperty("genre", employe.getGenre());
            
            container.add("employe", jsonEmploye);
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }

}
