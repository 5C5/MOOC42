package mooc.dto;


/**
 * Dto pour gerer les niveaux deverouilles de l'apprenant
 */
public class NiveauDeverouilleDto {

	/** Id */
	private int id;
	/** Nom */
	private String nom;
	/** Niveau */
	private int niveau;

	/** Id notion */
	private int idNotion;

	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(final String nom) {
		this.nom = nom;
	}

	public int getNiveau() {
		return this.niveau;
	}

	public void setNiveau(final int niveau) {
		this.niveau = niveau;
	}

	public int getIdNotion() {
		return this.idNotion;
	}

	public void setIdNotion(final int idNotion) {
		this.idNotion = idNotion;
	}

}
