package mooc.service;

import java.util.List;

import mooc.dto.NotionDto;



/**
 * Interface de definition des methodes permettant d'acceder au DAO Competence
 */
public interface CompetenceService {

	/**
	 * Ajout d'un exercice dans les competences
	 *
	 * @param idApprenant Id de l'apprenant
	 * @param notions Notions
	 * @param niveau Niveau
	 * @param score Score
	 */
	public void ajouterExercice(final int idApprenant, final List<NotionDto> notions, final int niveau, final int score);

}
