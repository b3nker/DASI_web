/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.service.ServiceAuthentification;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author benja
 */
public class DeconnexionAction extends Action {
    @Override
    public void executer(HttpServletRequest request) {
         
        request.setAttribute("estDeconnecte", "false");

        
        HttpSession session = request.getSession();
        if (session.getAttribute("idClient") !=null) {
            session.removeAttribute("idClient");
            request.setAttribute("estDeconnecte", "true");

        }
        if (session.getAttribute("idEmploye") !=null) {
            session.removeAttribute("idEmploye");
            request.setAttribute("estDeconnecte", "true");

        }       
    }
}
