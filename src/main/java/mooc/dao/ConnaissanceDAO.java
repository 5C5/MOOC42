package mooc.dao;

import java.util.List;

import mooc.model.Connaissance;


/**
 * The Interface ConnaissanceDAO.
 */
public interface ConnaissanceDAO {

    /**
     * Récupérer la connaissance avec son id
     *
     * @param id Id de la connaissance
     * @return Connaissance correspondante
     */
    public Connaissance getById(final int id);

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
