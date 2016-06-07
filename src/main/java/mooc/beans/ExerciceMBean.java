package mooc.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;
import org.springframework.context.annotation.Scope;

import mooc.dto.NotionDto;
import mooc.login.AbstractMBean;
import mooc.moteur.Exercice;
import mooc.service.CompetenceService;
import mooc.service.NotionService;
import mooc.utils.Constants;
import mooc.utils.Messages;

@ManagedBean
@Scope("view")
public class ExerciceMBean extends AbstractMBean implements Serializable{

	/**
	 * ID
	 */
	private static final long serialVersionUID = -8124356110804029220L;

	/** Service Notion */
	@ManagedProperty(value = "#{notionService}")
	private NotionService notionService;

	/** Service Apprenant */
	@ManagedProperty(value = "#{competenceService}")
	private CompetenceService competenceService;

	/** Elements/parametres a� recuperer */
	/** Liste des notions selectionnes pour l'exercice */
	private String[] selectedNotions;
	/** Niveau de l'exercice */
	private int niveau;
	/** Type de l'exercice */
	private String type;
	/** Liste de notion */
	private List<NotionDto> notions;

	/** Parametres d'affichage */
	private boolean disabled;

	/** Elements a� afficher */
	/** Exercice */
	private Exercice exercice;
	// private DefaultDiagramModel root;
	/** Switch entre les portes */
	// private String notionSwitch;
	/** Portes utilisees pour l'exercice */
	// private List<String> notionsExercice;

	/** Utilisateur connecte ou non */
	private boolean utilConn;

