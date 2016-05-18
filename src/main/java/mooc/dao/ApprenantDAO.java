package mooc.dao;

import java.util.List;

import mooc.model.Apprenant;


/**
 * The Interface ApprenantDAO.
 */
public interface ApprenantDAO {

	/**
	 * Récupérer l'apprenant en fonction de criteres
	 * @param nom Nom
	 * @param prenom Prenom
	 * @param motDePasse Mot de passe
	 * @return Apprenant correspondant
	 */
	public Apprenant getByCritere(final String nom, final String prenom, final String motDePasse);

	/**
	 * Récupérer l'apprenant avec son id
	 * @param id Id de l'apprenant
	 * @return Apprenant correspondant
	 */
	public Apprenant getById(final int id);

	/**
	 * Récupérer le theme de l'apprenant avec son id
	 * @param id Id de l'apprenant
	 * @return Theme correspondant
	 */
	public String getThemeById(final int id);

	/**
	 * Enregistrer un nouvel apprenant
	 * @param apprenant Apprenant
	 */
	public void save(final Apprenant apprenant);

	/**
	 * Tester si l'utilisateur existe
	 *
	 * @param nom Nom de l'utilisateur
	 * @param prenom Prenom de l'utilisateur
	 * @return true si l'apprenant est deja en base, sinon false
	 */
	public boolean isApprenantExists(final String nom, final String prenom);

	/**
	 * Recuperer tous les apprenants
	 *
	 * @return List des apprenants
	 */
	public List<Apprenant> getAll();

}
