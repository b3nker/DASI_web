package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.service.ServiceAuthentification;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author benja
 */
public class InscriptionAction extends Action {
    @Override
    public void executer(HttpServletRequest request) {
        
        String nom = request.getParameter("name");
        String prenom = request.getParameter("firstName");
        String dateNaissance = request.getParameter("dateOfBirth");
        String adressePostale = request.getParameter("postalAdress");
        String numeroTelephone = request.getParameter("phoneNumber");
        String login = request.getParameter("email");
        String password = request.getParameter("password");
        
        
        SimpleDateFormat sd = new SimpleDateFormat("YYYY-MM-dd");
        Date d = sd.parse(dateNaissance, new ParsePosition(0));
        Client c = new Client(nom, prenom, d, adressePostale, numeroTelephone, login, password); 
        ServiceAuthentification serviceAuth = new ServiceAuthentification();
        Long id = serviceAuth.inscrireClient(c);

        request.setAttribute("id", id);
        
        // Gestion de la Session: ici, enregistrer l'ID du Client authentifié
        HttpSession session = request.getSession();
        if (id != null) {
            session.setAttribute("idClient", c.getId());
        }
        else {
            session.removeAttribute("idClient");
        }
       
    }
}
