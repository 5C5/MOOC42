package mooc.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mooc.dao.ApprenantDAO;
import mooc.dao.CompetenceDAO;
import mooc.dao.NotionDAO;
import mooc.dto.NotionDto;
import mooc.model.Competence;
import mooc.model.CompetenceNotion;
import mooc.service.CompetenceService;
import mooc.utils.Constants;

import org.springframework.beans.factory.annotation.Autowired;

public class CompetenceServiceImpl implements CompetenceService, Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = -6419074212939519348L;

	/** DAO Competence */
	@Autowired
	private CompetenceDAO competenceDAO;

	/** DAO Apprenant */
	@Autowired
	private ApprenantDAO apprenantDAO;

	/** DAO Notion */
	@Autowired
	private NotionDAO notionDAO;

	@Override
	public void ajouterExercice(final int idApprenant, final List<NotionDto> notions, final int niveau, final int score) {
		Competence competence = new Competence();

		List<CompetenceNotion> compNotions = new ArrayList<CompetenceNotion>();
		String remarque = "Exercice de niveau ";
		if (niveau == 1) {
			remarque += Constants.NIVEAU_FACILE;
		} else if (niveau == 2) {
			remarque += Constants.NIVEAU_MOYEN;
		} else if (niveau == 3) {
			remarque += Constants.NIVEAU_DIFFICILE;
		}
		remarque += " pour ";
		boolean avant = false;
		for (int i = 0; i < notions.size(); i++) {
			if(i == notions.size() -1){
				remarque += avant ? " et " : "";
				remarque += notions.get(i).getNom();
			} else {
				remarque += avant ? ", " : "";
				remarque += notions.get(i).getNom();
			}
			avant = true;
			CompetenceNotion compNotion = new CompetenceNotion();
			compNotion.setCompetence(competence);
			compNotion.setNotion(this.notionDAO.getById(notions.get(i).getId()));
			compNotions.add(compNotion);
		}

		competence.setApprenant(this.apprenantDAO.getById(idApprenant));
		competence.setNiveau(niveau);
		competence.setScore(score);
		competence.setDate(new Date());
		competence.setRemarque(remarque);
		competence.setNotions(compNotions);
		this.competenceDAO.save(competence);
	}

	public CompetenceDAO getCompetenceDAO() {
		return this.competenceDAO;
	}

	public void setCompetenceDAO(final CompetenceDAO competenceDAO) {
		this.competenceDAO = competenceDAO;
	}

	public ApprenantDAO getApprenantDAO() {
		return this.apprenantDAO;
	}

	public void setApprenantDAO(final ApprenantDAO apprenantDAO) {
		this.apprenantDAO = apprenantDAO;
	}

	public NotionDAO getNotionDAO() {
		return this.notionDAO;
	}

	public void setNotionDAO(final NotionDAO notionDAO) {
		this.notionDAO = notionDAO;
	}

}
