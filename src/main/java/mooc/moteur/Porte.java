package moteur;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;

import lombok.Data;
import utils.Constants;

/**
 * Classe structure pour les portes
 * 
 * @author colas
 *
 */
@Data
public class Porte extends Node {

	/**
	 * Constraintes locales pour faciliter la récupération des Endpoint d'entrée
	 * et de sortie
	 */
	private final static int ENTREES = 0;
	private final static int SORTIE = 1;

	/**
	 * ID
	 */
	private static final long serialVersionUID = 7492047521264421882L;

	/** Liste des valeurs de portes disponibles pour changer le label */
	private List<String> listePortes;

	/** Valeur réelle du noeud, cachée au public */
	private String realValue;

	/** Sortie, soit une Valeur, soit une porte */
	private Node sortie;

	/** Liste des entrees (portes ou valeurs) */
	private List<Node> entrees;

	/**
	 * Constructeur simple
	 */
	public Porte(String label) {
		super(label);
		this.listePortes = new ArrayList<String>();
		if (!Constants.isLogicGate(label)) {
			throw new IllegalArgumentException(
					"La chaine de caractère passée en paramètre doit être une porte logique");
		}
		this.addEndPoint(this.createEndPoint(EndPointAnchor.LEFT));
		this.addEndPoint(this.createEndPoint(EndPointAnchor.RIGHT));
	}

	/**
	 * Nouveau constructeur
	 */
	public Porte(String label, String vraieValeur) {
		super(label);
		this.listePortes = new ArrayList<String>();
		if (!Constants.isLogicGate(label)) {
			throw new IllegalArgumentException(
					"La chaine de caractère passée en paramètre doit être une porte logique");
		}
		this.realValue = vraieValeur;
	}


	/**
	 * Méthode surchargée récupérant la valeur du noeud sous forme de booleen
	 * 
	 * @return True si "1", false sinon
	 */
	@Override
	public boolean getValeur() {

		/* Stockage temporaires des valeurs du fils 1 et du fils 2 */
		boolean fils1 = entrees.get(0).getValeur();
		boolean fils2 = false;
		if (entrees.size() > 1)
			fils2 = entrees.get(1).getValeur();

		/* Cas ET */
		if (this.getLabel() == Constants.AND) {
			return fils1 && fils2;
		}
		/* Cas OU */
		else if (this.getLabel() == Constants.OR) {
			return fils1 || fils2;
		}
		/* Cas NOT */
		else if (this.getLabel() == Constants.NOT) {
			return !fils1;
		}
		/* Case EMPTY */
		else if (this.getLabel() == Constants.EMPTY) {
			return fils1;
		}
		/* Case XOR */
		else if (this.getLabel() == Constants.XOR) {
			return fils1 != fils2;
		}
		return false;
	}

	/**
	 * Ajoute la sortie de la porte (pouvant être une Valeur ou une porte) en
	 * créant et renvoyant la connexion créé pour ajout au model
	 * 
	 * @param sortie
	 */
	public Connection addSortie(Node sortie) {
		Connection c = new Connection(this.getEndPoints().get(SORTIE), sortie.getEndPoints().get(ENTREES));
		this.sortie = sortie;
		((Porte) sortie).addEntreeWithoutConnection(this);
		return c;

	}

	/**
	 * Ajoute une entree(Porte ou Valeur) à la porte en créant la connexion et
	 * en la renvoyant pour ajout au model
	 * 
	 * @param entree Noeud à ajouter aux entrées
	 * @return la connexion créée
	 */
	public Connection addEntree(Node entree) {
		Connection c = new Connection();
		return c;
	}

	/**
	 * AJoute une entrée dans la liste sans creer de connexion supplémentaire
	 * 
	 * @param entree Noeud à ajouter
	 */
	public void addEntreeWithoutConnection(Node entree) {
		this.entrees.add(entree);
	}

	/**
	 * Retourne la valeur à renvoyer en fonction de la véritable valeur de la
	 * porte
	 * 
	 * @return
	 */
	public boolean getVraieValeur() {
		/* Stockage temporaires des valeurs du fils 1 et du fils 2 */
		boolean fils1 = entrees.get(0).getValeur();
		boolean fils2 = false;
		if (entrees.size() > 1)
			fils2 = entrees.get(1).getValeur();

		/* Cas ET */
		if (this.realValue == Constants.AND) {
			return fils1 && fils2;
		}
		/* Cas OU */
		else if (this.realValue == Constants.OR) {
			return fils1 || fils2;
		}
		/* Cas NOT */
		else if (this.realValue == Constants.NOT) {
			return !fils1;
		}
		/* Case EMPTY */
		else if (this.realValue == Constants.EMPTY) {
			return fils1;
		}
		/* Case XOR */
		else if (this.realValue == Constants.XOR) {
			return fils1 != fils2;
		}
		return false;
	}

	/**
	 * Ne rien faire dans le cas d'une porte
	 */
	@Override
	public void majValeur(boolean vraieValeur) {/* Do Nothing */}

}
