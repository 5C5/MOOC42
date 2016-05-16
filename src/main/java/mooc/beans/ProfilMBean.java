package mooc.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;
import mooc.dto.NiveauDeverouilleDto;
import mooc.dto.ProfilDto;
import mooc.login.AbstractMBean;
import mooc.service.ApprenantService;
import mooc.service.ConnaissanceService;
import mooc.utils.Constants;
import mooc.utils.Theme;

import org.springframework.context.annotation.Scope;

/**
 * Classe de gestion du profil
 *
 * @author colas
 *
 */
// @Data
@Getter
@Setter
// @NoArgsConstructor
@ManagedBean
@Scope("view")
public class ProfilMBean extends AbstractMBean implements Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Service Apprenant */
    @ManagedProperty(value = "#{apprenantService}")
    private ApprenantService apprenantService;

    /** Service Connaissance */
    @ManagedProperty(value = "#{connaissanceService}")
    private ConnaissanceService connaissanceService;

    /**
     * Apprenant dont le profil est affiché.
     */
    private ProfilDto profil;

    /** Liste des themes */
    private List<Theme> themes;

    /** Modification du mot de passe */
    private String ancienMdp;
    private String nouveauMdp;
    private String confirmMdp;

    /** Id de la connaissance a monter de niveau */
    private int idConnaissanceLevelUp;
    /** Niveau actuel de la connaissance */
    private int connaissanceLevel;

    @PostConstruct
    public void init() {
        this.initTheme();

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        int id = (int) request.getSession().getAttribute(Constants.UTILISATEUR_CONNECTE);
        this.profil = this.apprenantService.getProfilApprenant(id);

        // TODO récupérer l'ID de l'apprenant, récupérer son profil en base
        // TODO récupérer la liste des compétences qu'a réalisé l'apprenant

    }

    /**
     * Charger la liste des themes
     */
    private void initTheme() {
        this.themes = new ArrayList<Theme>();
        this.themes.add(new Theme(0, "Afterdark", "afterdark"));
        this.themes.add(new Theme(1, "Afternoon", "afternoon"));
        this.themes.add(new Theme(2, "Afterwork", "afterwork"));
        this.themes.add(new Theme(3, "Aristo", "aristo"));
        this.themes.add(new Theme(4, "Black-Tie", "black-tie"));
        this.themes.add(new Theme(5, "Blitzer", "blitzer"));
        this.themes.add(new Theme(6, "Bluesky", "bluesky"));
        this.themes.add(new Theme(7, "Bootstrap", "bootstrap"));
        this.themes.add(new Theme(8, "Casablanca", "casablanca"));
        this.themes.add(new Theme(9, "Cupertino", "cupertino"));
        this.themes.add(new Theme(10, "Cruze", "cruze"));
        this.themes.add(new Theme(11, "Dark-Hive", "dark-hive"));
        this.themes.add(new Theme(12, "Delta", "delta"));
        this.themes.add(new Theme(13, "Dot-Luv", "dot-luv"));
        this.themes.add(new Theme(14, "Eggplant", "eggplant"));
        this.themes.add(new Theme(15, "Excite-Bike", "excite-bike"));
        this.themes.add(new Theme(16, "Flick", "flick"));
        this.themes.add(new Theme(17, "Glass-X", "glass-x"));
        this.themes.add(new Theme(18, "Home", "home"));
        this.themes.add(new Theme(19, "Hot-Sneaks", "hot-sneaks"));
        this.themes.add(new Theme(20, "Humanity", "humanity"));
        this.themes.add(new Theme(21, "Le-Frog", "le-frog"));
        this.themes.add(new Theme(22, "Midnight", "midnight"));
        this.themes.add(new Theme(23, "Mint-Choc", "mint-choc"));
        this.themes.add(new Theme(24, "Overcast", "overcast"));
        this.themes.add(new Theme(25, "Pepper-Grinder", "pepper-grinder"));
        this.themes.add(new Theme(26, "Redmond", "redmond"));
        this.themes.add(new Theme(27, "Rocket", "rocket"));
        this.themes.add(new Theme(28, "Sam", "sam"));
        this.themes.add(new Theme(29, "Smoothness", "smoothness"));
        this.themes.add(new Theme(30, "South-Street", "south-street"));
        this.themes.add(new Theme(31, "Start", "start"));
        this.themes.add(new Theme(32, "Sunny", "sunny"));
        this.themes.add(new Theme(33, "Swanky-Purse", "swanky-purse"));
        this.themes.add(new Theme(34, "Trontastic", "trontastic"));
        this.themes.add(new Theme(35, "UI-Darkness", "ui-darkness"));
        this.themes.add(new Theme(36, "UI-Lightness", "ui-lightness"));
        this.themes.add(new Theme(37, "Vader", "vader"));
    }

    /**
     * Méthode appelée lorsque l'utilisateur modifie son mot de passe
     */
    public void modifierMdp() {
        // TODO

    }

    /**
     * Méthode appelée pour monter le niveau d'une connaissance de l'apprenant
     */
    public void levelUp() {
        this.connaissanceService.changerNiveauConnaissance(this.idConnaissanceLevelUp, this.connaissanceLevel+1);
        
        // Mise à jour des niveaux
        List<NiveauDeverouilleDto> niveauxUpdated = this.connaissanceService.reloadNiveau(this.profil.getId());
        this.profil.setNiveaux(niveauxUpdated);
    }

}
