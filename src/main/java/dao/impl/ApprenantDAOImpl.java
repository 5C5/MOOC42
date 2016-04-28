package dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import dao.ApprenantDAO;
import dto.Apprenant;


/**
 * The Class ApprenantDAOImpl.
 */
@Repository
public class ApprenantDAOImpl extends AbstractDAO<Apprenant> implements ApprenantDAO {

    /** serialVersionUID. */
    private static final long serialVersionUID = 412909852289002427L;

    @Override
    public Apprenant getByNom(final String nom, final String motDePasse) {
		final Session session = this.getSessionFactory().getCurrentSession();
		final Criteria criteria = session.createCriteria(Apprenant.class, "apprenant");
		criteria.add(Restrictions.eq("apprenant.nom", nom));
		// TODO ajouter mot de passe et prenom

		List<Object> resultList = criteria.list();
		if (resultList != null && !resultList.isEmpty()) {
			return (Apprenant) resultList.get(0);
		}
        return null;
    }

}
