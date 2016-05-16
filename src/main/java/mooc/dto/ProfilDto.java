package mooc.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Dto utilis� pour afficher les infos pour le profil
 */
@Getter
@Setter
public class ProfilDto {

    /** Id */
    private int id;

    /** Nom */
    private String nom;
    /** Prenom */
    private String prenom;

    /** Niveaux */
    private List<NiveauDeverouilleDto> niveaux;

    /** Evolution des competences */
    private List<EvolutionCompetenceDto> evolution;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public List<NiveauDeverouilleDto> getNiveaux() {
		return niveaux;
	}

	public void setNiveaux(List<NiveauDeverouilleDto> niveaux) {
		this.niveaux = niveaux;
	}

	public List<EvolutionCompetenceDto> getEvolution() {
		return evolution;
	}

	public void setEvolution(List<EvolutionCompetenceDto> evolution) {
		this.evolution = evolution;
	}

}
