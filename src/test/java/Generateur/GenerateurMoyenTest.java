package Generateur;

import java.util.ArrayList;

import mooc.dto.NotionDto;
import mooc.moteur.Exercice;
import mooc.moteur.GenerateurMoyen;
import mooc.utils.Constants;

import org.junit.Test;
import org.primefaces.model.diagram.Element;

public class GenerateurMoyenTest {

	@Test
	public void genererModele1() {

		Exercice exercice = new Exercice();
		exercice.setNotions(new ArrayList<NotionDto>());
		GenerateurMoyen generateur = new GenerateurMoyen();
		generateur.genererModele1(Constants.AND, Constants.OR, false, true, 0);

		String idEntree1 = null;
		String idEntree2 = null;
		String idEntree3 = null;

		for (Element el : generateur.getExercice().getElements()) {
			String id = el.getId();
			String style = el.getStyleClass();
			String data = (String) el.getData();
			//			System.out.println(el.getId()+" "+el.getData()+" "+el.getStyleClass());
			if (Constants.ENTREE.equalsIgnoreCase(style)) {
				if (idEntree1 == null) {
					idEntree1 = id;
				} else if (idEntree2 == null) {
					idEntree2 = id;
				} else {
					idEntree3 = id;
				}
			}
		}

		// Entree1 -> 1
		// Entree2 -> 1
		// Entree3 -> 0
		for (Element el : generateur.getExercice().getElements()) {
			String id = el.getId();
			if(id.equals(idEntree1)){
				el.setData(exercice.switchData(el));
			} else if(id.equals(idEntree2)){
				el.setData(exercice.switchData(el));
			}
		}

		for (Element el : generateur.getExercice().getElements()) {
			System.out.println(el.getId()+" "+el.getData()+" "+el.getStyleClass());
		}

		System.out.println("Sortie utilisateur : "+generateur.calculSortieUtilisateur(generateur.getExercice()));

		System.out.println("Sortie solution : "+generateur.calculSortieSolution(generateur.getExercice()));
	}
}
