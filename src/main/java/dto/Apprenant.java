package dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 
 * @author colas Classe représentant les apprenants (individus)
 *
 */
@Entity
public class Apprenant {

	/**
	 * Id de l'apprenant
	 */
	@Id
	@GeneratedValue
	private int id;

	/**
	 * prenom de l'apprenant
	 */
	private String prenom;

	private String nom;
}
