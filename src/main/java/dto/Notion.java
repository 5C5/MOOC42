package dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 
 * @author colas
 *
 *         Notion de cours, les différentes portes logiques existentes
 */
@Entity
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
}
