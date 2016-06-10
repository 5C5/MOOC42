package mooc.service;

import java.util.List;

import mooc.dto.NotionDto;



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

	/**
	 * Recuperer toutes les notions
	 *
	 * @return List de toutes les notionDto
	 */
	public List<NotionDto> getAll();

	/**
	 * Recuperer la notion associée au libellé
	 *
	 * @param libelle Libellé
	 * @return NotionDto
	 */
	public NotionDto getByLibelle(final String libelle);

	/***
	 * Recuperer les portes fondamentales
	 *
	 * @return Liste des portes
	 */
	public List<NotionDto> getPortesFondamentales();

	/***
	 * Recuperer les portes complexes
	 *
	 * @return Liste des portes
	 */
	public List<NotionDto> getPortesComplexes();

}
