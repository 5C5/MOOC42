package dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author colas
 *
 *         Notion de cours, les différentes portes logiques existentes
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Notion {

	/**
	 * Id de la notion
	 */
	@Id
	@GeneratedValue
	private int id;

	/**
	 * Nom de la notion
	 */
	private String nom;

	/**
	 * Constructeur basique
	 * 
	 * @param nom
	 */
	public Notion(String nom) {
		this.nom = nom;
	}
}
