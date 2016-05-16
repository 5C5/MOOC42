package mooc.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Dto pour gerer les niveaux deverouilles de l'apprenant
 */
@Getter
@Setter
public class NiveauDeverouilleDto {

    /** Id */
    private int id;
    /** Nom */
    private String nom;
    /** Niveau */
    private int niveau;

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

	public int getNiveau() {
		return niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

}
