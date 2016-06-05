package mooc.moteur;

import java.util.List;

import mooc.dto.LigneBacSableDto;
import mooc.utils.Constants;

import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;
import org.primefaces.model.diagram.endpoint.RectangleEndPoint;

public class GenerateurBacSable extends Generateur {

	public GenerateurBacSable() {
		super();
	}

	/**
	 * Methode generant un exercice de difficulte moyenne (deux portes)
	 */
	@Override
	public void generer(final List<String> portes, final boolean drag) {
		int y = 5;
		for (LigneBacSableDto ligne : this.table) {
			if ("0".equalsIgnoreCase(ligne.getEntree())) {
				this.genererEntree(drag, 20, y);
			}
			this.analyserPorte(drag, 40, y, ligne.getCol1());
			this.analyserPorte(drag, 60, y, ligne.getCol2());
			this.analyserPorte(drag, 80, y, ligne.getCol3());
			y += 10;
		}
		this.genererSortie(drag);
	}

	private void analyserPorte(final boolean drag, final int x, final int y,
			final String porte) {
		if ("1".equalsIgnoreCase(porte)) {
			this.genererPorteUnaire(drag, x, y);
		} else if ("2".equalsIgnoreCase(porte)) {
			this.genererPorteBinaire(drag, x, y);
		}
	}

	private void genererEntree(final boolean drag, final int x, final int y) {
		Valeur entree = null;
		entree = new Valeur("0", true, false);
		entree.setData("0");
		entree.setX(x + "em");
		entree.setY(y + "em");
		entree.setDraggable(drag);
		entree.setStyleClass(Constants.ENTREE);
		DotEndPoint endPointRight = new DotEndPoint(EndPointAnchor.RIGHT);
		endPointRight.setSource(true);
		entree.addEndPoint(endPointRight);
		this.getExercice().addElement(entree);
	}

	private void genererPorteUnaire(final boolean drag, final int x, final int y) {
		Porte porte = null;
		porte = new Porte("", "");
		porte.setData("");
		porte.setX(x+"em");
		porte.setY(y + "em");
		porte.setDraggable(drag);
		porte.setStyleClass(Constants.PORTE_NOT);

		DotEndPoint endPointLeft = new DotEndPoint(EndPointAnchor.LEFT);
		endPointLeft.setTarget(true);
		endPointLeft.setMaxConnections(1);
		porte.addEndPoint(endPointLeft);
		DotEndPoint endPointRight = new DotEndPoint(EndPointAnchor.RIGHT);
		endPointRight.setSource(true);
		porte.addEndPoint(endPointRight);

		this.getExercice().addElement(porte);
	}

	private void genererPorteBinaire(final boolean drag, final int x, final int y) {
		Porte porte = null;
		porte = new Porte("", "");
		porte.setData("");
		porte.setX(x + "em");
		porte.setY(y + "em");
		porte.setDraggable(drag);
		porte.setStyleClass(Constants.PORTE);

		DotEndPoint endPointLeft = new DotEndPoint(EndPointAnchor.LEFT);
		endPointLeft.setTarget(true);
		endPointLeft.setMaxConnections(2);
		porte.addEndPoint(endPointLeft);
		DotEndPoint endPointRight = new DotEndPoint(EndPointAnchor.RIGHT);
		endPointRight.setSource(true);
		porte.addEndPoint(endPointRight);

		this.getExercice().addElement(porte);
	}

	private void genererSortie(final boolean drag) {
		Valeur sortieUtilisateur = null;
		sortieUtilisateur = new Valeur("0", false, false);
		sortieUtilisateur.setData("0");
		sortieUtilisateur.setX("100em");
		sortieUtilisateur.setY("10em");
		sortieUtilisateur.setDraggable(drag);
		sortieUtilisateur.setStyleClass(Constants.SORTIE_UTILISATEUR);

		DotEndPoint endPointLeft = new DotEndPoint(EndPointAnchor.LEFT);
		endPointLeft.setTarget(true);
		endPointLeft.setMaxConnections(1);
		sortieUtilisateur.addEndPoint(endPointLeft);

		this.getExercice().addElement(sortieUtilisateur);
	}

	private EndPoint createDotEndPoint(final EndPointAnchor anchor) {
		DotEndPoint endPoint = new DotEndPoint(anchor);
		endPoint.setScope("network");
		endPoint.setTarget(true);
		endPoint.setStyle("{fillStyle:'#98AFC7'}");
		endPoint.setHoverStyle("{fillStyle:'#5C738B'}");

		return endPoint;
	}

	private EndPoint createRectangleEndPoint(final EndPointAnchor anchor) {
		RectangleEndPoint endPoint = new RectangleEndPoint(anchor);
		endPoint.setScope("network");
		endPoint.setSource(true);
		endPoint.setStyle("{fillStyle:'#98AFC7'}");
		endPoint.setHoverStyle("{fillStyle:'#5C738B'}");

		return endPoint;
	}

	@Override
	public Boolean calculSortieUtilisateur(final DefaultDiagramModel root) {

		// Affichage de tout ^^
		for(Element el : root.getElements()){
			System.out.println(el.getId()+" "+el.getStyleClass()+" "+el.getData());
			for(EndPoint ep : el.getEndPoints()){
				System.out.println(" - "+ep.getId());
			}
			try {
				Porte porte = (Porte) el;
				for (Node node : porte.getEntrees()) {
					System.out.println(" - entree " + node.getId());
				}
			} catch (Exception e) {

			}
		}
		for(Connection c : root.getConnections()){
			System.out.println(c.getSource().getId()+" -> "+c.getTarget().getId());
		}


		return true;
	}

	private Element getElementByEndPoint(final DefaultDiagramModel root, final String idEndPoint){
		for(Element el : root.getElements()){
			for(EndPoint ep : el.getEndPoints()){
				if(ep.getId().equals(idEndPoint)){
					System.out.println(el.getId()+" "+el.getStyleClass()+" "+el.getData());
					return el;
				}
			}
		}
		return null;
	}
}
