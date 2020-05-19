package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.service.ServiceAuthentification;
import fr.insalyon.dasi.metier.service.ServiceConsultation;
import java.util.List;
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
public class ConsultationAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {

        HttpSession session = request.getSession();
        ServiceAuthentification serviceAuth = new ServiceAuthentification();
        ServiceConsultation serviceConsul = new ServiceConsultation();
       
        Employe employe = serviceAuth.rechercherEmployeParId((Long) session.getAttribute("idEmploye"));
        Consultation consultation = serviceConsul.consultationEnCours(employe);
        if(consultation == null){
            request.setAttribute("estVide", true);
        }else{
            List<Consultation> consultationsClient =  serviceConsul.listeConsultationsClient(consultation.getClient());
            request.setAttribute("estVide", false);
            request.setAttribute("employe", employe);
            request.setAttribute("consultation", consultation);
            request.setAttribute("consultationsClient", consultationsClient);
            request.setAttribute("client", consultation.getClient());
            

        }
        
    }
}
