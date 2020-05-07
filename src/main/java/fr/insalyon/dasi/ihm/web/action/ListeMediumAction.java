package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.service.ServiceConsultation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DASI Team
 */
public class ListeMediumAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        

        ServiceConsultation serviceAuth = new ServiceConsultation();
        
        request.setAttribute("medium", serviceAuth.listerMediums());
    }
    
}
