package mooc.service;

import mooc.dto.ProfilDto;


/**
 * Interface de definition des methodes permettant d'acceder Ã  la DAO Apprenant
 */
public interface ApprenantService {

	/**
	 * Connecter un utilisateur
	 *
	 * @param nom Nom de l'utilisateur
	 * @param prenom Prenom de l'utilisateur
	 * @param motDePasse Mot de passe
	 * @return Identifiant de l'utilisateur
	 */
	public int getIdApprenant(final String nom, final String prenom, final String motDePasse);

	/**
	 * Inscrire un nouvel apprenant
	 * @param nom Nom
	 * @param prenom Prenom
	 * @param motDePasse Mot de passe
	 * @return Identifiant de l'apprenant
	 */
	public int inscrireApprenant(final String nom, final String prenom, final String motDePasse);

	/**
	 * Récupérer le theme de l'apprenant avec son id
	 *
	 * @param id Id de l'apprenant
	 * @return Theme correspondant
	 */
	public String getThemeById(final int id);

	/**
	 * Tester si l'utilisateur existe
	 *
	 * @param nom Nom de l'utilisateur
	 * @param prenom Prenom de l'utilisateur
	 * @return true si l'apprenant est deja en base, sinon false
	 */
	public boolean isApprenantExists(final String nom, final String prenom);

	/**
	 * Recuperer le profil de l'apprenant
	 *
	 * @param id Id de l'apprenant
	 * @return ProfilDto contenant toutes les infos de l'apprenant
	 */
	public ProfilDto getProfilApprenant(final int id);

	/**
	 * Modifier le mot de passe
	 *
	 * @param idApprenant Id de l'apprenant
	 * @param nouveauMdp Nouveau mot de passe
	 */
	public void modifierMdp(final int idApprenant, final String nouveauMdp);

}
