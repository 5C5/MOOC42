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
 * @author colas Consultation des cours
 */
@Getter
@Setter
// @NoArgsConstructor
@Entity
public class Connaissance {

    /** ID de la connaissance */
    @Id
    @GeneratedValue
    @Column(name = "id_connaissance", unique = true, nullable = false)
    private int idConnaissance;

    /** Apprenant ayant lu le cours */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_apprenant", nullable = false)
    private Apprenant apprenant;

    /** Notion lue */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_notion", nullable = false)
    private Notion notion;

    /** Niveau */
    @Column(name = "niveau")
    private int niveau;

    /**
     * Constructeur complet
     *
     * @param apprenant
     * @param notion
     */
    public Connaissance(final Apprenant apprenant, final Notion notion) {
        this.apprenant = apprenant;
        this.notion = notion;
    }

    public Connaissance() {

    }

	public int getIdConnaissance() {
		return idConnaissance;
	}

	public void setIdConnaissance(int idConnaissance) {
		this.idConnaissance = idConnaissance;
	}

	public Apprenant getApprenant() {
		return apprenant;
	}

	public void setApprenant(Apprenant apprenant) {
		this.apprenant = apprenant;
	}

	public Notion getNotion() {
		return notion;
	}

	public void setNotion(Notion notion) {
		this.notion = notion;
	}

	public int getNiveau() {
		return niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

}
