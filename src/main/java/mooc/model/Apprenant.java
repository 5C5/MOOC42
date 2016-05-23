package mooc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author colas Classe représentant les apprenants (individus)
 */
// @Getter
// @Setter
// @NoArgsConstructor
@Data
@Entity
@Table(name = "Apprenant")
public class Apprenant implements Serializable {

	/** Serializable */
	private static final long serialVersionUID = -792660145033278590L;

	/** Id de l'apprenant */
	@Id
	@GeneratedValue
	@Column(name = "id_apprenant", unique = true, nullable = false)
	private int idApprenant;

	/** Prenom de l'apprenant */
	@Column(name = "prenom", nullable = false)
	private String prenom;

	/** Nom de l'apprenant */
	@Column(name = "nom", nullable = false)
	private String nom;

	/** Mot de passe */
	@Column(name = "mot_de_passe", nullable = false)
	private String motDePasse;

	/** Theme */
	@Column(name = "theme")
	private String theme;

	/** Role */
	@Column(name = "role")
	private String role;

	/** Liste des connaissances */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "apprenant", cascade = CascadeType.ALL)
	private List<Connaissance> connaissances;

	/** Liste des compétences */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "apprenant", cascade = CascadeType.ALL)
	private List<Competence> competences;

	/**
	 * Constructeur basique
	 *
	 * @param prenom
	 * @param nom
	 */
	public Apprenant(final String prenom, final String nom) {
		this.prenom = prenom;
		this.nom = nom;
		this.connaissances = new ArrayList<Connaissance>();
		this.competences = new ArrayList<Competence>();
	}

	public Apprenant() {
		this.connaissances = new ArrayList<Connaissance>();
		this.competences = new ArrayList<Competence>();
	}

	/**
	 * Ajout d'une nouvelle notion
	 *
	 * @param nouvelle
	 */
	public void addNotion(final Notion nouvelle) {
		// if (this.connaissances == null) {
		// this.connaissances = new ArrayList<Notion>();
		// }
		// if (!this.connaissances.contains(nouvelle)) {
		// this.connaissances.add(nouvelle);
		// }
	}

	public int getIdApprenant() {
		return this.idApprenant;
	}

	public void setIdApprenant(final int idApprenant) {
		this.idApprenant = idApprenant;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(final String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(final String nom) {
		this.nom = nom;
	}

	public String getMotDePasse() {
		return this.motDePasse;
	}

	public void setMotDePasse(final String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getTheme() {
		return this.theme;
	}

	public void setTheme(final String theme) {
		this.theme = theme;
	}

	public List<Connaissance> getConnaissances() {
		return this.connaissances;
	}

	public void setConnaissances(final List<Connaissance> connaissances) {
		this.connaissances = connaissances;
	}

	public List<Competence> getCompetences() {
		return this.competences;
	}

	public void setCompetences(final List<Competence> competences) {
		this.competences = competences;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}