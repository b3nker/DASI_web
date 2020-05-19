package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class ConsultationSerialisation extends Serialisation {
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Employe employe = (Employe) request.getAttribute("employe");
        Client client = (Client) request.getAttribute("client");
        List<Consultation> consultationsClient = (List<Consultation>) request.getAttribute("consultationsClient");
        Consultation consultation = (Consultation) request.getAttribute("consultation");
        
        
        JsonObject container = new JsonObject();
        JsonObject jsonProfilEmploye = new JsonObject();
        JsonObject jsonConsultation = new JsonObject();
        JsonObject jsonProfilClient = new JsonObject();
        JsonArray jsonConsultationsClient = new JsonArray();
        
        jsonProfilEmploye.addProperty("nom", employe.getNom());
        jsonProfilEmploye.addProperty("genre", employe.getGenre());
        
        /*
        A FINIR : JSon Object pour les objets consultation et profilClient
        */
        jsonConsultation.addProperty("")
        for(Consultation c : consultationsClient){
            JsonObject jsonC = new JsonObject();
            Date date = c.getHoraire().getTime();  
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
            String strDate = dateFormat.format(date);
            jsonC.addProperty("date", strDate);
            jsonC.addProperty("commentaire", c.getCommentaire());
            jsonC.addProperty("medium", c.getMedium().getDenomination());
            jsonC.addProperty("employe", c.getEmploye().getNom());
            jsonConsultationsClient.add(jsonC);
        }
        
        container.add("employe", jsonProfilEmploye);
        container.add("consultation", jsonConsultation);
        container.add("client", jsonProfilClient);
        container.add("consultationsClient", jsonConsultationsClient);
        
       
        
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(jsonProfilClient, out);
        out.close();
    }

    
}
