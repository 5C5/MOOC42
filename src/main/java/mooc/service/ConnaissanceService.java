package mooc.service;



/**
 * Interface de definition des methodes permettant d'acceder au DAO Connaissance
 */
public interface ConnaissanceService {

    /**
     * Changer le niveau d'une connaissance
     *
     * @param idConnaissance Id de la connaissance
     * @param nouveauNiveau Nouveau niveau
     */
    public void changerNiveauConnaissance(final int idConnaissance, final int nouveauNiveau);

}
