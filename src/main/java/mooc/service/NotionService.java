package mooc.service;

import java.util.List;



/**
 * Interface de definition des methodes permettant d'acceder au DAO Notion
 */
public interface NotionService {

	/**
	 * Recuperer tous les libelles des notions
	 *
	 * @return List de tous les libelles
	 */
	public List<String> getAllLibelle();

}
