package mooc.moteur;

import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;

import lombok.Data;

/**
 * Classe semi abstraite des noeuds
 *
 * @author colas
 *
 */
@Data
public abstract class Node extends Element {

    /**
     * ID
     */
    private static final long serialVersionUID = -575980082032669766L;



    public abstract boolean getValeur();

    public abstract void majValeur(boolean vraieValeur);

	public abstract void addEntree(Node entree);

	public abstract void addSortie(Node sortie, boolean solution);

    /**
     * Constructeur avec juste le label visible
     *
     * @param label
     */
    public Node(final String label) {
        super(label);
    }

    /**
     * Constructeur avec le sélecteur de type de valeur (modifiable ou non)
     *
     * @param label
     * @param entree
     */
    public Node(final String label, final boolean modifiable) {
        super(label);
        this.setDraggable(modifiable);
    }

    /**
     * Petite simplification pour la création d'EndPoint (pour éviter de
     * repasser tous les paramètres)
     *
     * @param anchor
     * @return
     */
    protected EndPoint createEndPoint(final EndPointAnchor anchor) {
        DotEndPoint endPoint = new DotEndPoint(anchor);
        endPoint.setStyle("{fillStyle:'#404a4e'}");
        endPoint.setHoverStyle("{fillStyle:'#20282b'}");

        return endPoint;
    }

    /**
     * Override implicite du getter getData pour conversion automatique
     *
     * @return label
     */
    public String getLabel() {
        return (String) this.getData();
    }

}
