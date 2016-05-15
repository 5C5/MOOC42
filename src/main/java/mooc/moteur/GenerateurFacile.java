package mooc.moteur;

import java.util.List;

import org.primefaces.model.diagram.Connection;

import mooc.utils.Constants;

public class GenerateurFacile extends Generateur {

	public GenerateurFacile() {
		super();
	}

	@Override
	public void generer(final List<String> portes) {
		if (portes.size() < 1) {
			throw new IllegalArgumentException(
					"Pour un exercice de difficulté facile, il doit y avoir au moins une porte");
		}

		if (portes.contains(Constants.NOT)) {
			portes.add(Constants.EMPTY);
		}

		/* Création de la porte */
		Porte porte = new Porte("", portes.get((int) Math.random() * portes.size()));

		/* Création de l'entrée une */
		Valeur entree1 = new Valeur("0", true);

		/* Ajout de la connexion entre l'entrée 1 et la porte */
		Connection c1 = porte.addEntree(entree1);
		this.getExercice().connect(c1);

		/* Création de la sortie mise à jour selon les changements de l'utilisateur, dite sortie utilisateur*/
		Valeur sortieUtilisateur = new Valeur("0", false);
		/*
		 * Création de la sortie "Solution", qui affiche ce que le vrai circuit
		 * ferait
		 */
		Valeur sortieSolution = new Valeur("0", false);

		/* création de la conexion entre la porte et la sortie utilisateur */
		Connection cSU = porte.addSortie(sortieUtilisateur, false);
		/* AJout de la connexion au modèle (elle doit apparaître)*/
		this.getExercice().connect(cSU);
		/* Création de la connexion entre la porte et la sortie solution, qui n'est pas ajoutee au modele*/
		Connection cSOL = porte.addSortie(sortieSolution, true);

		this.getExercice().addElement(entree1);
		this.getExercice().addElement(porte);

		/* Creation de l'entree deux*/
		if (porte.getLabel() != Constants.NOT && porte.getLabel() != Constants.EMPTY) {
			Valeur entree2 = new Valeur("0", true);
			/* Creation de la connexion entre la porte et la sortie */
			Connection c2 = porte.addEntree(entree2);
			/* Ajout au modèle */
			this.getExercice().connect(c2);
		}

		/* Toujours ajouter les sorties en derniers, dans l'ordre sortie utilisateur puis sortie exercice
		 * Pour permettre leur mise à jour différente dans les exercices*/
		this.getExercice().addElement(sortieUtilisateur);
		this.getExercice().addElement(sortieSolution);

		/* Ajout des connexions */


	}
}
