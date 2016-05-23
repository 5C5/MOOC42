package mooc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Lien entre les competences et les notions
 *
 * @author colas
 */
// @NoArgsConstructor
@Entity
public class CompetenceNotion {

    /** ID */
    @Id
    @GeneratedValue
    @Column(name = "id_competence_notion", unique = true, nullable = false)
    private int idCompetenceNotion;

    /** ID de le competence */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_competence", nullable = false)
    private Competence competence;

    /** ID de la notion */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_notion", nullable = false)
    private Notion notion;

	public int getIdCompetenceNotion() {
		return this.idCompetenceNotion;
	}

	public void setIdCompetenceNotion(final int idCompetenceNotion) {
		this.idCompetenceNotion = idCompetenceNotion;
	}

	public Competence getCompetence() {
		return this.competence;
	}

	public void setCompetence(final Competence competence) {
		this.competence = competence;
	}

	public Notion getNotion() {
		return this.notion;
	}

	public void setNotion(final Notion notion) {
		this.notion = notion;
	}

}
