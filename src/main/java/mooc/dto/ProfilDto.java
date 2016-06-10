package mooc.dto;

import java.util.List;

/**
 * Dto utilise pour afficher les infos pour le profil
 */
public class ProfilDto {

	/** Id */
	private int id;

	/** Nom */
	private String nom;
	/** Prenom */
	private String prenom;

	/** Role */
	private String role;

	/** Niveaux */
	private List<NiveauDeverouilleDto> niveaux;
	private List<NiveauDeverouilleDto> niveauxComplexes;

	/** Evolution des competences */
	private List<EvolutionCompetenceDto> evolution;

	/** Liste des profils apprenant (si admin) */
	private List<ProfilDto> listeProfil;

	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(final String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(final String prenom) {
		this.prenom = prenom;
	}

	public List<NiveauDeverouilleDto> getNiveaux() {
		return this.niveaux;
	}

	public void setNiveaux(final List<NiveauDeverouilleDto> niveaux) {
		this.niveaux = niveaux;
	}

	public List<EvolutionCompetenceDto> getEvolution() {
		return this.evolution;
	}

	public void setEvolution(final List<EvolutionCompetenceDto> evolution) {
		this.evolution = evolution;
	}

	public boolean isAdmin() {
		return "Admin".equalsIgnoreCase(this.role);
	}

	public boolean isApprenant() {
		return "Apprenant".equalsIgnoreCase(this.role);
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(final String role) {
		this.role = role;
	}

	public List<ProfilDto> getListeProfil() {
		return this.listeProfil;
	}

	public void setListeProfil(final List<ProfilDto> listeProfil) {
		this.listeProfil = listeProfil;
	}

	public List<NiveauDeverouilleDto> getNiveauxComplexes() {
		return niveauxComplexes;
	}

	public void setNiveauxComplexes(List<NiveauDeverouilleDto> niveauxComplexes) {
		this.niveauxComplexes = niveauxComplexes;
	}

}
