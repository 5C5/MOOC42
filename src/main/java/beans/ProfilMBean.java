package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.context.annotation.Scope;

import dto.Apprenant;
import dto.Competence;
import login.AbstractMBean;

/**
 * Classe de gestion du profil
 * 
 * @author colas
 *
 */
// @Data
// @Getter
// @Setter
// @NoArgsConstructor
@ManagedBean
@Scope("view")
public class ProfilMBean extends AbstractMBean implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Apprenant dont le profil est affiché.
	 */
	private Apprenant apprenant;

	/**
	 * Liste des compétences de l'apprenant
	 */
	private List<Competence> competences;

	/** Boolean pour l'édition des données */
	public boolean nonEditable;

	@PostConstruct
	public void init() {

		this.competences = new ArrayList<Competence>();
		this.nonEditable = true;

		// TODO récupérer l'ID de l'apprenant, récupérer son profil en base
		// TODO récupérer la liste des compétences qu'a réalisé l'apprenant

		/*
		 * Version uniquement valable pour le test d'affichage
		 * 
		 * TODO retirer cette partie
		 */

		this.apprenant = new Apprenant("colas", "picard");
	}

	public Apprenant getApprenant() {
		return apprenant;
	}

	public void setApprenant(Apprenant apprenant) {
		this.apprenant = apprenant;
	}

	public List<Competence> getCompetences() {
		return competences;
	}

	public void setCompetences(List<Competence> competences) {
		this.competences = competences;
	}

	public boolean isNonEditable() {
		return nonEditable;
	}

	public void setNonEditable(boolean nonEditable) {
		this.nonEditable = nonEditable;
	}

}
