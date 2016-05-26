package mooc.moteur;

import java.util.List;

import mooc.utils.Constants;

import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;

public class GenerateurMoyen extends Generateur {

	public GenerateurMoyen() {
		super();
	}

	/**
	 * Methode generant un exercice de difficulte moyenne (deux portes)
	 */
	@Override
	public void generer(final List<String> portes, final boolean drag) {
		String porteAlea1 = null;
		String porteAlea2 = null;

		boolean binaire1 = true;
		boolean binaire2 = true;

		if (portes.contains(Constants.NOT)) {
			portes.add(Constants.EMPTY);
		}

		/* Tirage du premier nombre aleatoire */
		int alea = (int) (Math.random() * portes.size());
		porteAlea1 = portes.get(alea);
		if (Constants.NOT.equalsIgnoreCase(porteAlea1)|| Constants.EMPTY.equalsIgnoreCase(porteAlea1)) {
			binaire1 = false;
		}
		/* Tirage du second nombre aleatoire */
		int alea2 = (int) (Math.random() * portes.size());
		porteAlea2 = portes.get(alea2);
		if(!binaire1){
			while(Constants.NOT.equalsIgnoreCase(porteAlea2)|| Constants.EMPTY.equalsIgnoreCase(porteAlea2)) {
				// si la premiere porte est unaire, la seconde est obligatoirement binaire
				alea2 = (int) (Math.random() * portes.size());
				porteAlea2 = portes.get(alea2);
				binaire2 = true;
			}
		} else if (Constants.NOT.equalsIgnoreCase(porteAlea2)|| Constants.EMPTY.equalsIgnoreCase(porteAlea2)) {
			binaire2 = false;
		}
		// System.out.println(Math.random() * portes.size());
		// System.out.println("alea 1 " + alea + ", alea 2 " + alea2);
		// System.out.println("porte 1 " + porteAlea1 + ", porte 2 " +
		// porteAlea2);

		if(binaire1 && binaire2){
			// Creation du modele 1 : contient trois entrees et deux portes binaires
			this.creerModele1(porteAlea1, porteAlea2, drag);
		} else if(!binaire1 && binaire2){
			// Creation du modele 2 : contient deux entrees et deux portes (la premiere unaire et la seconde binaire)
			this.creerModele2(porteAlea1, porteAlea2, drag);
		} else if(binaire1 && !binaire2){
			// Creation du modele 3 : contient deux entrees et deux portes (la premiere binaire et la seconde unaire)
			this.creerModele3(porteAlea1, porteAlea2, drag);
		}


	}

