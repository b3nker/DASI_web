/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.service.ServiceAuthentification;
import fr.insalyon.dasi.metier.service.ServiceConsultation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author benja
 */
public class TerminerConsultationAction extends Action {
    @Override
    public void executer(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ServiceAuthentification serviceAuth = new ServiceAuthentification();
        ServiceConsultation serviceConsul = new ServiceConsultation();
       
        String commentaire = request.getParameter("commentaire");
        
        Employe employe = serviceAuth.rechercherEmployeParId((Long) session.getAttribute("idEmploye"));
        Consultation consultation = serviceConsul.consultationEnCours(employe);
        if( consultation != null && !consultation.isFinie()){
            serviceConsul.finConsultation(consultation, commentaire);
            request.setAttribute("succes", "true");
        }else{
            request.setAttribute("succes", "false");
        }
        
    }    
}
