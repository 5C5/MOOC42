package mooc.dao;

import java.util.List;

import mooc.model.Connaissance;


/**
 * The Interface ConnaissanceDAO.
 */
public interface ConnaissanceDAO {

    /**
     * R�cup�rer la connaissance avec son id
     *
     * @param id Id de la connaissance
     * @return Connaissance correspondante
     */
    public Connaissance getById(final int id);
    
    /**
     * R�cup�rer la connaissance avec son libelle
     *
     * @param id Id de l'apprenant
     * @param libelle Libelle de la connaissance
     * @return Connaissance correspondante
     */
    public Connaissance getByLibelle(final int idApprenant, final String libelle);

    /**
     * Enregistrer une nouvelle connaissance
     *
     * @param connaissance Connaissance
     */
    public void save(final Connaissance connaissance);

    /**
     * Charger la liste des connaissances
     *
     * @param idApprenant Id de l'apprenant
     * @return List des connaissances
     */
    public List<Connaissance> loadConnaissance(final int idApprenant);

}
