package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.service.ServiceAuthentification;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DASI Team
 */
public class InfosClientAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        HttpSession session = request.getSession();
        ServiceAuthentification serviceAuth = new ServiceAuthentification();
        Client client = serviceAuth.rechercherClientParId((Long) session.getAttribute("idClient"));
        request.setAttribute("client", client);
    }
    
}
