package mooc.moteur;

import java.util.List;

import mooc.dto.LigneBacSableDto;
import mooc.utils.Constants;

import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.connector.Connector;
import org.primefaces.model.diagram.connector.StraightConnector;

/**
 * Classe abstraite pour les générateurs d'exercices
 *
 * @author colas
 *
 */
public abstract class Generateur {

	/**
	 * Attributs
	 */
	private DefaultDiagramModel exercice;
	private Connector connecteur;

	protected List<LigneBacSableDto> table;

	public Generateur() {
		this.exercice = new DefaultDiagramModel();
		this.exercice.setMaxConnections(-1);
		this.connecteur = new StraightConnector();
		this.connecteur.setPaintStyle("{strokeStyle:'#404a4e', lineWidth:3}");
		this.connecteur.setHoverPaintStyle("{strokeStyle:'#20282b'}");
	}

	public abstract void generer(final List<String> portes, final boolean drag, final int type);

	public DefaultDiagramModel getExercice() {
		return this.exercice;
	}

	public void setExercice(final DefaultDiagramModel exercice) {
		this.exercice = exercice;
	}

	/**
	 * Vérifie que l'entree et la porte sont deja dans le modèle avant de creer
	 * la connexion allant de l'entree a la porte
	 *
	 * @param entree
	 * @param porte
	 */
	public void connectEntreeToPorte(final Valeur entree, final Porte porte) {
		if (!this.getExercice().getElements().contains(porte)) {
			this.getExercice().addElement(porte);
		}
		if (!this.getExercice().getElements().contains(entree)) {
			this.getExercice().addElement(entree);
		}

		entree.addSortie(porte, false);
		porte.addEntree(porte);

		this.getExercice().connect(new Connection(entree.getEndPoints().get(0), porte.getEndPoints().get(0), this.connecteur));
	}

	public void connectPorteToPorte(final Porte porteE, final Porte porteS) {
		if (!this.getExercice().getElements().contains(porteE)) {
			this.getExercice().addElement(porteE);
		}
		if (!this.getExercice().getElements().contains(porteS)) {
			this.getExercice().addElement(porteS);
		}

		porteE.addSortie(porteS, false);
		porteS.addEntree(porteE);

		this.getExercice().connect(new Connection(porteE.getEndPoints().get(1), porteS.getEndPoints().get(0), this.connecteur));
	}

	public void connectPorteToSortie(final Porte porte, final Valeur sortie) {
		if (!this.getExercice().getElements().contains(porte)) {
			this.getExercice().addElement(porte);
		}
		if (!this.getExercice().getElements().contains(sortie)) {
			this.getExercice().addElement(sortie);
		}

		porte.addSortie(sortie, false);
		sortie.addEntree(porte);

		this.getExercice().connect(new Connection(porte.getEndPoints().get(1), sortie.getEndPoints().get(0), this.connecteur));
	}

	public Connector getConnecteur() {
		return this.connecteur;
	}

	public void setConnecteur(final Connector connecteur) {
		this.connecteur = connecteur;
	}

	public List<LigneBacSableDto> getTable() {
		return this.table;
	}

	public void setTable(final List<LigneBacSableDto> table) {
		this.table = table;
	}

	public Boolean calculSortieSolution(final DefaultDiagramModel root) {
		return null;
	}

	public Boolean calculSortieUtilisateur(final DefaultDiagramModel root) throws Exception {
		return null;
	}

	public boolean valider(final DefaultDiagramModel root) {
		return false;
	}

	public boolean convertToBoolean(final String entree) {
		if ("1".equalsIgnoreCase(entree)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean calculPorte(final Boolean entree1, final Boolean entree2, final String porte){
		if (entree1 != null && entree2 != null) {
			// Operation binaire
			if (Constants.AND.equalsIgnoreCase(porte)) {
				return entree1 && entree2;
			} else if (Constants.NAND.equalsIgnoreCase(porte)) {
				return !(entree1 && entree2);
			} else if (Constants.OR.equalsIgnoreCase(porte)) {
				return entree1 || entree2;
			} else if (Constants.NOR.equalsIgnoreCase(porte)) {
				return !(entree1 || entree2);
			} else if (Constants.XOR.equalsIgnoreCase(porte)) {
				return entree1 ^ entree2;
			} else if (Constants.XNOR.equalsIgnoreCase(porte)) {
				return !(entree1 ^ entree2);
			}
		} else if (entree1 != null) {
			if (Constants.NOT.equalsIgnoreCase(porte)) {
				return !entree1;
			} else {
				return entree1;
			}
		}
		return false;
	}

	public boolean calculPorte(final Boolean entree1, final Boolean entree2, final Boolean entree3, final String porte1, final String porte2){
		Boolean entreeInter = this.calculPorte(entree1, entree2, porte1);
		return this.calculPorte(entreeInter, entree3, porte2);
	}

	public boolean calculDiffPorte(final Boolean entree1, final Boolean entree2, final String porte, final String porteReel){
		boolean sortieUtilisateur = this.calculPorte(entree1, entree2, porte);
		boolean sortieSolution = this.calculPorte(entree1, entree2, porteReel);
		if(sortieSolution == sortieUtilisateur){
			return true;
		}
		return false;
	}

	public boolean calculDiffPorte(final Boolean entree1, final Boolean entree2, final Boolean entree3,
			final String porte1, final String porte2, final String porteReel1, final String porteReel2){
		boolean sortieUtilisateur = this.calculPorte(entree1, entree2, entree3, porte1, porte2);
		boolean sortieSolution = this.calculPorte(entree1, entree2, entree3, porteReel1, porteReel2);
		if(sortieSolution == sortieUtilisateur){
			return true;
		}
		return false;
	}
}
