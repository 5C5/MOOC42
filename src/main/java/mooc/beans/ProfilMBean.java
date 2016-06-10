package mooc.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import mooc.dto.ApprenantNotionDto;
import mooc.dto.NiveauDeverouilleDto;
import mooc.dto.ProfilDto;
import mooc.login.AbstractMBean;
import mooc.moteur.Exercice;
import mooc.service.ApprenantService;
import mooc.service.ConnaissanceService;
import mooc.service.NotionService;
import mooc.utils.Constants;
import mooc.utils.Messages;
import mooc.utils.StringUtil;
import mooc.utils.Theme;

import org.springframework.context.annotation.Scope;

/**
 * Classe de gestion du profil
 *
 * @author colas
 *
 */
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

	/** Service Notion */
	@ManagedProperty(value = "#{notionService}")
	private NotionService notionService;

	/**
	 * Apprenant dont le profil est affiche.
	 */
	private ProfilDto profil;
	private boolean radmin = false;
	private boolean rapprenant = false;

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

	/** Tableau des niveaux de connaissances des apprenants (si admin) */
	private List<ApprenantNotionDto> lignes = new ArrayList<ApprenantNotionDto>();

	@PostConstruct
	public void init() {
		this.initTheme();

		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		try{
			int id = (int) request.getSession().getAttribute(Constants.UTILISATEUR_CONNECTE);
			this.profil = this.apprenantService.getProfil(id);
			if (this.profil != null) {
				this.radmin = this.profil.isAdmin();
				this.rapprenant = this.profil.isApprenant();
			}
		} catch(NullPointerException e){
			this.profil = null;
		}
		if (this.profil == null) {
			// Personne n'est connecte
			//			this.addFacesMessage(FacesMessage.SEVERITY_ERROR, Messages.message("profil.erreur.session"));
			return;
		}

		this.initTableauNiveau();

	}

	/**
	 * Charger le tableau des niveaux de connaissances des apprenants
	 */
	private void initTableauNiveau() {
		if (this.radmin) {
			this.lignes = new ArrayList<ApprenantNotionDto>();
			for (ProfilDto apprenant : this.profil.getListeProfil()) {
				ApprenantNotionDto ligne = new ApprenantNotionDto();
				ligne.setIdApprenant(apprenant.getId());
				ligne.setNomApprenant(apprenant.getNom());
				ligne.setPrenomApprenant(apprenant.getPrenom());
				for (NiveauDeverouilleDto niveau : apprenant.getNiveaux()) {
					if (Constants.OR.equalsIgnoreCase(niveau.getNom())) {
						ligne.setNiveauOU(niveau.getNiveau());
					} else if (Constants.AND.equalsIgnoreCase(niveau.getNom())) {
						ligne.setNiveauET(niveau.getNiveau());
					} else if (Constants.NOT.equalsIgnoreCase(niveau.getNom())) {
						ligne.setNiveauNOT(niveau.getNiveau());
					} else if (Constants.XOR.equalsIgnoreCase(niveau.getNom())) {
						ligne.setNiveauXOR(niveau.getNiveau());
					} else if (Constants.NAND.equalsIgnoreCase(niveau.getNom())) {
						ligne.setNiveauNAND(niveau.getNiveau());
					} else if (Constants.NOR.equalsIgnoreCase(niveau.getNom())) {
						ligne.setNiveauNOR(niveau.getNiveau());
					} else if (Constants.XNOR.equalsIgnoreCase(niveau.getNom())) {
						ligne.setNiveauXNOR(niveau.getNiveau());
					}
				}
				this.lignes.add(ligne);
			}
		}
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
	 * Methode appelee lorsque l'utilisateur modifie son mot de passe
	 */
	public void modifierMdp() {
		boolean check = true;

		if (!StringUtil.isNotEmpty(this.ancienMdp) && !StringUtil.isNotEmpty(this.nouveauMdp) && !StringUtil.isNotEmpty(this.confirmMdp)) {
			// Tous les champs sont obligatoires
			this.addFacesMessage(FacesMessage.SEVERITY_ERROR, Messages.message("profil.erreur.mdp.champs.obligatoire"));
			check = false;
		}

		if (!this.nouveauMdp.equals(this.confirmMdp)) {
			// Le mot de passe et la confirmation doivent etre identiques
			this.addFacesMessage(FacesMessage.SEVERITY_ERROR, Messages.message("profil.erreur.mdp.confirmation"));
			check = false;
		}

		if(check){
			int id = this.apprenantService.getIdApprenant(this.profil.getNom(), this.profil.getPrenom(), this.ancienMdp);
			if (id == -1) {
				this.addFacesMessage(FacesMessage.SEVERITY_ERROR, Messages.message("profil.erreur.mdp.ancien"));
				check = false;
			} else {
				// Si tous les controles sont bons, modification du mdp
				this.apprenantService.modifierMdp(this.profil.getId(), this.nouveauMdp);
			}
		}

	}

	/**
	 * Methode appelee pour monter le niveau d'une connaissance de l'apprenant
	 */
	public String levelUp() {
//		this.connaissanceService.changerNiveauConnaissance(this.idConnaissanceLevelUp, this.connaissanceLevel+1);

		// Mise à jour des niveaux
//		List<NiveauDeverouilleDto> niveauxUpdated = this.connaissanceService.reloadNiveau(this.profil.getId());
//		this.profil.setNiveaux(niveauxUpdated);
//		List<NiveauDeverouilleDto> niveauxUpdatedComplexes = this.connaissanceService.reloadNiveauComplexes(this.profil.getId());
//		this.profil.setNiveauxComplexes(niveauxUpdatedComplexes);

		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		request.getSession().removeAttribute(Constants.PRE_NIVEAU);
		request.getSession().removeAttribute(Constants.PRE_NOTION);
		request.getSession().setAttribute(Constants.PRE_NIVEAU, this.connaissanceLevel+1);
		request.getSession().setAttribute(Constants.PRE_NOTION, Integer.toString(this.idConnaissanceLevelUp));
		return "exercice";
	}

	public ApprenantService getApprenantService() {
		return this.apprenantService;
	}

	public void setApprenantService(final ApprenantService apprenantService) {
		this.apprenantService = apprenantService;
	}

	public ConnaissanceService getConnaissanceService() {
		return this.connaissanceService;
	}

	public void setConnaissanceService(final ConnaissanceService connaissanceService) {
		this.connaissanceService = connaissanceService;
	}

	public ProfilDto getProfil() {
		return this.profil;
	}

	public void setProfil(final ProfilDto profil) {
		this.profil = profil;
	}

	public List<Theme> getThemes() {
		return this.themes;
	}

	public void setThemes(final List<Theme> themes) {
		this.themes = themes;
	}

	public String getAncienMdp() {
		return this.ancienMdp;
	}

	public void setAncienMdp(final String ancienMdp) {
		this.ancienMdp = ancienMdp;
	}

	public String getNouveauMdp() {
		return this.nouveauMdp;
	}

	public void setNouveauMdp(final String nouveauMdp) {
		this.nouveauMdp = nouveauMdp;
	}

	public String getConfirmMdp() {
		return this.confirmMdp;
	}

	public void setConfirmMdp(final String confirmMdp) {
		this.confirmMdp = confirmMdp;
	}

	public int getIdConnaissanceLevelUp() {
		return this.idConnaissanceLevelUp;
	}

	public void setIdConnaissanceLevelUp(final int idConnaissanceLevelUp) {
		this.idConnaissanceLevelUp = idConnaissanceLevelUp;
	}

	public int getConnaissanceLevel() {
		return this.connaissanceLevel;
	}

	public void setConnaissanceLevel(final int connaissanceLevel) {
		this.connaissanceLevel = connaissanceLevel;
	}

	public boolean isRadmin() {
		return this.radmin;
	}

	public void setRadmin(final boolean radmin) {
		this.radmin = radmin;
	}

	public boolean isRapprenant() {
		return this.rapprenant;
	}

	public void setRapprenant(final boolean rapprenant) {
		this.rapprenant = rapprenant;
	}

	public NotionService getNotionService() {
		return this.notionService;
	}

	public void setNotionService(final NotionService notionService) {
		this.notionService = notionService;
	}

	public List<ApprenantNotionDto> getLignes() {
		return this.lignes;
	}

	public void setLignes(final List<ApprenantNotionDto> lignes) {
		this.lignes = lignes;
	}

}
