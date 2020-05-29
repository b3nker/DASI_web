package fr.insalyon.dasi.ihm.web;

import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.ihm.web.action.Action;
import fr.insalyon.dasi.ihm.web.action.AuthentifierAction;
import fr.insalyon.dasi.ihm.web.action.InscriptionAction;
import fr.insalyon.dasi.ihm.web.action.ListeMediumAction;
import fr.insalyon.dasi.ihm.web.action.InfosClientAction;
import fr.insalyon.dasi.ihm.web.action.ConsultationAction;
import fr.insalyon.dasi.ihm.web.action.DeconnexionAction;
import fr.insalyon.dasi.ihm.web.action.DemanderConsultationAction;
import fr.insalyon.dasi.ihm.web.action.PredictionAction;
import fr.insalyon.dasi.ihm.web.action.TerminerConsultationAction;
import fr.insalyon.dasi.ihm.web.serialisation.ListeMediumSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.ConnexionSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.ConsultationSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.DeconnexionSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.DemanderConsultationSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.InscriptionSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.Serialisation;
import fr.insalyon.dasi.ihm.web.serialisation.InfosClientSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.PredictionSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.TerminerConsultationSerialisation;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DASI Team
 */
@WebServlet(name = "ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        JpaUtil.init();
    }

    @Override
    public void destroy() {
        JpaUtil.destroy();
        super.destroy();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        request.setCharacterEncoding("UTF-8");

        String todo = request.getParameter("todo");

        Action action = null;
        Serialisation serialisation = null;

        if (todo != null) {
            switch (todo) {
                case "inscrire":
                    action = new InscriptionAction();
                    serialisation = new InscriptionSerialisation();
                    break;
                case "connecter":
                    action = new AuthentifierAction();
                    serialisation = new ConnexionSerialisation();
                    break;
                case "demanderMedium":
                    action = new ListeMediumAction();
                    serialisation = new ListeMediumSerialisation();
                    break;
                case "infosClient":
                    action = new InfosClientAction();
                    serialisation = new InfosClientSerialisation();
                    break;
                case "infosEmploye":
                    action = new ConsultationAction();
                    serialisation = new ConsultationSerialisation();
                    break;
                case "deconnexion":
                    action = new DeconnexionAction();
                    serialisation = new DeconnexionSerialisation();
                    break;
                case "terminerConsultation":
                    action = new TerminerConsultationAction ();
                    serialisation = new TerminerConsultationSerialisation();
                    break;
                case "demanderConsultation":
                    action = new DemanderConsultationAction();
                    serialisation = new DemanderConsultationSerialisation();
                    break;
                case "obtenirPredictions":
                    action = new PredictionAction();
                    serialisation= new PredictionSerialisation();
                    break;
            }
        }
        
        if (action != null) {
            action.executer(request);
            serialisation.serialiser(request, response);
        }
        else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erreur dans les paramètres de la requête");
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
