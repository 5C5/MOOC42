package mooc.moteur;

import java.util.List;

import org.primefaces.model.diagram.Connection;

import mooc.utils.Constants;

public class GenerateurMoyen extends Generateur {

	public GenerateurMoyen() {
		super();
	}

	/**
	 * Méthode générant un exercice de difficulté moyenne (deux portes)
	 */
	@Override
	public void generer(final List<String> portes) {
		if (portes.size() < 2) {
			throw new IllegalArgumentException(
					"Pour un exercice de difficulté moyenne, il doit y avoir au moins deux portes");
		}

		/* Si la porte NOT est présente, on ajoute la porte EMPTY (vide) */
		if (portes.contains(Constants.NOT)) {
			portes.add(Constants.EMPTY);
		}

		/* La première porte logique est choisie aléatoirement, et une ou deux entrées y sont ajoutées */
		Porte porte1 = new Porte("", portes.get((int) Math.random() * portes.size()));
		Valeur entree1 = new Valeur("0", true);

		/*On ajoute porte1 et entree1 au modèle*/
		this.getExercice().addElement(entree1);
		this.getExercice().addElement(porte1);

		/* On crée la connexion entre la porte1 et l'entree 1*/
		Connection c11 = porte1.addEntree(entree1);
		/*Ajout de la connexion au modèle*/
		this.getExercice().connect(c11);

		/*Création, connexion et ajout au modèle de l'entree2 si besoin est*/
		if (porte1.getLabel() != Constants.NOT && porte1.getLabel() != Constants.EMPTY) {
			Valeur entree2 = new Valeur("0", true);
			Connection c12 = porte1.addEntree(entree2);
			this.getExercice().connect(c12);
			this.getExercice().addElement(entree2);
		}

		/** Pour la seconde porte, on retire les portes unaires */
		if (portes.contains(Constants.NOT)) {
			portes.remove(Constants.NOT);
			portes.remove(Constants.EMPTY);
		}

		/* On choisit aléatoirement la seconde porte et son entree supplementaire, plus ajout au modèle*/
		Porte porte2 = new Porte("", portes.get((int) Math.random() * portes.size()));
		Valeur entree3 = new Valeur("0", true);
		this.getExercice().addElement(porte2);
		this.getExercice().addElement(entree3);
		Connection c21 = porte1.addEntree(entree3);
		this.getExercice().connect(c21);

		/* On branche la porte 2 en sortie de la porte 1 */
		Connection c22 = porte1.addSortie(porte2, false);
		this.getExercice().connect(c22);

		/* On crée les sorties utilisateurs et solutions, on les branches et on les ajoutes au modèles*/
		Valeur sortieUtilisateur = new Valeur("0", false);
		Valeur sortieSolution = new Valeur("0", false);
		Connection cSU = porte2.addSortie(sortieUtilisateur, false);
		this.getExercice().connect(cSU);
		Connection cSol = porte2.addSortie(sortieSolution, true);

	}
}
