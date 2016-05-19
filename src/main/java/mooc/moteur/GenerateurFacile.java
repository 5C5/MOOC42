package mooc.moteur;

import java.util.List;

import mooc.utils.Constants;

public class GenerateurFacile extends Generateur {

	public GenerateurFacile() {
		super();
	}

	@Override
	public void generer(final List<String> portes) {
		if (portes.contains(Constants.NOT)) {
			portes.add(Constants.EMPTY);
		}

		/* Création de la porte */
		Porte porte = new Porte("", portes.get((int) Math.random() * portes.size()));
		porte.setX("40em");
		porte.setY("10em");
		porte.setDraggable(true);
		porte.setStyleClass("porte");
		System.out.println("porte alea : " + porte.getRealValue());

		/* Création de l'entrée une */
		Valeur entree1 = new Valeur("0", true, false);
		entree1.setX("20em");
		entree1.setY("5em");
		entree1.setDraggable(true);
		entree1.setStyleClass("entree");

		/* Ajout de la connexion entre l'entrée 1 et la porte */
		this.connectEntreeToPorte(entree1, porte);

		/* Creation de l'entree deux */
		if (porte.getLabel() != Constants.NOT && porte.getLabel() != Constants.EMPTY) {
			Valeur entree2 = new Valeur("0", true, false);
			entree2.setX("20em");
			entree2.setY("15em");
			entree2.setDraggable(true);
			entree2.setStyleClass("entree");
			this.getExercice().addElement(entree2);
			/* Creation de la connexion entre la porte et la sortie */
			this.connectEntreeToPorte(entree2, porte);
		}

		/* Création de la sortie mise à jour selon les changements de l'utilisateur, dite sortie utilisateur*/
		Valeur sortieUtilisateur = new Valeur("0", false, false);
		sortieUtilisateur.setX("60em");
		sortieUtilisateur.setY("10em");
		sortieUtilisateur.setDraggable(true);
		/*
		 * Création de la sortie "Solution", qui affiche ce que le vrai circuit
		 * ferait
		 */
		Valeur sortieSolution = new Valeur("0", false, true);
		sortieSolution.setX("80em");
		sortieSolution.setY("10em");
		sortieSolution.setDraggable(true);


		/* création de la conexion entre la porte et la sortie utilisateur */
		this.connectPorteToSortie(porte, sortieUtilisateur);
		/* Création de la connexion entre la porte et la sortie solution, qui n'est pas ajoutee au modele*/
		porte.addSortie(sortieSolution, true);
		sortieSolution.addEntree(porte);
		this.getExercice().addElement(sortieSolution);

	}
}
