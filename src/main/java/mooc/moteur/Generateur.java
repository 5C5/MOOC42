package mooc.moteur;

import java.util.List;

import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.connector.Connector;
import org.primefaces.model.diagram.connector.StraightConnector;

import lombok.Data;

/**
 * Classe abstraite pour les générateurs d'exercices
 *
 * @author colas
 *
 */
@Data
public abstract class Generateur {

    /**
     * Attributs
     */
    private DefaultDiagramModel exercice;
	private Connector connecteur;

    public Generateur() {
        this.exercice = new DefaultDiagramModel();
		this.exercice.setMaxConnections(-1);
		connecteur = new StraightConnector();
		connecteur.setPaintStyle("{strokeStyle:'#404a4e', lineWidth:3}");
		connecteur.setHoverPaintStyle("{strokeStyle:'#20282b'}");
    }

    public abstract void generer(List<String> portes);

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
	public void connectEntreeToPorte(Valeur entree, Porte porte) {
		if (!this.getExercice().getElements().contains(porte))
			this.getExercice().addElement(porte);
		if (!this.getExercice().getElements().contains(entree))
			this.getExercice().addElement(entree);

		entree.addSortie(porte, false);
		porte.addEntree(porte);

		this.getExercice().connect(new Connection(entree.getEndPoints().get(0), porte.getEndPoints().get(0), connecteur));
	}

	public void connectPorteToPorte(Porte porteE, Porte porteS) {
		if (!this.getExercice().getElements().contains(porteE))
			this.getExercice().addElement(porteE);
		if (!this.getExercice().getElements().contains(porteS))
			this.getExercice().addElement(porteS);

		porteE.addSortie(porteS, false);
		porteS.addEntree(porteE);

		this.getExercice().connect(new Connection(porteE.getEndPoints().get(1), porteS.getEndPoints().get(0), connecteur));
	}

	public void connectPorteToSortie(Porte porte, Valeur sortie) {
		if (!this.getExercice().getElements().contains(porte))
			this.getExercice().addElement(porte);
		if (!this.getExercice().getElements().contains(sortie))
			this.getExercice().addElement(sortie);

		porte.addSortie(sortie, false);
		sortie.addEntree(porte);

		this.getExercice().connect(new Connection(porte.getEndPoints().get(1), sortie.getEndPoints().get(0), connecteur));
	}

}
