package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Astrologue;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.ProfilAstral;
import fr.insalyon.dasi.metier.modele.Spirite;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author benja
 */
public class InfosClientSerialisation extends Serialisation{

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Client client = (Client) request.getAttribute("client");
        ProfilAstral pa = client.getProfilAstral();

        JsonObject jsonProfilClient = new JsonObject();

        jsonProfilClient.addProperty("nom", client.getNom());
        jsonProfilClient.addProperty("prenom", client.getPrenom());
        jsonProfilClient.addProperty("signeChinois", pa.getSigneChinois());
        jsonProfilClient.addProperty("signeZodiaque", pa.getSigneZodiaque());
        jsonProfilClient.addProperty("couleur", pa.getCouleur());
        jsonProfilClient.addProperty("animalTotem", pa.getAnimalTotem());
       
        
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(jsonProfilClient, out);
        out.close();
    }
}
