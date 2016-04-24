package service;



/** Interface de definition des methodes permettant d'acceder a la DAO Apprenant */
public interface ApprenantService {

    /**
     * Connecter un utilisateur
     * 
     * @param nom Nom de l'utilisateur
     * @param motDePasse Mot de passe
     * @return Identifiant de l'utilisateur
     */
    public int getConnexionApprenant(final String nom, final String motDePasse);

}
