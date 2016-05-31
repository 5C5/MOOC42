package mooc.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import mooc.dto.NotionDto;
import mooc.login.AbstractMBean;
import mooc.moteur.Exercice;
import mooc.service.CompetenceService;
import mooc.service.NotionService;
import mooc.utils.Constants;
import mooc.utils.Messages;

import org.primefaces.context.RequestContext;
import org.primefaces.event.diagram.ConnectEvent;
import org.primefaces.event.diagram.ConnectionChangeEvent;
import org.primefaces.event.diagram.DisconnectEvent;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;
import org.springframework.context.annotation.Scope;

@ManagedBean
@Scope("view")
public class BacSableMBean extends AbstractMBean implements Serializable{

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

	/** Liste de notion */
	private List<NotionDto> notions;

	/** Exercice */
	private Exercice exercice;

	/** Parametrage */
	/** Nombre d'entree */
	private Integer nbEntree;
	/** Nombre de porte unaire */
	private Integer nbPorteUnaire;
	/** Nombre de porte binaire */
	private Integer nbPorteBinaire;
	/** Parametres d'affichage */
	private boolean disabled;

	/** Utilisateur connecte ou non */
	private boolean utilConn;

	@PostConstruct
	public void init() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		this.exercice = (Exercice) request.getSession().getAttribute(Constants.BAC_SABLE);
		Integer id = (Integer) request.getSession().getAttribute(Constants.UTILISATEUR_CONNECTE);
		if(id == null){
			this.utilConn = false;
			this.addFacesMessage(FacesMessage.SEVERITY_WARN, Messages.message("general.erreur.utilisateur"));
		} else {
			this.utilConn = true;
		}
		if (this.exercice == null) {
			this.disabled = false;
		} else {
			this.disabled = true;
		}
		this.notions = this.notionService.getAll();
	}

	public void generer() {
		this.exercice = new Exercice();
		this.exercice.setNotions(this.notions);
		this.exercice.setDifficulte(0);
		this.exercice.generer(this.nbEntree, this.nbPorteUnaire, this.nbPorteBinaire);
		System.out.println(this.exercice);
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		request.getSession().removeAttribute(Constants.BAC_SABLE);
		request.getSession().setAttribute(Constants.BAC_SABLE, this.exercice);
	}


	public void solutionRecalculer(final String idElement) {
		// Recuperation de l'exercice
		String form = "form_exer:diag-";
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		this.exercice = (Exercice) request.getSession().getAttribute(Constants.BAC_SABLE);
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
				Boolean sortieUtilisateur = this.exercice.calculSortieUtilisateur(root);
				if(sortieUtilisateur){
					el.setData("1");
				} else {
					el.setData("0");
				}
			}
		}

		// Modification de l'exercice
		this.exercice.setRoot(root);
		request.getSession().removeAttribute(Constants.BAC_SABLE);
		request.getSession().setAttribute(Constants.BAC_SABLE, this.exercice);
	}

	public void reset(){
		this.exercice = null;
		this.nbEntree = null;
		this.nbPorteUnaire = null;
		this.nbPorteBinaire = null;
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		request.getSession().removeAttribute(Constants.BAC_SABLE);
	}

	private boolean suspendEvent;
	public void onConnect(final ConnectEvent event) {
		if (!this.suspendEvent) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Connected", "From " + event.getSourceElement().getData()
					+ " To " + event.getTargetElement().getData());

			FacesContext.getCurrentInstance().addMessage(null, msg);

			RequestContext.getCurrentInstance().update("form:msgs");
		} else {
			this.suspendEvent = false;
		}
	}

	public void onDisconnect(final DisconnectEvent event) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Disconnected", "From " + event.getSourceElement().getData()
				+ " To " + event.getTargetElement().getData());

		FacesContext.getCurrentInstance().addMessage(null, msg);

		RequestContext.getCurrentInstance().update("form:msgs");
	}

	public void onConnectionChange(final ConnectionChangeEvent event) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Connection Changed", "Original Source:"
						+ event.getOriginalSourceElement().getData()
						+ ", New Source: "
						+ event.getNewSourceElement().getData()
						+ ", Original Target: "
						+ event.getOriginalTargetElement().getData()
						+ ", New Target: "
						+ event.getNewTargetElement().getData());

		FacesContext.getCurrentInstance().addMessage(null, msg);

		RequestContext.getCurrentInstance().update("form:msgs");
		this.suspendEvent = true;
	}

	public List<NotionDto> getNotions() {
		return this.notions;
	}

	public void setNotions(final List<NotionDto> notions) {
		this.notions = notions;
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

	public Integer getNbEntree() {
		return this.nbEntree;
	}

	public void setNbEntree(final Integer nbEntree) {
		this.nbEntree = nbEntree;
	}

	public Integer getNbPorteUnaire() {
		return this.nbPorteUnaire;
	}

	public void setNbPorteUnaire(final Integer nbPorteUnaire) {
		this.nbPorteUnaire = nbPorteUnaire;
	}

	public Integer getNbPorteBinaire() {
		return this.nbPorteBinaire;
	}

	public void setNbPorteBinaire(final Integer nbPorteBinaire) {
		this.nbPorteBinaire = nbPorteBinaire;
	}


}
