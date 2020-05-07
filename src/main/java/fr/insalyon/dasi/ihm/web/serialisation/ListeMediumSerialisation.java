package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Astrologue;
import fr.insalyon.dasi.metier.modele.Cartomancien;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.Spirite;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DASI Team
 */
public class ListeMediumSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Medium> listMedium = (List<Medium>)request.getAttribute("medium");
        
        JsonArray container = new JsonArray();

        for(Medium m : listMedium){
            JsonObject jsonMedium = new JsonObject();
            jsonMedium.addProperty("id", m.getId());
            jsonMedium.addProperty("denomination", m.getDenomination());
            jsonMedium.addProperty("genre", m.getGenre());
            jsonMedium.addProperty("presentation", m.getPresentation());
            if(m instanceof Astrologue){
                jsonMedium.addProperty("type", "astrologue");
                jsonMedium.addProperty("formation", ((Astrologue) m).getFormation());
                jsonMedium.addProperty("promotion", ((Astrologue) m).getPromotion());   
            }else if(m instanceof Spirite){
                jsonMedium.addProperty("type", "spirite");
                jsonMedium.addProperty("support",((Spirite) m).getSupport());                
            }else{
                jsonMedium.addProperty("type","cartomancien");
            }
            container.add(jsonMedium);
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }

}
