package mooc.service.impl;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import mooc.dao.ConnaissanceDAO;
import mooc.model.Connaissance;
import mooc.service.ConnaissanceService;

import org.springframework.beans.factory.annotation.Autowired;

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

}
