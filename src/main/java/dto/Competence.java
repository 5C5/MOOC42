package dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author colas
 *
 *         Résultat des exercices
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Competence {

	/**
	 * ID du résultat
	 */
	@Id
	@GeneratedValue
	private int id;

	/**
	 * Apprenant ayant fait l'exercice
	 */
	private Apprenant apprenant;

	/**
	 * Notions concernées par l'exercice
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "competences")
	@JoinColumn(name = "id_notion")
	private List<Notion> notions;

	/**
	 * Date de la réalisation de l'exercice
	 */
	private Date date;

	/**
	 * Difficulté de l'exercice réalisé
	 */
	private int difficulté;

	/**
	 * Score effectué
	 */
	private int score;

	/**
	 * Constructeur complet
	 * 
	 * @param apprenant
	 * @param notions
	 * @param difficulte
	 * @param score
	 */
	public Competence(Apprenant apprenant, List<Notion> notions, int difficulte, int score) {
		this.apprenant = apprenant;
		if (notions != null)
			this.notions = notions;
		this.score = score;
		this.difficulté = difficulte;
		this.date = new Date();
	}
}