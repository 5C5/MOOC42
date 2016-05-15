package mooc.dao.impl;

import java.util.List;

import mooc.dao.CompetenceDAO;
import mooc.model.Competence;
import mooc.model.CompetenceNotion;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;


/**
 * The Class CompetenceDAOImpl.
 */
public class CompetenceDAOImpl extends AbstractDAO<Competence> implements CompetenceDAO {

    @Override
    public Competence getById(final int id) {
        Session session = this.getSession();
        Transaction tx = session.beginTransaction();

        final Criteria criteria = session.createCriteria(Competence.class, "competence");
        criteria.add(Restrictions.eq("competence.idCompetence", id));

        List<Object> resultList = criteria.list();
        Competence competence = null;

        if (resultList != null && !resultList.isEmpty()) {
            competence = (Competence) resultList.get(0);
        }

        tx.commit();
        return competence;
    }

    @Override
    public void save(final Competence competence) {
        Session session = null;
        try {
            session = this.getSession();
            session.beginTransaction();
            session.saveOrUpdate(competence);
            if (competence.getNotions() != null) {
                for (CompetenceNotion comNotion : competence.getNotions()) {
                    session.saveOrUpdate(comNotion);
                }
            }
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
    public List<Competence> loadCompetence(final int idApprenant) {
        Session session = this.getSession();
        Transaction tx = session.beginTransaction();

        final Criteria criteria = session.createCriteria(Competence.class, "connaissance");
        criteria.createAlias("connaissance.apprenant", "apprenant");
        criteria.add(Restrictions.eq("apprenant.idApprenant", idApprenant));

        List<Competence> resultList = criteria.list();
        tx.commit();

        for (Competence comp : resultList) {
            comp.setNotions(this.loadCompetenceNotion(comp));
        }
        return resultList;
    }

    private List<CompetenceNotion> loadCompetenceNotion(final Competence competence) {
        Session session = this.getSession();
        Transaction tx = session.beginTransaction();

        final Criteria criteria = session.createCriteria(CompetenceNotion.class, "compNotion");
        if (competence != null) {
            criteria.createAlias("compNotion.competence", "competence");
            criteria.add(Restrictions.eq("competence.idCompetence", competence.getIdCompetence()));
        }

        List<CompetenceNotion> resultList = criteria.list();
        tx.commit();
        return resultList;
    }


}
