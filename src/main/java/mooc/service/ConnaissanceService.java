package mooc.service;

import java.util.List;

import mooc.dto.NiveauDeverouilleDto;



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

	/**
	 * Recharger la liste des niveaux (connaissances) de l'apprenant
	 * 
	 * @param idApprenant Id de l'apprenant
	 * @return List des NiveauDeverouilleDto
	 */
	public List<NiveauDeverouilleDto> reloadNiveau(final int idApprenant);
	
	/**
	 * Recharger la liste des niveaux (connaissances) de l'apprenant
	 * 
	 * @param idApprenant Id de l'apprenant
	 * @return List des NiveauDeverouilleDto
	 */
	public List<NiveauDeverouilleDto> reloadNiveauComplexes(final int idApprenant);

}