	/**
	 * Creation du modele 1, contenant trois entrees et deux portes binaires
	 *
	 * @param porteAlea1
	 * @param porteAlea2
	 * @param drag
	 */
	private void creerModele1(final String porteAlea1, final String porteAlea2, final boolean drag) {
		// Modele 1 contient trois entrees et deux portes binaires
		Porte porte1 = null;
		Porte porte2 = null;
		Valeur entree1 = null;
		Valeur entree2 = null;
		Valeur entree3 = null;
		Valeur sortieUtilisateur = null;
		Valeur sortieSolution = null;

		/* Creation de la porte 1 */
		porte1 = new Porte("", porteAlea1);
		porte1.setData(porteAlea1);
		porte1.setX("40em");
		porte1.setY("10em");
		porte1.setDraggable(drag);
		porte1.setStyleClass(Constants.PORTE);
		this.getExercice().addElement(porte1);

		/* Creation de la porte 2 */
		porte2 = new Porte("", porteAlea2);
		porte2.setData(porteAlea2);
		porte2.setX("60em");
		porte2.setY("20em");
		porte2.setDraggable(drag);
		porte2.setStyleClass(Constants.PORTE);
		this.getExercice().addElement(porte2);

		/* Creation de l'entree une */
		entree1 = new Valeur("0", true, false);
		entree1.setData("0");
		entree1.setX("20em");
		entree1.setY("5em");
		entree1.setDraggable(drag);
		entree1.setStyleClass(Constants.ENTREE);
		this.getExercice().addElement(entree1);
		/* Ajout de la connexion entre l'entree 1 et la porte 1 */
		this.connectEntreeToPorte(entree1, porte1);

		/* Creation de l'entree deux */
		entree2 = new Valeur("0", true, false);
		entree2.setData("0");
		entree2.setX("20em");
		entree2.setY("15em");
		entree2.setDraggable(drag);
		entree2.setStyleClass(Constants.ENTREE);
		this.getExercice().addElement(entree2);
		/* Ajout de la connexion entre l'entree 2 et la porte 1 */
		this.connectEntreeToPorte(entree2, porte1);

		/* Creation de l'entree trois */
		entree3 = new Valeur("0", true, false);
		entree3.setData("0");
		entree3.setX("20em");
		entree3.setY("25em");
		entree3.setDraggable(drag);
		entree3.setStyleClass(Constants.ENTREE);
		this.getExercice().addElement(entree3);
		/* Ajout de la connexion entre l'entree 2 et la porte 1 */
		this.connectEntreeToPorte(entree3, porte2);

		/* Ajout de la connexion entre la porte 1 et la porte 2 */
		this.connectPorteToPorte(porte1, porte2);


		/*
		 * Creation de la sortie mise a jour selon les changements de
		 * l'utilisateur, dite sortie utilisateur
		 */
		sortieUtilisateur = new Valeur("0", false, false);
		sortieUtilisateur.setData("0");
		sortieUtilisateur.setX("80em");
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
		sortieSolution.setX("100em");
		sortieSolution.setY("10em");
		sortieSolution.setDraggable(drag);
		sortieSolution.setStyleClass(Constants.SORTIE_SOLUTION);
		this.getExercice().addElement(sortieSolution);

		/* Creation de la connexion entre la porte 2 et la sortie utilisateur */
		this.connectPorteToSortie(porte2, sortieUtilisateur);

		/*
		 * Creation de la connexion entre la porte et la sortie solution, qui
		 * n'est pas ajoutee au modele
		 */
		porte2.addSortie(sortieSolution, true);
		sortieSolution.addEntree(porte2);

	}

	/**
	 * Creation du modele 2, contenant deux entrees et deux portes (la premiere unaire et la seconde binaire)
	 *
	 * @param porteAlea1
	 * @param porteAlea2
	 * @param drag
	 */
	private void creerModele2(final String porteAlea1, final String porteAlea2, final boolean drag) {
		Porte porte1 = null;
		Porte porte2 = null;
		Valeur entree1 = null;
		Valeur entree2 = null;
		Valeur sortieUtilisateur = null;
		Valeur sortieSolution = null;

		/* Creation de la porte 1 */
		porte1 = new Porte("", porteAlea1);
		porte1.setData(porteAlea1);
		if (Constants.EMPTY.equalsIgnoreCase(porteAlea1)) {
			porte1.setData("");
		}
		porte1.setX("40em");
		porte1.setY("10em");
		porte1.setDraggable(drag);
		porte1.setStyleClass(Constants.PORTE_NOT);
		this.getExercice().addElement(porte1);

		/* Creation de la porte 2 */
		porte2 = new Porte("", porteAlea2);
		porte2.setData(porteAlea2);
		porte2.setX("60em");
		porte2.setY("20em");
		porte2.setDraggable(drag);
		porte2.setStyleClass(Constants.PORTE);
		this.getExercice().addElement(porte2);

		/* Creation de l'entree une */
		entree1 = new Valeur("0", true, false);
		entree1.setData("0");
		entree1.setX("20em");
		entree1.setY("5em");
		entree1.setDraggable(drag);
		entree1.setStyleClass(Constants.ENTREE);
		this.getExercice().addElement(entree1);

		/* Creation de l'entree deux */
		entree2 = new Valeur("0", true, false);
		entree2.setData("0");
		entree2.setX("20em");
		entree2.setY("15em");
		entree2.setDraggable(drag);
		entree2.setStyleClass(Constants.ENTREE);
		this.getExercice().addElement(entree2);

		/* Ajout de la connexion entre l'entree 1 et la porte 1 */
		this.connectEntreeToPorte(entree1, porte1);
		/* Ajout de la connexion entre la porte 1 et la porte 2 */
		this.connectPorteToPorte(porte1, porte2);
		/* Ajout de la connexion entre l'entree 2 et la porte 2 */
		this.connectEntreeToPorte(entree2, porte2);


		/*
		 * Creation de la sortie mise a jour selon les changements de
		 * l'utilisateur, dite sortie utilisateur
		 */
		sortieUtilisateur = new Valeur("0", false, false);
		sortieUtilisateur.setData("0");
		sortieUtilisateur.setX("80em");
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
		sortieSolution.setX("100em");
		sortieSolution.setY("10em");
		sortieSolution.setDraggable(drag);
		sortieSolution.setStyleClass(Constants.SORTIE_SOLUTION);
		this.getExercice().addElement(sortieSolution);

		/* Creation de la connexion entre la porte 2 et la sortie utilisateur */
		this.connectPorteToSortie(porte2, sortieUtilisateur);

		/*
		 * Creation de la connexion entre la porte et la sortie solution, qui
		 * n'est pas ajoutee au modele
		 */
		porte2.addSortie(sortieSolution, true);
		sortieSolution.addEntree(porte2);

	}

