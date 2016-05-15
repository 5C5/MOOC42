package mooc.moteur;

import java.util.List;

public class GenerateurDifficile extends Generateur {

    public GenerateurDifficile() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void generer(final List<String> portes) {
        if (portes.size() < 2) {
            throw new IllegalArgumentException(
                    "Pour un exercice de difficulté difficile, il doit y avoir au moins deux portes");
        }

    }
}
