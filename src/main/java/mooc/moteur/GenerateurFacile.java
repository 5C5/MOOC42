package mooc.moteur;

import java.util.List;

import mooc.utils.Constants;

import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;

public class GenerateurFacile extends Generateur {

	public GenerateurFacile() {
		super();
	}

	@Override
	public void generer(final List<String> portes, final boolean drag) {
		boolean binaire = true;

		if (portes.contains(Constants.NOT)) {
			portes.add(Constants.EMPTY);
		}

		/* Tirage du nombre aleatoire */
		int alea = (int) (Math.random() * portes.size());
		String porteAlea = portes.get(alea);
		if (Constants.NOT.equalsIgnoreCase(porteAlea) || Constants.EMPTY.equalsIgnoreCase(porteAlea)){
			binaire = false;
		}

		if (binaire) {
			this.creerModele1(porteAlea, drag);
		} else {
			this.creerModele2(porteAlea, drag);
		}

	}

	/**
	 * Création du modele 1, contenant deux entrees et une porte binaire
	 *
	 * @param porteAlea Porte unaire
	 * @param drag Draggable
	 */
	private void creerModele1(final String porteAlea, final boolean drag) {
		// Modele 1 contient une porte binaire
		Porte porte = null;
		Valeur entree1 = null;
		Valeur entree2 = null;
		Valeur sortieUtilisateur = null;
		Valeur sortieSolution = null;

		/* Creation de la porte */
		porte = new Porte("", porteAlea);
		porte.setData(porteAlea);
		porte.setX("40em");
		porte.setY("10em");
		porte.setDraggable(drag);
		porte.setStyleClass(Constants.PORTE);
		this.getExercice().addElement(porte);

		/* Creation de l'entree une */
		entree1 = new Valeur("0", true, false);
		entree1.setData("0");
		entree1.setX("20em");
		entree1.setY("5em");
		entree1.setDraggable(drag);
		entree1.setStyleClass(Constants.ENTREE);
		this.getExercice().addElement(entree1);

		/* Ajout de la connexion entre l'entree 1 et la porte */
		this.connectEntreeToPorte(entree1, porte);

		/* Creation de l'entree deux */
		entree2 = new Valeur("0", true, false);
		entree2.setData("0");
		entree2.setX("20em");
		entree2.setY("15em");
		entree2.setDraggable(drag);
		entree2.setStyleClass(Constants.ENTREE);
		this.getExercice().addElement(entree2);
		/* Creation de la connexion entre la porte et la sortie */
		this.connectEntreeToPorte(entree2, porte);

		/* Creation de la sortie mise a jour selon les changements de l'utilisateur, dite sortie utilisateur*/
		sortieUtilisateur = new Valeur("0", false, false);
		sortieUtilisateur.setData("0");
		sortieUtilisateur.setX("60em");
		sortieUtilisateur.setY("10em");
		sortieUtilisateur.setDraggable(drag);
		sortieUtilisateur.setStyleClass(Constants.SORTIE_UTILISATEUR);
		this.getExercice().addElement(sortieUtilisateur);

		/* Creation de la sortie "Solution", qui affiche ce que le vrai circuit ferait */
		sortieSolution = new Valeur("0", false, true);
		sortieSolution.setData("0");
		sortieSolution.setX("80em");
		sortieSolution.setY("10em");
		sortieSolution.setDraggable(drag);
		sortieSolution.setStyleClass(Constants.SORTIE_SOLUTION);
		this.getExercice().addElement(sortieSolution);


		/* Creation de la connexion entre la porte et la sortie utilisateur */
		this.connectPorteToSortie(porte, sortieUtilisateur);

		/* Creation de la connexion entre la porte et la sortie solution, qui n'est pas ajoutee au modele*/
		porte.addSortie(sortieSolution, true);
		sortieSolution.addEntree(porte);

	}