	/**
	 * Creation du modele 3, contenant deux entrees et deux portes (la premiere binaire et la seconde unaire)
	 *
	 * @param porteAlea1
	 * @param porteAlea2
	 * @param drag
	 */
	private void creerModele3(final String porteAlea1, final String porteAlea2, final boolean drag) {
		Porte porte1 = null;
		Porte porte2 = null;
		Valeur entree1 = null;
		Valeur entree2 = null;
		Valeur sortieUtilisateur = null;
		Valeur sortieSolution = null;

		/* Creation de la porte 1 */
		porte1 = new Porte("", porteAlea1);
		porte1.setData(porteAlea1);
		porte1.setX("40em");
		porte1.setY("10em");
		porte1.setDraggable(drag);
		porte1.setStyleClass(Constants.PORTE);
		this.getExercice().addElement(porte1);

		/* Creation de la porte 2 */
		porte2 = new Porte("", porteAlea2);
		porte2.setData(porteAlea2);
		if (Constants.EMPTY.equalsIgnoreCase(porteAlea2)) {
			porte2.setData("");
		}
		porte2.setX("60em");
		porte2.setY("20em");
		porte2.setDraggable(drag);
		porte2.setStyleClass(Constants.PORTE_NOT);
		this.getExercice().addElement(porte2);

		/* Creation de l'entree une */
		entree1 = new Valeur("0", true, false);
		entree1.setData("0");
		entree1.setX("20em");
		entree1.setY("5em");
		entree1.setDraggable(drag);
		entree1.setStyleClass(Constants.ENTREE);
		this.getExercice().addElement(entree1);

		/* Creation de l'entree deux */
		entree2 = new Valeur("0", true, false);
		entree2.setData("0");
		entree2.setX("20em");
		entree2.setY("15em");
		entree2.setDraggable(drag);
		entree2.setStyleClass(Constants.ENTREE);
		this.getExercice().addElement(entree2);

		/* Ajout de la connexion entre l'entree 1 et la porte 1 */
		this.connectEntreeToPorte(entree1, porte1);
		/* Ajout de la connexion entre l'entree 2 et la porte 1 */
		this.connectEntreeToPorte(entree2, porte1);
		/* Ajout de la connexion entre la porte 1 et la porte 2 */
		this.connectPorteToPorte(porte1, porte2);

		/*
		 * Creation de la sortie mise a jour selon les changements de
		 * l'utilisateur, dite sortie utilisateur
		 */
		sortieUtilisateur = new Valeur("0", false, false);
		sortieUtilisateur.setData("0");
		sortieUtilisateur.setX("80em");
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
		sortieSolution.setX("100em");
		sortieSolution.setY("10em");
		sortieSolution.setDraggable(drag);
		sortieSolution.setStyleClass(Constants.SORTIE_SOLUTION);
		this.getExercice().addElement(sortieSolution);

		/* Creation de la connexion entre la porte 2 et la sortie utilisateur */
		this.connectPorteToSortie(porte2, sortieUtilisateur);

		/*
		 * Creation de la connexion entre la porte et la sortie solution, qui
		 * n'est pas ajoutee au modele
		 */
		porte2.addSortie(sortieSolution, true);
		sortieSolution.addEntree(porte2);

	}

