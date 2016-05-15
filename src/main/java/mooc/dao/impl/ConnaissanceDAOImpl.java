package mooc.dao.impl;

import java.util.List;

import mooc.dao.ConnaissanceDAO;
import mooc.model.Connaissance;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;


/**
 * The Class ConnaissanceDAOImpl.
 */
public class ConnaissanceDAOImpl extends AbstractDAO<Connaissance> implements ConnaissanceDAO {

    @Override
    public Connaissance getById(final int id) {
        Session session = this.getSession();
        Transaction tx = session.beginTransaction();

        final Criteria criteria = session.createCriteria(Connaissance.class, "connaissance");
        criteria.add(Restrictions.eq("connaissance.idConnaissance", id));

        List<Object> resultList = criteria.list();
        Connaissance connaissance = null;

        if (resultList != null && !resultList.isEmpty()) {
            connaissance = (Connaissance) resultList.get(0);
        }

        tx.commit();
        return connaissance;
    }

    @Override
    public void save(final Connaissance connaissance) {
        Session session = null;
        try {
            session = this.getSession();
            session.beginTransaction();
            session.saveOrUpdate(connaissance);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List<Connaissance> loadConnaissance(final int idApprenant) {
        Session session = this.getSession();
        Transaction tx = session.beginTransaction();

        final Criteria criteria = session.createCriteria(Connaissance.class, "connaissance");
        criteria.createAlias("connaissance.apprenant", "apprenant");
        criteria.add(Restrictions.eq("apprenant.idApprenant", idApprenant));

        List<Connaissance> resultList = criteria.list();
        tx.commit();

        return resultList;
    }

}
