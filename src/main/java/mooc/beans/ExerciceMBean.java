package mooc.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import mooc.dto.NotionDto;
import mooc.login.AbstractMBean;
import mooc.moteur.GenerateurFacile;
import mooc.service.NotionService;

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

	@PostConstruct
	public void init() {
		this.disabled = false;
		this.notions = this.notionService.getAll();
		// TODO controles :
		// - un niveau doit etre sélectionne
		// - la liste des notions selectionnees doit avoir au moins deux
		// elements
	}

	public void generer() {
		this.disabled = true;
		System.out.println("niveau : " + this.niveau);
		System.out.println("notions : " + this.selectedNotions.length);
		GenerateurFacile g = new GenerateurFacile();
		List<String> l = new ArrayList<String>();
		l.add("AND");
		g.generer(l);
		this.root = g.getExercice();
		System.out.println("fini");
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

}
