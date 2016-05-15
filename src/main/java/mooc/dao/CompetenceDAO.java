package mooc.dao;

import java.util.List;

import mooc.model.Competence;


/**
 * The Interface CompetenceDAO.
 */
public interface CompetenceDAO {

    /**
     * Récupérer la connaissance avec son id
     * 
     * @param id Id de la competence
     * @return Competence correspondante
     */
    public Competence getById(final int id);

    /**
     * Enregistrer une nouvelle Competence
     * 
     * @param competence Competence
     */
    public void save(final Competence competence);

    /**
     * Charger la liste des competences
     *
     * @param idApprenant Id de l'apprenant
     * @return List des competences
     */
    public List<Competence> loadCompetence(final int idApprenant);

}
