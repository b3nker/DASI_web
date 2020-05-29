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
        
        Boolean estVide = Boolean.parseBoolean((String)request.getAttribute("estVide"));
        JsonObject container = new JsonObject();
        if(!estVide){
            Employe employe = (Employe) request.getAttribute("employe");
            Client client = (Client) request.getAttribute("client");
            List<Consultation> consultationsClient = (List<Consultation>) request.getAttribute("consultationsClient");
            Consultation consultation = (Consultation) request.getAttribute("consultation");
            Date date;
            DateFormat dateFormat;
            String strDate;

            JsonObject jsonMedium = new JsonObject();
            JsonObject jsonProfilEmploye = new JsonObject();
            JsonObject jsonConsultation = new JsonObject();
            JsonObject jsonProfilClient = new JsonObject();
            JsonArray jsonConsultationsClient = new JsonArray();
            //Json Object : Medium
            jsonMedium.addProperty("id", consultation.getMedium().getId());
            jsonMedium.addProperty("genre", consultation.getMedium().getGenre());
            jsonMedium.addProperty("denomination", consultation.getMedium().getDenomination());
            jsonMedium.addProperty("presentation", consultation.getMedium().getPresentation());
            
            //Json Object : Employe
            jsonProfilEmploye.addProperty("nom", employe.getNom());
            jsonProfilEmploye.addProperty("genre", employe.getGenre());
            //Json Object : ProfilClient
            jsonProfilClient.addProperty("id", client.getId());
            jsonProfilClient.addProperty("prenom", client.getPrenom());
            jsonProfilClient.addProperty("nom", client.getNom());
            jsonProfilClient.addProperty("PA_AnimalTotem", client.getProfilAstral().getAnimalTotem());
            jsonProfilClient.addProperty("PA_Couleur", client.getProfilAstral().getCouleur());
            jsonProfilClient.addProperty("PA_SigneChinois", client.getProfilAstral().getSigneChinois());
            jsonProfilClient.addProperty("PA_SigneZodiaque", client.getProfilAstral().getSigneZodiaque());
            //Json Object : Consultation
            jsonConsultation.addProperty("id", consultation.getId());
            date = consultation.getHoraire().getTime();  
            dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            strDate = dateFormat.format(date);
            jsonConsultation.addProperty("horaire", strDate);
            jsonConsultation.add("medium", jsonMedium);
            //Json Object : List<Consultation>        
            for(Consultation c : consultationsClient){
                JsonObject jsonC = new JsonObject();
                date = c.getHoraire().getTime();  
                dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
                strDate = dateFormat.format(date);
                jsonC.addProperty("date", strDate);
                jsonC.addProperty("commentaire", c.getCommentaire());
                jsonC.addProperty("medium", c.getMedium().getDenomination());
                jsonC.addProperty("employe", c.getEmploye().getNom());
                jsonConsultationsClient.add(jsonC);
            }



            container.addProperty("succes", true);
            container.add("employe", jsonProfilEmploye);
            container.add("consultation", jsonConsultation);
            container.add("client", jsonProfilClient);
            container.add("consultationsClient", jsonConsultationsClient);
        }else{
            container.addProperty("succes", false);
        }
        
       
        
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }

    
}
