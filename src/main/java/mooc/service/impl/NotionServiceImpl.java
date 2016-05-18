package mooc.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mooc.dao.NotionDAO;
import mooc.model.Notion;
import mooc.service.NotionService;

import org.springframework.beans.factory.annotation.Autowired;

public class NotionServiceImpl implements NotionService, Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 8399574624050114298L;

	/** DAO Connaissance */
	@Autowired
	private NotionDAO notionDAO;

	@Override
	public List<String> getAllLibelle() {
		List<Notion> notions = this.notionDAO.getAll();
		List<String> libelles = new ArrayList<String>();
		for (Notion notion : notions) {
			libelles.add(notion.getNomNotion());
		}
		return libelles;
	}

	public NotionDAO getNotionDAO() {
		return this.notionDAO;
	}

	public void setNotionDAO(final NotionDAO notionDAO) {
		this.notionDAO = notionDAO;
	}

}
