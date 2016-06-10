package mooc.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mooc.dao.NotionDAO;
import mooc.dto.NotionDto;
import mooc.model.Notion;
import mooc.service.NotionService;
import mooc.utils.Constants;

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

	@Override
	public List<NotionDto> getAll() {
		List<Notion> notions = this.notionDAO.getAll();
		List<NotionDto> toReturn = new ArrayList<NotionDto>();
		for (Notion notion : notions) {
			NotionDto dto = new NotionDto();
			dto.setId(notion.getIdNotion());
			dto.setNom(notion.getNomNotion());
			toReturn.add(dto);
		}
		return toReturn;
	}

	public NotionDAO getNotionDAO() {
		return this.notionDAO;
	}

	public void setNotionDAO(final NotionDAO notionDAO) {
		this.notionDAO = notionDAO;
	}

	@Override
	public NotionDto getByLibelle(final String libelle) {
		Notion notion = this.notionDAO.getByLibelle(libelle);
		NotionDto dto = new NotionDto();
		dto.setId(notion.getIdNotion());
		dto.setNom(notion.getNomNotion());
		return dto;
	}

	@Override
	public List<NotionDto> getPortesFondamentales() {
		NotionDto notionOr = this.getByLibelle(Constants.OR);
		NotionDto notionAnd = this.getByLibelle(Constants.AND);
		NotionDto notionNot = this.getByLibelle(Constants.NOT);
		List<NotionDto> notions = new ArrayList<NotionDto>();
		notions.add(notionOr);
		notions.add(notionAnd);
		notions.add(notionNot);
		return notions;
	}

	@Override
	public List<NotionDto> getPortesComplexes() {
		NotionDto notionNand = this.getByLibelle(Constants.NAND);
		NotionDto notionNor = this.getByLibelle(Constants.NOR);
		NotionDto notionXor = this.getByLibelle(Constants.XOR);
		NotionDto notionXnor = this.getByLibelle(Constants.XNOR);
		List<NotionDto> notions = new ArrayList<NotionDto>();
		notions.add(notionNand);
		notions.add(notionNor);
		notions.add(notionXor);
		notions.add(notionXnor);
		return notions;
	}

}
