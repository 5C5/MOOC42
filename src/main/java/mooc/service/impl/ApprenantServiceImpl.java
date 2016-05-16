package mooc.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.Setter;
import mooc.dao.ApprenantDAO;
import mooc.dao.CompetenceDAO;
import mooc.dao.ConnaissanceDAO;
import mooc.dao.NotionDAO;
import mooc.dto.EvolutionCompetenceDto;
import mooc.dto.NiveauDeverouilleDto;
import mooc.dto.ProfilDto;
import mooc.model.Apprenant;
import mooc.model.Connaissance;
import mooc.model.Notion;
import mooc.service.ApprenantService;

//@Service("apprenantService")
@Getter
@Setter
public class ApprenantServiceImpl implements ApprenantService, Serializable {

<<<<<<< HEAD
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
    public ProfilDto getProfilApprenant(final int id) {
        ProfilDto profil = new ProfilDto();
        Apprenant apprenant = this.apprenantDAO.getById(id);

        // ID
        profil.setId(apprenant.getIdApprenant());
        // Nom
        profil.setNom(apprenant.getNom());
        // Prenom
        profil.setPrenom(apprenant.getPrenom());

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
        profil.setEvolution(new ArrayList<EvolutionCompetenceDto>());
        EvolutionCompetenceDto evol1 = new EvolutionCompetenceDto();
        evol1.setDate("01/01/2000");
        evol1.setNiveau(1);
        evol1.setRemarque("Exercice de validation du OR reussi");
        profil.getEvolution().add(evol1);
        EvolutionCompetenceDto evol2 = new EvolutionCompetenceDto();
        evol2.setDate("08/11/2000");
        evol2.setNiveau(2);
        evol2.setRemarque("Exercice avec les portes OR / NOT reussi");
        profil.getEvolution().add(evol2);

        return profil;
    }

	public ApprenantDAO getApprenantDAO() {
		return apprenantDAO;
	}

	public void setApprenantDAO(ApprenantDAO apprenantDAO) {
		this.apprenantDAO = apprenantDAO;
	}

	public NotionDAO getNotionDAO() {
		return notionDAO;
	}

	public void setNotionDAO(NotionDAO notionDAO) {
		this.notionDAO = notionDAO;
	}

	public CompetenceDAO getCompetenceDAO() {
		return competenceDAO;
	}

	public void setCompetenceDAO(CompetenceDAO competenceDAO) {
		this.competenceDAO = competenceDAO;
	}

	public ConnaissanceDAO getConnaissanceDAO() {
		return connaissanceDAO;
	}

	public void setConnaissanceDAO(ConnaissanceDAO connaissanceDAO) {
		this.connaissanceDAO = connaissanceDAO;
	}

}
