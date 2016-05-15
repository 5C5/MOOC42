package mooc.moteur;

import java.util.List;

import mooc.utils.Constants;

public class GenerateurMoyen extends Generateur {

    public GenerateurMoyen() {
        // TODO
    }

    @Override
    public void generer(final List<String> portes) {
        if (portes.size() < 2) {
            throw new IllegalArgumentException(
                    "Pour un exercice de difficulté moyenne, il doit y avoir au moins deux portes");
        }

        /** Si la porte NOT est présente, on ajoute la porte EMPTY (vide) */
        if (portes.contains(Constants.NOT)) {
            portes.add(Constants.EMPTY);
        }

        /**
         * La première porte logique est choisie aléatoirement, et une ou deux
         * entrées y sont ajoutées
         */
        // Porte porte1 = new Porte(portes.get((int) Math.random() *
        // portes.size()));
        // Valeur entree1 = new Valeur("0", true);
        // porte1.addNode(entree1);
        // if (porte1.getLabel() != Constants.NOT && porte1.getLabel() !=
        // Constants.EMPTY) {
        // Valeur entree2 = new Valeur("0", true);
        // porte1.addNode(entree2);
        // }
        // /** Pour la seconde porte, on retire les portes unaires */
        // if (portes.contains(Constants.NOT)) {
        // portes.remove(Constants.NOT);
        // portes.remove(Constants.EMPTY);
        // }
        //
        // /** On choisit aléatoirement la seconde porte, et on la connecte aux
        // entrées*/
        // Porte porte2 = new Porte(portes.get((int) Math.random() *
        // portes.size()));
        // Valeur entree3 = new Valeur("0", true);
        // porte2.addNode(entree3);
        // porte2.addNode(porte1);
        // this.getRootSolution().addNode(porte2);
    }
}
