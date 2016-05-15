package mooc.dao.impl;

import java.util.List;

import mooc.dao.ApprenantDAO;
import mooc.model.Apprenant;
import mooc.model.Competence;
import mooc.model.CompetenceNotion;
import mooc.model.Connaissance;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;


/**
 * The Class ApprenantDAOImpl.
 */
// @Repository("apprenantDAO")
public class ApprenantDAOImpl extends AbstractDAO<Apprenant> implements ApprenantDAO {

    @Override
    public Apprenant getByCritere(final String nom, final String prenom, final String motDePasse) {
        Session session = this.getSession();
        Transaction tx = session.beginTransaction();

        final Criteria criteria = session.createCriteria(Apprenant.class, "apprenant");
        if(nom != null){
            criteria.add(Restrictions.eq("apprenant.nom", nom));
        }
        if(prenom != null){
            criteria.add(Restrictions.eq("apprenant.prenom", prenom));
        }
        if(motDePasse != null){
            criteria.add(Restrictions.eq("apprenant.motDePasse", motDePasse));
        }

        List<Object> resultList = criteria.list();
        Apprenant apprenant = null;

        if (resultList != null && !resultList.isEmpty()) {
            apprenant = (Apprenant) resultList.get(0);
        }

        tx.commit();
        return apprenant;
    }

    @Override
    public void save(final Apprenant apprenant) {
        Session session = null;
        try {
            session = this.getSession();
            session.beginTransaction();
            session.saveOrUpdate(apprenant);
            for (Connaissance connaissance : apprenant.getConnaissances()) {
                session.saveOrUpdate(connaissance);
            }
            for (Competence competence : apprenant.getCompetences()) {
                session.saveOrUpdate(competence);
                if (competence.getNotions() != null) {
                    for (CompetenceNotion comNotion : competence.getNotions()) {
                        session.saveOrUpdate(comNotion);
                    }
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
    public Apprenant getById(final int id) {
        Session session = this.getSession();
        Transaction tx = session.beginTransaction();

        final Criteria criteria = session.createCriteria(Apprenant.class, "apprenant");
        criteria.add(Restrictions.eq("apprenant.idApprenant", id));

        List<Object> resultList = criteria.list();
        Apprenant apprenant = null;

        if (resultList != null && !resultList.isEmpty()) {
            apprenant = (Apprenant) resultList.get(0);
        }

        tx.commit();
        return apprenant;
    }

    @Override
    public String getThemeById(final int id) {
        Session session = this.getSession();
        Transaction tx = session.beginTransaction();

        final Criteria criteria = session.createCriteria(Apprenant.class, "apprenant");
        criteria.add(Restrictions.eq("apprenant.idApprenant", id));

        List<Object> resultList = criteria.list();
        Apprenant apprenant = null;

        if (resultList != null && !resultList.isEmpty()) {
            apprenant = (Apprenant) resultList.get(0);
        }

        tx.commit();

        if (apprenant != null) {
            return apprenant.getTheme();
        }
        return null;
    }

    @Override
    public boolean isApprenantExists(final String nom, final String prenom) {
        Session session = this.getSession();
        Transaction tx = session.beginTransaction();

        final Criteria criteria = session.createCriteria(Apprenant.class, "apprenant");
        if (nom != null) {
            criteria.add(Restrictions.eq("apprenant.nom", nom));
        }
        if (prenom != null) {
            criteria.add(Restrictions.eq("apprenant.prenom", prenom));
        }

        List<Object> resultList = criteria.list();
        Apprenant apprenant = null;

        if (resultList != null && !resultList.isEmpty()) {
            apprenant = (Apprenant) resultList.get(0);
        }

        tx.commit();
        if (apprenant == null) {
            return false;
        } else {
            return true;
        }

    }

    @Override
    public List<Connaissance> loadConnaissance(final Apprenant apprenant) {
        Session session = this.getSession();
        Transaction tx = session.beginTransaction();

        final Criteria criteria = session.createCriteria(Connaissance.class, "connaissance");
        if (apprenant != null) {
            criteria.createAlias("connaissance.apprenant", "apprenant");
            criteria.add(Restrictions.eq("apprenant.idApprenant", apprenant.getIdApprenant()));
        }

        List<Connaissance> resultList = criteria.list();
        tx.commit();

        return resultList;
    }

    @Override
    public List<Competence> loadCompetence(final Apprenant apprenant) {
        Session session = this.getSession();
        Transaction tx = session.beginTransaction();

        final Criteria criteria = session.createCriteria(Competence.class, "connaissance");
        if (apprenant != null) {
            criteria.createAlias("connaissance.apprenant", "apprenant");
            criteria.add(Restrictions.eq("apprenant.idApprenant", apprenant.getIdApprenant()));
        }

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
