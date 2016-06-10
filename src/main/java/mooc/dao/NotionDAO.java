package mooc.dao;

import java.util.List;

import mooc.model.Notion;


/**
 * The Interface NotionDAO.
 */
public interface NotionDAO {

	/**
	 * Recuperer la notion avec son id
	 *
	 * @param id Id de la notion
	 * @return Notion correspondante
	 */
	public Notion getById(final int id);

	/**
	 * Recuperer la notion avec son libelle
	 *
	 * @param libelle Libelle de la notion
	 * @return Notion correspondante
	 */
	public Notion getByLibelle(final String libelle);

	/**
	 * Recuperer toutes les notions
	 *
	 * @return List des notions
	 */
	public List<Notion> getAll();

}
