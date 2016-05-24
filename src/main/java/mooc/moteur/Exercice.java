package mooc.moteur;

import java.util.ArrayList;
import java.util.List;

import mooc.dto.NotionDto;
import mooc.utils.Constants;

import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;

/**
 * Classe de génération d'exercice
 *
 * @author colas
 *
 */
public class Exercice {

	/** Ensemble de donnees */
	private DefaultDiagramModel root;

	/** Generateur */
	private Generateur generateur;

	/** Liste des notions a inserer dans l'exercice */
	private List<NotionDto> notions;

	/** Niveau de difficulte de l'exercice */
	private int difficulte;

	/** Constructeur vide */
	public Exercice() {

	}

	/** Constructeur avec arguments */
	public Exercice(final List<NotionDto> notions, final int difficulte) {
		this.notions = notions;
		if (difficulte > 3 || difficulte == 3) {
			this.difficulte = 3;
			this.generateur = new GenerateurDifficile();
		} else if (difficulte < 1 || difficulte == 1) {
			this.difficulte = 1;
			this.generateur = new GenerateurFacile();
		} else {
			this.difficulte = difficulte;
			this.generateur = new GenerateurMoyen();
		}

	}

	/**
	 * Lance le générateur (remplit l'exercice)
	 */
	public void generer() {
		this.generateur.generer(this.convertNotion(), false);
		this.root = this.generateur.getExercice();
	}

	private ArrayList<String> convertNotion() {
		ArrayList<String> notionsExercice = new ArrayList<String>();
		for (NotionDto dto : this.notions) {
			notionsExercice.add(dto.getNom());
		}
		return notionsExercice;
	}

	/**
	 * Met à jour les sorties
	 */
	public void majSortie() {
		int x = this.root.getElements().size();
		// Le dernière élément est la sortie Solution, on la met donc à jour
		// selon le vrai circuit
		((Valeur) this.root.getElements().get(x - 1)).majValeur(true);
		// L'avant dernière est la sortie utilisateur, on la met donc à jour
		// selon le circuit utilisateur
	}

	public DefaultDiagramModel getRoot() {
		return this.root;
	}

	public void setRoot(final DefaultDiagramModel root) {
		this.root = root;
	}

	public Generateur getGenerateur() {
		return this.generateur;
	}

	public void setGenerateur(final Generateur generateur) {
		this.generateur = generateur;
	}

	public List<NotionDto> getNotions() {
		return this.notions;
	}

	public void setNotions(final List<NotionDto> notions) {
		this.notions = notions;
	}

	public int getDifficulte() {
		return this.difficulte;
	}

	public void setDifficulte(final int difficulte) {
		this.difficulte = difficulte;
		if (this.difficulte == 1) {
			this.setGenerateur(new GenerateurFacile());
		} else if (this.difficulte == 2) {
			this.setGenerateur(new GenerateurMoyen());
		} else if (this.difficulte == 3) {
			this.setGenerateur(new GenerateurDifficile());
		}
	}

	public void setNotionSelected(final String[] selectedNotions, final List<NotionDto> notions){
		this.notions = new ArrayList<NotionDto>();
		for(int i=0; i<selectedNotions.length; i++){
			NotionDto toAdd = notions.get(Integer.parseInt(selectedNotions[i])-1);
			this.notions.add(toAdd);
		}
	}

	@Override
	public String toString(){
		String s = "";
		s+="Niveau : "+this.difficulte+"\n";
		s += "Notions : ";
		for (NotionDto notion : this.notions) {
			s += notion.getNom() + " ";
		}
		s += "\n";
		return s;
	}

	public String switchData(final Element el) {
		String style = el.getStyleClass();
		String data = (String) el.getData();
		if (Constants.ENTREE.equalsIgnoreCase(style)) {
			// Si entree, switch entre 0 et 1
			if ("1".equalsIgnoreCase(data)) {
				return "0";
			} else {
				return "1";
			}
		} else if (Constants.PORTE.equalsIgnoreCase(style)) {
			// Si porte, switch entre les portes possibles
			int i = 0;
			for (NotionDto notion : this.notions) {
				if (notion.getNom().equalsIgnoreCase(data)) {
					// On prend la notion suivante
					if (i < this.notions.size() - 1) {
						return this.notions.get(i + 1).getNom();
					} else {
						return this.notions.get(0).getNom();
					}
				}
				i++;
			}
		} else if (Constants.PORTE_NOT.equalsIgnoreCase(style)) {
			// Si porte not, switch entre not et vide
			if (Constants.NOT.equalsIgnoreCase(data)) {
				return "";
			} else {
				return Constants.NOT;
			}
		}
		return null;
	}

	public Boolean calculSortieSolution(final DefaultDiagramModel root) {
		return this.generateur.calculSortieSolution(root);
	}

	public Boolean calculSortieUtilisateur(final DefaultDiagramModel root) {
		return this.generateur.calculSortieUtilisateur(root);
	}
}
