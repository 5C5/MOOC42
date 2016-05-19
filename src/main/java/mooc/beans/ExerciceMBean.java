package mooc.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import mooc.dto.NotionDto;
import mooc.login.AbstractMBean;
import mooc.moteur.GenerateurFacile;
import mooc.service.NotionService;
import mooc.utils.Constants;
import mooc.utils.Messages;

import org.primefaces.model.diagram.DefaultDiagramModel;
import org.springframework.context.annotation.Scope;

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

	/** Elements/parametres a  recuperer */
	/** Liste des notions selectionnes pour l'exercice */
	private String[] selectedNotions;
	/** Niveau de l'exercice */
	private int niveau;
	/** Liste de notion */
	private List<NotionDto> notions;

	/** Parametres d'affichage */
	private boolean disabled;

	/** Elements a  afficher */
	/** Exercice */
	private DefaultDiagramModel root;
	/** Switch entre les portes */
	private String notionSwitch;
	/** Portes utilisees pour l'exercice */
	private List<String> notionsExercice;

	@PostConstruct
	public void init() {
		this.disabled = false;
		this.notions = this.notionService.getAll();
	}

	public void generer() {
		boolean check = this.checkBefore();
		if (!check) {
			// Si un controle n'est pas bon, on arrete
			return;
		}

		this.disabled = true;
		this.convertNotion();
		System.out.println("niveau : " + this.niveau);
		System.out.println("notions : " + this.selectedNotions.length);
		System.out.println("notion switch : " + this.notionSwitch);
		if (this.niveau == 1) {
			GenerateurFacile g = new GenerateurFacile();
			// List<String> l = new ArrayList<String>();
			// l.add("AND");
			// g.generer(l);
			g.generer(this.notionsExercice);
			this.root = g.getExercice();
		} else if (this.niveau == 2) {
			// TODO
		} else if (this.niveau == 3) {
			// TODO
		}
	}

	/**
	 * Faire les controles avant de lancer la generation de l'exercice
	 *
	 * @return true si tous les controles sont bons, sinon false
	 */
	private boolean checkBefore() {
		// TODO controles :
		// - un niveau doit etre sélectionne
		// - la liste des notions selectionnees doit avoir au moins deux elements
		if(this.niveau == 0){
			this.addFacesMessage(FacesMessage.SEVERITY_ERROR, Messages.message("exercice.erreur.niveau"));
			return false;
		} else {
			if(this.niveau == 1){
				// En niveau facile, on peut avoir une porte ou plus
				if(this.selectedNotions.length < 1){
					this.addFacesMessage(FacesMessage.SEVERITY_ERROR, Messages.message("exercice.erreur.niveau.une.porte", new Object[]{Constants.NIVEAU_FACILE}));
					return false;
				}
			} else{
				// Pour les autres niveaux, il faut au moins deux portes
				if(this.selectedNotions.length < 2) {
					this.addFacesMessage(FacesMessage.SEVERITY_ERROR, Messages.message("exercice.erreur.niveau.plus.porte",
							new Object[]{this.niveau==2 ? Constants.NIVEAU_MOYEN : Constants.NIVEAU_DIFFICILE}));
					return false;
				}
			}
		}
		return true;
	}

	private void convertNotion() {
		this.notionSwitch = "";
		this.notionsExercice = new ArrayList<String>();
		for (int i = 0; i < this.selectedNotions.length; i++) {
			if (i != this.selectedNotions.length - 1) {
				this.notionSwitch += this.notions.get(Integer.parseInt(this.selectedNotions[i])-1).getNom() + ";";
			} else {
				this.notionSwitch += this.notions.get(Integer.parseInt(this.selectedNotions[i])-1).getNom();
			}
			this.notionsExercice.add(this.notions.get(Integer.parseInt(this.selectedNotions[i])-1).getNom());
		}
	}

	public void onElementClicked() {
		System.out.println(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("elementId"));
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("elementId");

	}

	public DefaultDiagramModel getRoot() {
		return this.root;
	}

	public void setRoot(final DefaultDiagramModel root) {
		this.root = root;
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

	public String getNotionSwitch() {
		return this.notionSwitch;
	}

	public void setNotionSwitch(final String notionSwitch) {
		this.notionSwitch = notionSwitch;
	}

}
