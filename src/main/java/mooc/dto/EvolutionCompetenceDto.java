package mooc.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Dto pour gerer les evolutions des competences de l'apprenant
 */
@Getter
@Setter
public class EvolutionCompetenceDto {

    /** Date */
    private String date;
    /** Niveau */
    private int niveau;
    /** Remarque */
    private String remarque;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getNiveau() {
		return niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

	public String getRemarque() {
		return remarque;
	}

	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}

}