	@Override
	public Boolean calculSortieUtilisateur(final DefaultDiagramModel root) {
		int modele = 0;
		String porte1 = null;
		String porte2 = null;
		Boolean entree1 = null;
		Boolean entree2 = null;
		Boolean entree3 = null;

		Boolean binaire1 = null;
		Boolean binaire2 = null;

		for (Element el : root.getElements()) {
			String style = el.getStyleClass();
			String data = (String) el.getData();
			if (Constants.ENTREE.equalsIgnoreCase(style)) {
				if (entree1 == null) {
					entree1 = this.convertToBoolean(data);
				} else if(entree2 == null){
					entree2 = this.convertToBoolean(data);
				} else {
					entree3 = this.convertToBoolean(data);
				}
			} else if (Constants.PORTE.equalsIgnoreCase(style)) {
				if(porte1 == null){
					porte1 = data;
					binaire1 = true;
				} else {
					porte2 = data;
					binaire2 = true;
				}
			} else if (Constants.PORTE_NOT.equalsIgnoreCase(style)) {
				if(porte1 == null){
					porte1 = data;
					binaire1 = false;
				} else {
					porte2 = data;
					binaire2 = false;
				}
			}
		}

		// Reconnaissance du modele
		if(binaire1 && binaire2){
			modele = 1;
		} else if(!binaire1 && binaire2){
			modele = 2;
		} else if(binaire1 && !binaire2){
			modele = 3;
		}

		if(modele == 1){
			Boolean entreeInter = this.calculPorte(entree1, entree2, porte1);
			return this.calculPorte(entreeInter, entree3, porte2);
		} else if(modele == 2){
			Boolean entreeInter = this.calculPorte(entree1, null, porte1);
			return this.calculPorte(entreeInter, entree2, porte2);
		} else if(modele == 3){
			Boolean entreeInter = this.calculPorte(entree1, entree2, porte1);
			return this.calculPorte(entreeInter, null, porte2);
		}

		return null;
	}

	@Override
	public Boolean calculSortieSolution(final DefaultDiagramModel root) {
		int modele = 0;
		String porte1 = null;
		String porte2 = null;
		Boolean entree1 = null;
		Boolean entree2 = null;
		Boolean entree3 = null;
		Porte porteReel1 = null;
		Porte porteReel2 = null;

		Boolean binaire1 = null;
		Boolean binaire2 = null;

		for (Element el : root.getElements()) {
			String style = el.getStyleClass();
			String data = (String) el.getData();
			if (Constants.ENTREE.equalsIgnoreCase(style)) {
				if (entree1 == null) {
					entree1 = this.convertToBoolean(data);
				} else if(entree2 == null){
					entree2 = this.convertToBoolean(data);
				} else {
					entree3 = this.convertToBoolean(data);
				}
			} else if (Constants.PORTE.equalsIgnoreCase(style)) {
				if(porte1 == null){
					porteReel1 = (Porte) el;
					porte1 = porteReel1.getRealValue();
					binaire1 = true;
				} else {
					porteReel2 = (Porte) el;
					porte2 = porteReel2.getRealValue();
					binaire2 = true;
				}
			} else if (Constants.PORTE_NOT.equalsIgnoreCase(style)) {
				if(porte1 == null){
					porteReel1 = (Porte) el;
					porte1 = porteReel1.getRealValue();
					binaire1 = false;
				} else {
					porteReel2 = (Porte) el;
					porte2 = porteReel2.getRealValue();
					binaire2 = false;
				}
			}
		}


		// Reconnaissance du modele
		if(binaire1 && binaire2){
			modele = 1;
		} else if(!binaire1 && binaire2){
			modele = 2;
		} else if(binaire1 && !binaire2){
			modele = 3;
		}

		if(modele == 1){
			Boolean entreeInter = this.calculPorte(entree1, entree2, porte1);
			return this.calculPorte(entreeInter, entree3, porte2);
		} else if(modele == 2){
			Boolean entreeInter = this.calculPorte(entree1, null, porte1);
			return this.calculPorte(entreeInter, entree2, porte2);
		} else if(modele == 3){
			Boolean entreeInter = this.calculPorte(entree1, entree2, porte1);
			return this.calculPorte(entreeInter, null, porte2);
		}

		return null;
	}
}
