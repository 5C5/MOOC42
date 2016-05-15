package moteur;

import java.util.List;

import org.primefaces.model.diagram.DefaultDiagramModel;

/**
 * Classe de génération d'exercice
 * 
 * @author colas
 *
 */
public class Exercice {

	/**
	 * Ensemble de donnée
	 */
	private DefaultDiagramModel exo;

	/** Generateur */
	private Generateur generateur;

	/** Liste des notions à insérer dans l'exercice */
	private List<String> notions;
	/* Niveau de difficulté de l'exercice */
	private int difficulte;

	/** Constructeur avec arguments */
	public Exercice(List<String> notions, int difficulte) {
		this.notions = notions;
		if (difficulte > 3 || difficulte == 3) {
			this.difficulte = 3;
			generateur = new GenerateurDifficile();
		} else if (difficulte < 1 || difficulte == 1) {
			this.difficulte = 1;
			generateur = new GenerateurFacile();
		} else {
			this.difficulte = difficulte;
			generateur = new GenerateurMoyen();
		}

	}

	/**
	 * Lance le générateur (remplit l'exercice)
	 */
	public void generer() {
		this.generateur.generer(notions);
		this.exo = this.generateur.getExercice();
	}

	/**
	 * Met à jour les sorties
	 */
	public void majSortie() {
		int x = this.exo.getElements().size();
		// Le dernière élément est la sortie Solution, on la met donc à jour
		// selon le vrai circuit
		((Valeur) this.exo.getElements().get(x - 1)).majValeur(true);
		// L'avant dernière est la sortie utilisateur, on la met donc à jour
		// selon le circuit utilisateur
	}


}
