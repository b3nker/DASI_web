/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.ServiceAuthentification;
import fr.insalyon.dasi.metier.service.ServiceConsultation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author benja
 */
public class DemanderConsultationAction extends Action {
    @Override
    public void executer(HttpServletRequest request){
        HttpSession session = request.getSession();
        ServiceConsultation serviceConsul = new ServiceConsultation();
        
        Medium m = serviceConsul.rechercherMediumParId(Long.parseLong(request.getParameter("idMedium")));
        ServiceAuthentification serviceAuth = new ServiceAuthentification();
        Client client = serviceAuth.rechercherClientParId((Long) session.getAttribute("idClient"));
        
        if(client != null && m != null && serviceConsul.consultationEnCours(client) == null){
            if(serviceConsul.demandeConsultation(client,m)){
                request.setAttribute("succes","true");
            }else{
                request.setAttribute("succes","false");
            }
               
            
        }else{
            request.setAttribute("succes", "false");
        }

    }
}
