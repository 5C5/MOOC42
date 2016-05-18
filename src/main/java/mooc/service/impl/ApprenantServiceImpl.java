package mooc.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import mooc.dao.ApprenantDAO;
import mooc.dao.CompetenceDAO;
import mooc.dao.ConnaissanceDAO;
import mooc.dao.NotionDAO;
import mooc.dto.CompetenceNotionDto;
import mooc.dto.EvolutionCompetenceDto;
import mooc.dto.NiveauDeverouilleDto;
import mooc.dto.ProfilDto;
import mooc.model.Apprenant;
import mooc.model.Competence;
import mooc.model.CompetenceNotion;
import mooc.model.Connaissance;
import mooc.model.Notion;
import mooc.service.ApprenantService;
import mooc.utils.Constants;

import org.springframework.beans.factory.annotation.Autowired;

//@Service("apprenantService")
@Getter
@Setter
public class ApprenantServiceImpl implements ApprenantService, Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = -7304548977948254205L;

	/** DAO Apprenant */
	@Autowired
	private ApprenantDAO apprenantDAO;

	/** DAO Notion */
	@Autowired
	private NotionDAO notionDAO;

	/** DAO Competence */
	@Autowired
	private CompetenceDAO competenceDAO;

	/** DAO Connaissance */
	@Autowired
	private ConnaissanceDAO connaissanceDAO;

	@Override
	public int getIdApprenant(final String nom, final String prenom, final String motDePasse) {
		Apprenant apprenant = this.apprenantDAO.getByCritere(nom, prenom, motDePasse);
		if (apprenant != null) {
			return apprenant.getIdApprenant();
		}
		return -1;
	}

	@Override
	public int inscrireApprenant(final String nom, final String prenom, final String motDePasse) {
		Apprenant apprenant = new Apprenant();

		// Informations g�n�rales
		apprenant.setNom(nom);
		apprenant.setPrenom(prenom);
		apprenant.setMotDePasse(motDePasse);
		// apprenant.setTheme(Constants.THEME_DEFAUT);
		apprenant.setRole(Constants.APPRENANT);

		// Connaissances
		List<Connaissance> connaissances = new ArrayList<Connaissance>();
		List<Notion> notions = this.notionDAO.getAll();
		for (Notion notion : notions) {
			Connaissance connaissance = new Connaissance();
			connaissance.setApprenant(apprenant);
			connaissance.setNotion(notion);
			connaissance.setNiveau(0);
			connaissances.add(connaissance);
		}
		apprenant.setConnaissances(connaissances);

		this.apprenantDAO.save(apprenant);
		return apprenant.getIdApprenant();
	}

	@Override
	public String getThemeById(final int id) {
		Apprenant apprenant = this.apprenantDAO.getById(id);
		if (apprenant != null) {
			return apprenant.getTheme();
		}
		return null;
	}

	@Override
	public boolean isApprenantExists(final String nom, final String prenom) {
		return this.apprenantDAO.isApprenantExists(nom, prenom);
	}

	@Override
	public ProfilDto getProfil(final int id) {
		ProfilDto profil = new ProfilDto();
		Apprenant apprenant = this.apprenantDAO.getById(id);

		// ID
		profil.setId(apprenant.getIdApprenant());
		// Nom
		profil.setNom(apprenant.getNom());
		// Prenom
		profil.setPrenom(apprenant.getPrenom());
		// Role
		profil.setRole(apprenant.getRole());

		if(profil.isApprenant()){
			/** Profil apprenant */
			profil = this.getProfilApprenant(apprenant);
		} else {
			/** Profil admin */
			profil.setListeProfil(new ArrayList<ProfilDto>());
			List<Apprenant> listeApprenant = this.apprenantDAO.getAll();
			for (Apprenant app : listeApprenant) {
				if (app.getIdApprenant() != apprenant.getIdApprenant()) {
					ProfilDto profilApp = this.getProfilApprenant(app);
					profil.getListeProfil().add(profilApp);
				}
			}
		}

		return profil;
	}

	private ProfilDto getProfilApprenant(final Apprenant apprenant){
		ProfilDto profil = new ProfilDto();
		// ID
		profil.setId(apprenant.getIdApprenant());
		// Nom
		profil.setNom(apprenant.getNom());
		// Prenom
		profil.setPrenom(apprenant.getPrenom());
		// Role
		profil.setRole(apprenant.getRole());

		// Notions
		List<Connaissance> connaissances = this.connaissanceDAO.loadConnaissance(apprenant.getIdApprenant());
		List<NiveauDeverouilleDto> niveaux = new ArrayList<NiveauDeverouilleDto>();
		for (Connaissance connaissance : connaissances) {
			NiveauDeverouilleDto niveau = new NiveauDeverouilleDto();
			niveau.setId(connaissance.getIdConnaissance());
			niveau.setNom(connaissance.getNotion().getNomNotion());
			niveau.setNiveau(connaissance.getNiveau());
			niveaux.add(niveau);
		}
		profil.setNiveaux(niveaux);

		// Evolution des competences
		List<Competence> competences = this.competenceDAO.loadCompetence(apprenant.getIdApprenant());
		List<EvolutionCompetenceDto> evolutions = new ArrayList<EvolutionCompetenceDto>();
		for (Competence competence : competences) {
			EvolutionCompetenceDto evolution = new EvolutionCompetenceDto();
			evolution.setId(competence.getIdCompetence());
			evolution.setDate(competence.getDate());
			evolution.setNiveau(competence.getNiveau());
			evolution.setRemarque(competence.getRemarque());
			evolution.setScore(competence.getScore());
			List<CompetenceNotionDto> notionsDto = new ArrayList<CompetenceNotionDto>();
			for (CompetenceNotion notion : competence.getNotions()) {
				CompetenceNotionDto dto = new CompetenceNotionDto();
				dto.setId(notion.getIdCompetenceNotion());
				dto.setNom(notion.getNotion().getNomNotion());
				notionsDto.add(dto);
			}
			evolution.setNotions(notionsDto);
			evolutions.add(evolution);
		}
		profil.setEvolution(evolutions);
		return profil;
	}

	@Override
	public void modifierMdp(final int idApprenant, final String nouveauMdp) {
		Apprenant apprenant = this.apprenantDAO.getById(idApprenant);
		apprenant.setMotDePasse(nouveauMdp);
		this.apprenantDAO.save(apprenant);
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

	public CompetenceDAO getCompetenceDAO() {
		return this.competenceDAO;
	}

	public void setCompetenceDAO(final CompetenceDAO competenceDAO) {
		this.competenceDAO = competenceDAO;
	}

	public ConnaissanceDAO getConnaissanceDAO() {
		return this.connaissanceDAO;
	}

	public void setConnaissanceDAO(final ConnaissanceDAO connaissanceDAO) {
		this.connaissanceDAO = connaissanceDAO;
	}


}
