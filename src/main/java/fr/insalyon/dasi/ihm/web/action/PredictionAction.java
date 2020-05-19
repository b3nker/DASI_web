/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.api.Prediction;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.ProfilAstral;
import fr.insalyon.dasi.metier.service.ServiceAuthentification;
import fr.insalyon.dasi.metier.service.ServiceConsultation;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author benja
 */
public class PredictionAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
         
        String idClient = request.getParameter("idClient");
        int amour = Integer.parseInt(request.getParameter("amour"));
        int sante = Integer.parseInt(request.getParameter("sante"));
        int travail = Integer.parseInt(request.getParameter("travail"));

        ServiceAuthentification serviceAuth = new ServiceAuthentification();
        Client client = serviceAuth.rechercherClientParId(Long.parseLong(idClient));
        ServiceConsultation serviceConsul = new ServiceConsultation();
        Prediction prediction = serviceConsul.obtenirPrediction(client.getProfilAstral(), amour, sante, travail);

        request.setAttribute("prediction", prediction);
    }    
}