	/**
	 * Création du modele 2, contenant une entree et une porte uniaire
	 *
	 * @param porteAlea Porte unaire
	 * @param drag Draggable
	 */
	private void creerModele2(final String porteAlea, final boolean drag) {
		// Modele 2 contient une porte unaire
		Porte porte = null;
		Valeur entree1 = null;
		Valeur sortieUtilisateur = null;
		Valeur sortieSolution = null;

		/* Creation de la porte */
		porte = new Porte("", porteAlea);
		porte.setData(porteAlea);
		if (Constants.EMPTY.equalsIgnoreCase(porteAlea)) {
			porte.setData("");
		}
		porte.setX("40em");
		porte.setY("10em");
		porte.setDraggable(drag);
		porte.setStyleClass(Constants.PORTE_NOT);
		this.getExercice().addElement(porte);

		/* Creation de l'entree une */
		entree1 = new Valeur("0", true, false);
		entree1.setData("0");
		entree1.setX("20em");
		entree1.setY("10em");
		entree1.setDraggable(drag);
		entree1.setStyleClass(Constants.ENTREE);
		this.getExercice().addElement(entree1);

		/* Ajout de la connexion entre l'entree 1 et la porte */
		this.connectEntreeToPorte(entree1, porte);

		/*
		 * Creation de la sortie mise a jour selon les changements de
		 * l'utilisateur, dite sortie utilisateur
		 */
		sortieUtilisateur = new Valeur("0", false, false);
		sortieUtilisateur.setData("0");
		sortieUtilisateur.setX("60em");
		sortieUtilisateur.setY("10em");
		sortieUtilisateur.setDraggable(drag);
		sortieUtilisateur.setStyleClass(Constants.SORTIE_UTILISATEUR);
		this.getExercice().addElement(sortieUtilisateur);

		/*
		 * Creation de la sortie "Solution", qui affiche ce que le vrai circuit
		 * ferait
		 */
		sortieSolution = new Valeur("0", false, true);
		sortieSolution.setData("0");
		sortieSolution.setX("80em");
		sortieSolution.setY("10em");
		sortieSolution.setDraggable(drag);
		sortieSolution.setStyleClass(Constants.SORTIE_SOLUTION);
		this.getExercice().addElement(sortieSolution);

		/* Creation de la connexion entre la porte et la sortie utilisateur */
		this.connectPorteToSortie(porte, sortieUtilisateur);

		/*
		 * Creation de la connexion entre la porte et la sortie solution, qui
		 * n'est pas ajoutee au modele
		 */
		porte.addSortie(sortieSolution, true);
		sortieSolution.addEntree(porte);
	}

	@Override
	public Boolean calculSortieUtilisateur(final DefaultDiagramModel root) {
		String porte = null;
		Boolean entree1 = null;
		Boolean entree2 = null;

		for (Element el : root.getElements()) {
			String style = el.getStyleClass();
			String data = (String) el.getData();
			if (Constants.ENTREE.equalsIgnoreCase(style)) {
				if (entree1 == null) {
					entree1 = this.convertToBoolean(data);
				} else {
					entree2 = this.convertToBoolean(data);
				}
			} else if (Constants.PORTE.equalsIgnoreCase(style)) {
				porte = data;
			} else if (Constants.PORTE_NOT.equalsIgnoreCase(style)) {
				porte = data;
			}
		}

		return this.calculPorte(entree1, entree2, porte);
	}

	@Override
	public Boolean calculSortieSolution(final DefaultDiagramModel root) {
		String porte = null;
		Boolean entree1 = null;
		Boolean entree2 = null;
		Porte porteReel = null;

		for (Element el : root.getElements()) {
			String style = el.getStyleClass();
			String data = (String) el.getData();
			if (Constants.ENTREE.equalsIgnoreCase(style)) {
				if (entree1 == null) {
					entree1 = this.convertToBoolean(data);
				} else {
					entree2 = this.convertToBoolean(data);
				}
			} else if (Constants.PORTE.equalsIgnoreCase(style)) {
				porteReel = (Porte) el;
				porte = porteReel.getRealValue();
			} else if (Constants.PORTE_NOT.equalsIgnoreCase(style)) {
				porteReel = (Porte) el;
				porte = porteReel.getRealValue();
			}
		}

		return this.calculPorte(entree1, entree2, porte);
	}
}
