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
    public Valeur(final String label, final boolean modifiable) {
        super(label, modifiable);
        if (modifiable) {
            this.addEndPoint(this.createEndPoint(EndPointAnchor.RIGHT));
        } else {
            this.addEndPoint(this.createEndPoint(EndPointAnchor.LEFT));
        }
        this.inOut = new ArrayList<Node>();
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


}
