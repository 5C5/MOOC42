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

import mooc.dto.NotionDto;
import mooc.login.AbstractMBean;
import mooc.moteur.GenerateurFacile;
import mooc.service.NotionService;
import mooc.utils.Constants;
import mooc.utils.Messages;

import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;
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
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		this.root = (DefaultDiagramModel) request.getSession().getAttribute(Constants.EXERCICE);
		if(this.root == null){
			this.disabled = false;
		} else {
			this.disabled = true;
		}
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
			g.generer(this.notionsExercice, false);
			this.root = g.getExercice();
		} else if (this.niveau == 2) {
			// TODO
		} else if (this.niveau == 3) {
			// TODO
		}
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		request.getSession().removeAttribute(Constants.EXERCICE);
		request.getSession().setAttribute(Constants.EXERCICE, this.root);
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

	public void solutionRecalculer() {
		// Inutile ?
		System.out.println("root " + this.root);
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		this.root = (DefaultDiagramModel) request.getSession().getAttribute(Constants.EXERCICE);
		System.out.println(this.root);
		for (Element el : this.root.getElements()) {
			System.out.println(el.getStyleClass() + " " + el.getData()+" "+el.getId());
		}
	}

	public void solutionRecalculer(final String idElement) {
		String form = "form_exer:diag-";
		System.out.println("root " + this.root);
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		this.root = (DefaultDiagramModel) request.getSession().getAttribute(Constants.EXERCICE);
		System.out.println(this.root);
		for (Element el : this.root.getElements()) {
			if ((form + el.getId()).equals(idElement)) {
				el.setData(1);
			}
			System.out.println(el.getStyleClass() + " " + el.getData()+" "+el.getId());

		}
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

	public List<String> getNotionsExercice() {
		return this.notionsExercice;
	}

	public void setNotionsExercice(final List<String> notionsExercice) {
		this.notionsExercice = notionsExercice;
	}

}
