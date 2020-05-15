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
public class AuthentifierAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
         
        String login = request.getParameter("email");
        String password = request.getParameter("password");

        ServiceAuthentification serviceAuth = new ServiceAuthentification();
        Client client = serviceAuth.authentifierClient(login, password);

        request.setAttribute("client", client);
        
        // Gestion de la Session: ici, enregistrer l'ID du Client authentifié
        HttpSession session = request.getSession();
        if (client != null) {
            session.setAttribute("idClient", client.getId());
        }
        else {
            session.removeAttribute("idClient");
        }
        
        Employe employe = serviceAuth.authentifierEmploye(login, password);

        request.setAttribute("employe", employe);
        
        
        if (employe != null) {
            session.setAttribute("idEmploye", employe.getId());
        }
        else {
            session.removeAttribute("idEmploye");
        }
    }
    
}
