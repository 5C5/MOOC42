package mooc.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import lombok.Getter;
import lombok.Setter;
import mooc.login.AbstractMBean;
import mooc.moteur.GenerateurFacile;

import org.primefaces.model.diagram.DefaultDiagramModel;
import org.springframework.context.annotation.Scope;

@Getter
@Setter
@ManagedBean
@Scope("view")
public class ExerciceMBean extends AbstractMBean implements Serializable{

	/**
	 * ID
	 */
	private static final long serialVersionUID = -8124356110804029220L;

	/** Elements Ã  afficher */
	/** Exercice */
	private DefaultDiagramModel root;
	/** LIste de notion */
	private List<String> notions;

	/** Elements/parametres a  recuperer */
	/** Liste des notions selectionnes pour l'exercice */
	private String[] selectedNotions;

	/** Parametres d'affichage */
	private boolean options;

	@PostConstruct
	public void init() {

		GenerateurFacile g = new GenerateurFacile();
		List<String> l = new ArrayList<String>();
		l.add("AND");
		g.generer(l);
		this.root = g.getExercice();
	}

	public DefaultDiagramModel getRoot() {
		return this.root;
	}

	public void setRoot(final DefaultDiagramModel root) {
		this.root = root;
	}

	public List<String> getNotions() {
		return notions;
	}

	public void setNotions(List<String> notions) {
		this.notions = notions;
	}

	public String[] getSelectedNotions() {
		return selectedNotions;
	}

	public void setSelectedNotions(String[] selectedNotions) {
		this.selectedNotions = selectedNotions;
	}

	public boolean isOptions() {
		return options;
	}

	public void setOptions(boolean options) {
		this.options = options;
	}

}