	@PostConstruct
	public void init() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		this.exercice = (Exercice) request.getSession().getAttribute(Constants.EXERCICE);
		Integer id = (Integer) request.getSession().getAttribute(Constants.UTILISATEUR_CONNECTE);
		this.notions = this.notionService.getAll();
		if(id == null){
			this.utilConn = false;
			this.addFacesMessage(FacesMessage.SEVERITY_WARN, Messages.message("general.erreur.utilisateur"));
		} else {
			this.utilConn = true;
		}
		if (this.exercice == null) {
			this.disabled = false;
			this.niveau = 1;

		} else {
			this.disabled = true;
			int i = 0;
			this.selectedNotions = new String[this.exercice.getNotions().size()];
			for (NotionDto notion : this.exercice.getNotions()) {
				this.selectedNotions[i] = Integer.toString(notion.getId());
				i++;
			}
			this.niveau = this.exercice.getDifficulte();
			this.type = Integer.toString(this.exercice.getType());
		}
		this.notions = this.notionService.getAll();
	}

	public void generer() {
		boolean check = this.checkBefore();
		if (!check) {
			// Si un controle n'est pas bon, on arrete
			return;
		}

		this.exercice = new Exercice();
		this.disabled = true;
		this.exercice.setNotionSelected(this.selectedNotions, this.notions);
		this.exercice.setDifficulte(this.niveau);
		this.exercice.setType(Integer.parseInt(this.type));
		this.exercice.generer();
		System.out.println(this.exercice);
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		request.getSession().removeAttribute(Constants.EXERCICE);
		request.getSession().setAttribute(Constants.EXERCICE, this.exercice);
	}

	/**
	 * Faire les controles avant de lancer la generation de l'exercice
	 *
	 * @return true si tous les controles sont bons, sinon false
	 */
	private boolean checkBefore() {
		if(this.niveau == 0){
			// Un niveau doit etre selectionne
			this.addFacesMessage(FacesMessage.SEVERITY_ERROR, Messages.message("exercice.erreur.niveau"));
			return false;
		} else {
			if(this.niveau == 1){
				// En niveau facile, on peut avoir une porte ou plus
				if(this.selectedNotions.length < 1){
					this.addFacesMessage(FacesMessage.SEVERITY_ERROR, Messages.message("exercice.erreur.niveau.une.porte", new Object[]{Constants.NIVEAU_FACILE}));
					return false;
				}
			} else if(this.niveau == 2){
				// En niveau moyen, il faut au moins deux portes
				if(this.selectedNotions.length < 2) {
					this.addFacesMessage(FacesMessage.SEVERITY_ERROR, Messages.message("exercice.erreur.niveau.plus.porte", new Object[]{Constants.NIVEAU_MOYEN, "2"}));
					return false;
				}
			} else if(this.niveau == 3){
				// En niveau difficile, il faut au moins deux portes
				if(this.selectedNotions.length < 2) {
					this.addFacesMessage(FacesMessage.SEVERITY_ERROR, Messages.message("exercice.erreur.niveau.plus.porte", new Object[]{Constants.NIVEAU_DIFFICILE, "2"}));
					return false;
				}
			}
		}
		return true;
	}

	public void solutionRecalculer(final String idElement) {
		// Recuperation de l'exercice
		String form = "form_exer:diag-";
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		this.exercice = (Exercice) request.getSession().getAttribute(Constants.EXERCICE);
		// System.out.println(this.exercice);
		DefaultDiagramModel root = this.exercice.getRoot();

		// Mise a jour des valeurs lors d'un clic dessus
		for (Element el : root.getElements()) {
			if ((form + el.getId()).equals(idElement)) {
				if(!Constants.SORTIE_SOLUTION.equalsIgnoreCase(el.getStyleClass()) && !Constants.SORTIE_UTILISATEUR.equalsIgnoreCase(el.getStyleClass())){
					el.setData(this.exercice.switchData(el));
				}
			}
		}
		// Recalcule des solutions
		for (Element el : root.getElements()) {
			if(Constants.SORTIE_SOLUTION.equalsIgnoreCase(el.getStyleClass())){
				Boolean sortieSolution = this.exercice.calculSortieSolution(root);
				if(sortieSolution){
					el.setData("1");
				} else {
					el.setData("0");
				}
			} else if(Constants.SORTIE_UTILISATEUR.equalsIgnoreCase(el.getStyleClass())){
				try {
					Boolean sortieUtilisateur = this.exercice.calculSortieUtilisateur(root);
					if(sortieUtilisateur){
						el.setData("1");
					} else {
						el.setData("0");
					}
				} catch (Exception e){
					this.addFacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage());
				}
			}
		}

		// Modification de l'exercice
		this.exercice.setRoot(root);
		request.getSession().removeAttribute(Constants.EXERCICE);
		request.getSession().setAttribute(Constants.EXERCICE, this.exercice);
	}

	public void valider() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		Integer id = (Integer) request.getSession().getAttribute(Constants.UTILISATEUR_CONNECTE);
		this.exercice = (Exercice) request.getSession().getAttribute(Constants.EXERCICE);
		// Verification si la solution utilisateur est correcte
		boolean verif = this.exercice.valider(this.exercice.getRoot());
		RequestContext context = RequestContext.getCurrentInstance();
		// System.out.println("Au moins j'ai ça");

		if (verif) {
			if (id != null) {
				// Enregistrement de l'exercice pour l'apprenant
				this.competenceService.ajouterExercice(id, this.exercice.getNotions(), this.exercice.getDifficulte(), 0);
			}
			// this.reset();
			//this.addFacesMessage(FacesMessage.SEVERITY_INFO, Messages.message("exercice.reussi"));
			context.execute("PF('reussite').show();");

		} else {
			//this.addFacesMessage(FacesMessage.SEVERITY_ERROR, Messages.message("exercice.erreur.validation", new Object[] { verif }));

			context.execute("PF('echec').show();");
		}
	}

	public void reset(){
		this.exercice = null;
		this.disabled = false;
		this.niveau = 0;
		this.selectedNotions = new String[this.notions.size()];
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		request.getSession().removeAttribute(Constants.EXERCICE);
	}

	public void refermer() {
		this.reset();
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('reussite').hide();");
		System.out.println("Plop back");
	}

	public List<NotionDto> getNotions() {
		return this.notions;
	}

	public void setNotions(final List<NotionDto> notions) {
		this.notions = notions;
	}

	public String[] getSelectedNotions() {
		return this.selectedNotions;
	}

	public void setSelectedNotions(final String[] selectedNotions) {
		this.selectedNotions = selectedNotions;
	}

	public int getNiveau() {
		return this.niveau;
	}

	public void setNiveau(final int niveau) {
		this.niveau = niveau;
	}

	public NotionService getNotionService() {
		return this.notionService;
	}

	public void setNotionService(final NotionService notionService) {
		this.notionService = notionService;
	}

	public boolean isDisabled() {
		return this.disabled;
	}

	public void setDisabled(final boolean disabled) {
		this.disabled = disabled;
	}

	public Exercice getExercice() {
		return this.exercice;
	}

	public void setExercice(final Exercice exercice) {
		this.exercice = exercice;
	}

	public boolean isUtilConn() {
		return this.utilConn;
	}

	public void setUtilConn(final boolean utilConn) {
		this.utilConn = utilConn;
	}

	public CompetenceService getCompetenceService() {
		return this.competenceService;
	}

	public void setCompetenceService(final CompetenceService competenceService) {
		this.competenceService = competenceService;
	}

	public void updateType() {

	}

	public String getType() {
		return this.type;
	}

	public void setType(final String type) {
		this.type = type;
	}


}
