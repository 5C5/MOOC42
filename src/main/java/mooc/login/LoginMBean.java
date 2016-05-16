package mooc.login;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mooc.service.ApprenantService;
import mooc.utils.Constants;
import mooc.utils.Messages;
import mooc.utils.StringUtil;


/**
 * Bean de connexion / deconnexion
 */
@NoArgsConstructor
@ManagedBean
// @SessionScoped
@Scope("view")
@Getter
@Setter
public class LoginMBean extends AbstractMBean implements Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = 5249149389582805694L;

    /** Service Apprenant */
    @ManagedProperty(value = "#{apprenantService}")
    private ApprenantService apprenantService;

    /** Identification */
    /** Nom */
    private String nom;
    /** Prenom */
    private String prenom;
    /** Mot de passe */
    private String motDePasse;

    /** Inscription */
    /** Nom */
    private String insNom;
    /** Prenom */
    private String insPrenom;
    /** Mot de passe */
    private String insMotDePasse;
    /** Confirmation mot de passe */
    private String insConfirmationMotDePasse;

    /** Donnees de connexion */
    /** Theme */
    private String theme = "sunny";
    /** Langue */
    private String langue; // TODO

    @PostConstruct
    public void init() {

        // String defaut = "sunny";
        // ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        // String param = ec.getInitParameter("primefaces.THEME");
        // System.out.println(param);
    }

    /**
     * Connexion d'un utilisateur
     *
     * @return Accueil si connexion, sinon meme page
     * @throws ServletException
     * @throws IOException
     */
    public String doLogin() throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        if (!StringUtil.isNotEmpty(this.nom) && !StringUtil.isNotEmpty(this.prenom)) {
            // Le nom et le prenom sont obligatoires
            this.addFacesMessage(FacesMessage.SEVERITY_ERROR, Messages.message("login.oblig.nom"));
            return "";
        }
        if (!StringUtil.isNotEmpty(this.motDePasse)) {
            // Le mot de passe est obligatoire
            this.addFacesMessage(FacesMessage.SEVERITY_ERROR, Messages.message("login.oblig.mdp"));
            return "";
        }

        int id = this.apprenantService.getIdApprenant(this.nom, this.prenom, this.motDePasse);

        if (id == -1) {
            this.addFacesMessage(FacesMessage.SEVERITY_ERROR, Messages.message("login.erreur"));
            return "";
        }

        this.setParamUtilisateur(request, id);
        return "accueil";
    }

    /**
     * Inscription d'un utilisateur
     *
     * @return Accueil si inscription, sinon meme page
     * @throws ServletException
     * @throws IOException
     */
    public String doRegister() throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        if (!StringUtil.isNotEmpty(this.insNom) && !StringUtil.isNotEmpty(this.insPrenom)) {
            // Le nom et le prenom sont obligatoires
            this.addFacesMessage(FacesMessage.SEVERITY_ERROR, Messages.message("register.oblig.nom"));
            return "";
        }
        if (!StringUtil.isNotEmpty(this.insMotDePasse) && !StringUtil.isNotEmpty(this.insConfirmationMotDePasse)) {
            // Le mot de passe est obligatoire
            this.addFacesMessage(FacesMessage.SEVERITY_ERROR, Messages.message("register.oblig.mdp"));
            return "";
        }
        if (!this.insMotDePasse.equals(this.insConfirmationMotDePasse)) {
            // Le mot de passe et la confirmation doivent etre identiques
            this.addFacesMessage(FacesMessage.SEVERITY_ERROR, Messages.message("register.erreur.password"));
            return "";
        }
        if (this.apprenantService.isApprenantExists(this.insNom, this.insPrenom)) {
            // L'apprenant existe deja en base
            this.addFacesMessage(FacesMessage.SEVERITY_ERROR, Messages.message("register.erreur.deja"));
            return "";
        }

        int id = this.apprenantService.inscrireApprenant(this.insNom, this.insPrenom, this.insMotDePasse);
        System.out.println(id);
        if (id == -1) {
            this.addFacesMessage(FacesMessage.SEVERITY_ERROR, Messages.message("register.erreur"));
            return "";
        }

        this.setParamUtilisateur(request, id);
        return "accueil";
    }

    /**
     * Changer les parametres de l'utilisateur
     *
     * @param request
     * @param id
     */
    private void setParamUtilisateur(final HttpServletRequest request, final int id) {
        request.getSession().setAttribute(Constants.UTILISATEUR_CONNECTE, id);
        this.langue = "fr";
        this.theme = this.apprenantService.getThemeById(id);
    }

    /**
     * Deconnexion d'un utilisateur
     *
     * @return Retour sur la page de connexion
     */
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index";
    }

	public ApprenantService getApprenantService() {
		return apprenantService;
	}

	public void setApprenantService(ApprenantService apprenantService) {
		this.apprenantService = apprenantService;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getInsNom() {
		return insNom;
	}

	public void setInsNom(String insNom) {
		this.insNom = insNom;
	}

	public String getInsPrenom() {
		return insPrenom;
	}

	public void setInsPrenom(String insPrenom) {
		this.insPrenom = insPrenom;
	}

	public String getInsMotDePasse() {
		return insMotDePasse;
	}

	public void setInsMotDePasse(String insMotDePasse) {
		this.insMotDePasse = insMotDePasse;
	}

	public String getInsConfirmationMotDePasse() {
		return insConfirmationMotDePasse;
	}

	public void setInsConfirmationMotDePasse(String insConfirmationMotDePasse) {
		this.insConfirmationMotDePasse = insConfirmationMotDePasse;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	@Override
	public String getLangue() {
		return langue;
	}

	public void setLangue(String langue) {
		this.langue = langue;
	}

}
