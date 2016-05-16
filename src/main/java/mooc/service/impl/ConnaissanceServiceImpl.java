package mooc.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.Setter;
import mooc.dao.ConnaissanceDAO;
import mooc.dto.NiveauDeverouilleDto;
import mooc.model.Connaissance;
import mooc.service.ConnaissanceService;

//@Service("apprenantService")
@Getter
@Setter
public class ConnaissanceServiceImpl implements ConnaissanceService, Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = -5057050010791237795L;

	/** DAO Connaissance */
	@Autowired
	private ConnaissanceDAO connaissanceDAO;

	@Override
	public void changerNiveauConnaissance(final int idConnaissance, final int nouveauNiveau) {
		Connaissance connaissance = this.connaissanceDAO.getById(idConnaissance);
		connaissance.setNiveau(nouveauNiveau);
		this.connaissanceDAO.save(connaissance);
	}

	@Override
	public List<NiveauDeverouilleDto> reloadNiveau(final int idApprenant) {
		List<Connaissance> connaissances = this.connaissanceDAO.loadConnaissance(idApprenant);
		List<NiveauDeverouilleDto> niveaux = new ArrayList<NiveauDeverouilleDto>();
		for (Connaissance connaissance : connaissances) {
			NiveauDeverouilleDto niveau = new NiveauDeverouilleDto();
			niveau.setId(connaissance.getIdConnaissance());
			niveau.setNom(connaissance.getNotion().getNomNotion());
			niveau.setNiveau(connaissance.getNiveau());
			niveaux.add(niveau);
		}
		return niveaux;
	}

	public ConnaissanceDAO getConnaissanceDAO() {
		return connaissanceDAO;
	}

	public void setConnaissanceDAO(ConnaissanceDAO connaissanceDAO) {
		this.connaissanceDAO = connaissanceDAO;
	}

}
