package dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

/**
 * 
 * @author colas Classe représentant les apprenants (individus)
 *
 */
// @Getter
// @Setter
// @NoArgsConstructor
@Entity
public class Apprenant {

	/** Id de l'apprenant (BDD) */
	@Id
	@GeneratedValue
	private int id;

	/** Prenom de l'apprenant */
	private String prenom;

	/** Nom de l'apprenant */
	private String nom;

	@OneToMany
	@JoinTable(name = "connaissances", joinColumns = { @JoinColumn(name = "id_apprenant") }, inverseJoinColumns = {
			@JoinColumn(name = "id_notion") })
	private List<Notion> connaissances;

	/**
	 * Constructeur basique
	 * 
	 * @param prenom
	 * @param nom
	 */
	public Apprenant(String prenom, String nom) {
		this.prenom = prenom;
		this.nom = nom;
		this.connaissances = new ArrayList<>();
	}

	public Apprenant() {
	}

	/**
	 * Ajout d'une nouvelle notion
	 * 
	 * @param nouvelle
	 */
	public void addNotion(Notion nouvelle) {
		if (this.connaissances == null)
			this.connaissances = new ArrayList<Notion>();
		if (!this.connaissances.contains(nouvelle))
			this.connaissances.add(nouvelle);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Notion> getConnaissances() {
		return connaissances;
	}

	public void setConnaissances(List<Notion> connaissances) {
		this.connaissances = connaissances;
	}

}