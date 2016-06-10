package mooc.dao.impl;

import java.util.List;

import mooc.dao.NotionDAO;
import mooc.model.Notion;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;


/**
 * The Class NotionDAOImpl.
 */
public class NotionDAOImpl extends AbstractDAO<Notion> implements NotionDAO {

	@Override
	public Notion getById(final int id) {
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();

		final Criteria criteria = session.createCriteria(Notion.class, "notion");
		criteria.add(Restrictions.eq("notion.idNotion", id));
		criteria.addOrder(Order.asc("notion.idNotion"));

		List<Object> resultList = criteria.list();
		Notion notion = null;

		if (resultList != null && !resultList.isEmpty()) {
			notion = (Notion) resultList.get(0);
		}

		tx.commit();
		return notion;
	}

	@Override
	public List<Notion> getAll() {
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();

		final Criteria criteria = session.createCriteria(Notion.class, "notion");
		criteria.addOrder(Order.asc("notion.idNotion"));

		List<Notion> resultList = criteria.list();

		tx.commit();
		return resultList;
	}

	@Override
	public Notion getByLibelle(final String libelle) {
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();

		final Criteria criteria = session.createCriteria(Notion.class, "notion");
		criteria.add(Restrictions.eq("notion.nomNotion", libelle));

		List<Object> resultList = criteria.list();
		Notion notion = null;

		if (resultList != null && !resultList.isEmpty()) {
			notion = (Notion) resultList.get(0);
		}

		tx.commit();
		return notion;
	}

}
