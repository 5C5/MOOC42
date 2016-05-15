package beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.diagram.Element;
import org.springframework.context.annotation.Scope;

import login.AbstractMBean;

@ManagedBean
@Scope("view")
public class ExerciceMBean extends AbstractMBean implements Serializable{

	/**
	 * ID
	 */
	private static final long serialVersionUID = -8124356110804029220L;

	private Element root;

	@PostConstruct
	public void init() {
		this.root = new Element("enfant 1");

		Element pere = new Element("papa");
		Element enfant2 = new Element("enfant 2");
		Element enfant3 = new Element("enfant 3");

		Element petitFils11 = new Element("petitFils 11");
		Element petitFils12 = new Element("petitFils 12");
		Element petitFils21 = new Element("petitFils 21");
		Element petitFils22 = new Element("petitFils 22");

		root.addNode(petitFils11);
		root.addNode(petitFils12);
		enfant2.addNode(petitFils21);
		enfant2.addNode(petitFils22);

		pere.addNode(root);
		pere.addNode(enfant2);
		pere.addNode(enfant3);

	}

	public Element getRoot() {
		return root;
	}

	public void setRoot(Element root) {
		this.root = root;
	}
}
