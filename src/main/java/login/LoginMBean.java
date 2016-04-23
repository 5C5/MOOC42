package login;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.context.annotation.Scope;

import utils.Constants;

/**
 * Bean de connexion / deconnexion
 */
@Data
@NoArgsConstructor
@ManagedBean
@Scope("view")
public class LoginMBean {

    /** serialVersionUID. */
    private static final long serialVersionUID = 5249149389582805694L;

    /**
     * Connexion d'un utilisateur
     *
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String doLogin() throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // TODO récupérer l'id de l'utilisateur connecté
        // TODO ajouter les messages, "Erreur de login", "Erreur lors de la connexion" (si probleme technique)
        request.getSession().setAttribute(Constants.UTILISATEUR_CONNECTE, "0");
        return "accueil";
    }

    /**
     * Déconnexion d'un utilisateur
     *
     * @return
     */
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        // envoi de l'information de déconnexion
        return "logout";
    }
}
