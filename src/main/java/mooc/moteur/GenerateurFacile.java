package mooc.moteur;

import java.util.List;

import mooc.utils.Constants;

public class GenerateurFacile extends Generateur {

	public GenerateurFacile() {
		super();
	}

	@Override
	public void generer(final List<String> portes, final boolean drag) {
		Porte porte = null;
		Valeur entree1 = null;
		Valeur entree2 = null;
		Valeur sortieUtilisateur = null;
		Valeur sortieSolution = null;

		boolean binaire = true;

		if (portes.contains(Constants.NOT)) {
			portes.add(Constants.EMPTY);
		}

		/* Tirage du nombre aleatoire */
		int alea = (int) Math.random() * portes.size();
		String porteAlea = portes.get(alea);
		if (Constants.NOT.equalsIgnoreCase(porteAlea) || Constants.EMPTY.equalsIgnoreCase(porteAlea)){
			binaire = false;
		}

		/* Creation de la porte */
		porte = new Porte("", porteAlea);
		porte.setData(porteAlea);
		porte.setX("40em");
		porte.setY("10em");
		porte.setDraggable(drag);
		if (binaire) {
			porte.setStyleClass("porte");
		} else {
			porte.setStyleClass("porteNot");
		}
		System.out.println("porte alea : " + porte.getRealValue());
		System.out.println("porte label : " + porte.getLabel());

		/* Creation de l'entree une */
		entree1 = new Valeur("0", true, false);
		entree1.setData("0");
		if (binaire) {
			entree1.setX("20em");
			entree1.setY("5em");
		} else {
			entree1.setX("20em");
			entree1.setY("10em");
		}
		entree1.setDraggable(drag);
		entree1.setStyleClass("entree");

		/* Ajout de la connexion entre l'entree 1 et la porte */
		if (entree1 != null && porte != null) {
			this.connectEntreeToPorte(entree1, porte);
		}

		/* Creation de l'entree deux */
		if (binaire) {
			entree2 = new Valeur("0", true, false);
			entree2.setData("0");
			entree2.setX("20em");
			entree2.setY("15em");
			entree2.setDraggable(drag);
			entree2.setStyleClass("entree");
			this.getExercice().addElement(entree2);
			/* Creation de la connexion entre la porte et la sortie */
			if(entree2 != null && porte != null){
				this.connectEntreeToPorte(entree2, porte);
			}
		}

		/* Creation de la sortie mise a jour selon les changements de l'utilisateur, dite sortie utilisateur*/
		sortieUtilisateur = new Valeur("0", false, false);
		sortieUtilisateur.setData("0");
		sortieUtilisateur.setX("60em");
		sortieUtilisateur.setY("10em");
		sortieUtilisateur.setDraggable(drag);

		/* Creation de la sortie "Solution", qui affiche ce que le vrai circuit ferait */
		sortieSolution = new Valeur("0", false, true);
		sortieSolution.setData("0");
		sortieSolution.setX("80em");
		sortieSolution.setY("10em");
		sortieSolution.setDraggable(drag);


		/* Creation de la connexion entre la porte et la sortie utilisateur */
		if(porte != null && sortieUtilisateur != null){
			this.connectPorteToSortie(porte, sortieUtilisateur);
		}

		/* Creation de la connexion entre la porte et la sortie solution, qui n'est pas ajoutee au modele*/
		if (porte != null && sortieSolution != null) {
			porte.addSortie(sortieSolution, true);
			sortieSolution.addEntree(porte);
			this.getExercice().addElement(sortieSolution);
		}

	}
}
