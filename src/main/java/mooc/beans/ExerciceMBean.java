package mooc.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.diagram.Element;
import org.springframework.context.annotation.Scope;

import lombok.Getter;
import lombok.Setter;
import mooc.login.AbstractMBean;

@Getter
@Setter
@ManagedBean
@Scope("view")
public class ExerciceMBean extends AbstractMBean implements Serializable{

	/**
	 * ID
	 */
	private static final long serialVersionUID = -8124356110804029220L;

	/** Elements à afficher */
	/** Exercice */
	private Element root;
	/** LIste de notion */
	private List<String> notions;

	/** Elements/paramètres à récupérer */
	/** Liste des notions selectionnées pour l'exercice */
	private String[] selectedNotions;

	/** Paramètres d'affichage */
	private boolean options;

	@PostConstruct
	public void init() {

	}

	public Element getRoot() {
		return this.root;
	}

	public void setRoot(final Element root) {
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
