package service.impl;

import org.springframework.stereotype.Service;

import dao.ApprenantDAO;
import dto.Apprenant;
import service.ApprenantService;

@Service
public class ApprenantServiceImpl implements ApprenantService {

    /** serialVersionUID */
    private static final long serialVersionUID = -7304548977948254205L;

    /**
     * DAO Apprenant
     */
	// @Autowired
    private ApprenantDAO apprenantDAO;

    @Override
    public int getConnexionApprenant(final String nom, final String motDePasse) {
        Apprenant apprenant = this.apprenantDAO.getByNom(nom, motDePasse);
        if (apprenant != null) {
            return apprenant.getId();
        }
        return -1;
    }
}
