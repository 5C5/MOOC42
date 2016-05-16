package mooc.moteur;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.diagram.endpoint.EndPointAnchor;

import lombok.Data;

@Data
public class Valeur extends Node {

    private final List<Node> inOut;

    /**
     * Constructeur hérité de la classe mère
     *
     * @param label -> valeur (0 ou 1) du noeud
     * @param modifiable -> définit s'il s'aggit d'une entrée ou d'une sortie
     */
	public Valeur(final String label, final boolean modifiable, boolean solution) {
		super(label);
		if (!solution) {
			if (modifiable) {
				this.addEndPoint(this.createEndPoint(EndPointAnchor.RIGHT));
			} else {
				this.addEndPoint(this.createEndPoint(EndPointAnchor.LEFT));
			}
		}
        this.inOut = new ArrayList<Node>();
		this.setX("10cm");
		this.setY("10cm");
    }

    /**
     * Constructeur hérité de la classe mère
     *
     * @param label
     */
    public Valeur(final String label) {
        super(label);
        this.addEndPoint(this.createEndPoint(EndPointAnchor.AUTO_DEFAULT));
        this.inOut = new ArrayList<Node>();
		this.setX("10cm");
		this.setY("10cm");
    }

    /**
     *
     */
    private static final long serialVersionUID = -5708144318935103564L;

    @Override
    public boolean getValeur() {
        if (this.getLabel() == "1") {
            return true;
        }
        return false;
    }

    /**
     * Mettre à jour la valeur en fonction
     */
    @Override
    public void majValeur(final boolean vraieValeur) {
        if (!this.isDraggable()) {
            if (vraieValeur) {
                if (((Porte) this.inOut.get(0)).getVraieValeur()) {
                    this.setData("1");
                } else {
                    this.setData("0");
                }
            } else {
                if (((Porte) this.inOut.get(0)).getValeur()) {
                    this.setData("1");
                } else {
                    this.setData("0");
                }
            }
        }
    }

	@Override
	public void addEntree(Node entree) {
		this.inOut.add(entree);
	}

	@Override
	public void addSortie(Node sortie, boolean solution) {
		this.inOut.add(sortie);
	}

}
