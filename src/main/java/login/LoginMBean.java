package login;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
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
public class LoginMBean extends AbstractMBean implements Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = 5249149389582805694L;

    /** Langue de l'utilisateur */
    private String langue;

    @PostConstruct
    public void init() {
        this.langue = this.getLangue();
    }

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
        // Langue par défaut fr (attention, fr ou en ou ... doivent être les noms donnés au fichier de properties (ex : fr.properties, en.properties)
        request.getSession().setAttribute(Constants.UTILISATEUR_LANGUE, "fr");
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
