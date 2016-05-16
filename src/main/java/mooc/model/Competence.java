package mooc.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

/**
 * @author colas Résultat des exercices
 */
@Getter
@Setter
// @NoArgsConstructor
@Entity
public class Competence {

	/** ID du resultat */
	@Id
	@GeneratedValue
	@Column(name = "id_competence", unique = true, nullable = false)
	private int idCompetence;

	/** Apprenant ayant fait l'exercice */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_apprenant", nullable = false)
	private Apprenant apprenant;

	/** Notions concernees par l'exercice */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "notion")
	private List<CompetenceNotion> notions;

	/** Date de la realisation de l'exercice */
	@Column(name = "date_competence")
	private Date date;

	/** Difficulte de l'exercice realise */
	@Column(name = "niveau")
	private Integer niveau;

	/** Score effectue */
	@Column(name = "score")
	private Integer score;

	/** Remarque */
	@Column(name = "remarque")
	private String remarque;


	/**
	 * Constructeur complet
	 *
	 * @param apprenant
	 * @param notions
	 * @param difficulte
	 * @param score
	 */
	public Competence(final Apprenant apprenant, final List<Notion> notions, final int difficulte, final int score) {

	}

	public Competence() {

	}

	public int getIdCompetence() {
		return this.idCompetence;
	}

	public void setIdCompetence(final int idCompetence) {
		this.idCompetence = idCompetence;
	}

	public Apprenant getApprenant() {
		return this.apprenant;
	}

	public void setApprenant(final Apprenant apprenant) {
		this.apprenant = apprenant;
	}

	public List<CompetenceNotion> getNotions() {
		return this.notions;
	}

	public void setNotions(final List<CompetenceNotion> notions) {
		this.notions = notions;
	}

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

	public void setNiveau(final Integer niveau) {
		this.niveau = niveau;
	}

	public void setScore(final Integer score) {
		this.score = score;
	}

}
