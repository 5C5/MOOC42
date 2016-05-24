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
			porte.setStyleClass(Constants.PORTE);
		} else {
			porte.setStyleClass(Constants.PORTE_NOT);
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
		entree1.setStyleClass(Constants.ENTREE);

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
			entree2.setStyleClass(Constants.ENTREE);
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
		sortieUtilisateur.setStyleClass(Constants.SORTIE_UTILISATEUR);

		/* Creation de la sortie "Solution", qui affiche ce que le vrai circuit ferait */
		sortieSolution = new Valeur("0", false, true);
		sortieSolution.setData("0");
		sortieSolution.setX("80em");
		sortieSolution.setY("10em");
		sortieSolution.setDraggable(drag);
		sortieSolution.setStyleClass(Constants.SORTIE_SOLUTION);


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

		if (entree1 != null && entree2 != null) {
			System.out.println("Calcul sortie utilisateur : "+entree1+" "+porte +" "+entree2);
			// Operation binaire
			if (Constants.AND.equalsIgnoreCase(porte)) {
				return entree1 && entree2;
			} else if (Constants.OR.equalsIgnoreCase(porte)) {
				return entree1 || entree2;
			} else if (Constants.XOR.equalsIgnoreCase(porte)) {
				return entree1 ^ entree2;
			}
		} else if (entree1 != null) {
			System.out.println("Calcul sortie solution : "+entree1+" "+porte);
			if (Constants.NOT.equalsIgnoreCase(porte)) {
				return !entree1;
			} else {
				return entree1;
			}
		}

		return null;
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

		if (entree1 != null && entree2 != null) {
			System.out.println("Calcul sortie solution : "+entree1+" "+porte +" "+entree2);
			// Operation binaire
			if (Constants.AND.equalsIgnoreCase(porte)) {
				return entree1 && entree2;
			} else if (Constants.OR.equalsIgnoreCase(porte)) {
				return entree1 || entree2;
			} else if (Constants.XOR.equalsIgnoreCase(porte)) {
				return entree1 ^ entree2;
			}
		} else if (entree1 != null) {
			if (Constants.NOT.equalsIgnoreCase(porte)) {
				return !entree1;
			} else {
				return entree1;
			}
		}

		return null;
	}
}
