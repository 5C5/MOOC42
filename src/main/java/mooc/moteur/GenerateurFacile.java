package mooc.moteur;

import java.util.List;

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

        /* Création de la sortie mise à jour selon les changements de l'utilisateur, dite sortie utilisateur*/
        Valeur sortieUtilisateur = new Valeur("0", false);
        Valeur sortieSolution = new Valeur("0", false);

        this.getExercice().addElement(entree1);
        this.getExercice().addElement(porte);

        /* Creation de l'entree deux*/
        if (porte.getLabel() != Constants.NOT && porte.getLabel() != Constants.EMPTY) {
            Valeur entree2 = new Valeur("0", true);
        }

        /* Toujours ajouter les sorties en derniers, dans l'ordre sortie utilisateur puis sortie exercice
         * Pour permettre leur mise à jour différente dans les exercices*/
        this.getExercice().addElement(sortieUtilisateur);
        this.getExercice().addElement(sortieSolution);

    }
}
