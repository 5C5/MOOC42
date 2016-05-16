package mooc.dto;

import java.util.Date;
import java.util.List;

/**
 * Dto pour gerer les evolutions des competences de l'apprenant
 */
public class EvolutionCompetenceDto {

	/** Id */
	private int id;
	/** Date */
	private Date date;
	/** Niveau */
	private int niveau;
	/** Score */
	private int score;
	/** Remarque */
	private String remarque;
	/** Notions */
	private List<CompetenceNotionDto> notions;

	public Date getDate() {
		return this.date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	public int getNiveau() {
		return this.niveau;
	}

	public void setNiveau(final int niveau) {
		this.niveau = niveau;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(final int score) {
		this.score = score;
	}

	public String getRemarque() {
		return this.remarque;
	}

	public void setRemarque(final String remarque) {
		this.remarque = remarque;
	}

	public List<CompetenceNotionDto> getNotions() {
		return this.notions;
	}

	public void setNotions(final List<CompetenceNotionDto> notions) {
		this.notions = notions;
	}

	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

}
