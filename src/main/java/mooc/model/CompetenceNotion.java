package mooc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

/**
 * Lien entre les competences et les notions
 *
 * @author colas
 */
@Getter
@Setter
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
		return idCompetenceNotion;
	}

	public void setIdCompetenceNotion(int idCompetenceNotion) {
		this.idCompetenceNotion = idCompetenceNotion;
	}

	public Competence getCompetence() {
		return competence;
	}

	public void setCompetence(Competence competence) {
		this.competence = competence;
	}

	public Notion getNotion() {
		return notion;
	}

	public void setNotion(Notion notion) {
		this.notion = notion;
	}

}
