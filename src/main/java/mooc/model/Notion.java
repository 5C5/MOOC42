package mooc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author colas Notion de cours, les différentes portes logiques existentes
 */
@Entity
// @NoArgsConstructor
public class Notion {

    /** Id de la notion */
    @Id
    @GeneratedValue
    @Column(name = "id_notion", unique = true, nullable = false)
    private int idNotion;

    /** Nom de la notion */
    @Column(name = "nom_notion")
    private String nomNotion;

    /**
     * Constructeur basique
     *
     * @param nom
     */
    public Notion(final String nom) {
        this.nomNotion = nom;
    }

    public Notion() {

    }

	public int getIdNotion() {
		return this.idNotion;
	}

	public void setIdNotion(final int idNotion) {
		this.idNotion = idNotion;
	}

	public String getNomNotion() {
		return this.nomNotion;
	}

	public void setNomNotion(final String nomNotion) {
		this.nomNotion = nomNotion;
	}

}
