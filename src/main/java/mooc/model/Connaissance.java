package mooc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import mooc.utils.Constants;

/**
 * @author colas Consultation des cours
 */
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
		return this.idConnaissance;
	}

	public void setIdConnaissance(final int idConnaissance) {
		this.idConnaissance = idConnaissance;
	}

	public Apprenant getApprenant() {
		return this.apprenant;
	}

	public void setApprenant(final Apprenant apprenant) {
		this.apprenant = apprenant;
	}

	public Notion getNotion() {
		return this.notion;
	}

	public void setNotion(final Notion notion) {
		this.notion = notion;
	}

	public int getNiveau() {
		return this.niveau;
	}

	public void setNiveau(final int niveau) {
		this.niveau = niveau;
	}

	public boolean isComplexe() {
		if (Constants.NOT.equalsIgnoreCase(this.notion.getNomNotion())) {
			return false;
		} else if (Constants.AND.equalsIgnoreCase(this.notion.getNomNotion())) {
			return false;
		} else if (Constants.OR.equalsIgnoreCase(this.notion.getNomNotion())) {
			return false;
		}
		return true;
	}
}
