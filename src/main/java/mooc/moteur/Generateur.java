package mooc.moteur;

import java.util.List;

import org.primefaces.model.diagram.DefaultDiagramModel;

import lombok.Data;

/**
 * Classe abstraite pour les générateurs d'exercices
 *
 * @author colas
 *
 */
@Data
public abstract class Generateur {

    /**
     * Attributs
     */
    private DefaultDiagramModel exercice;

    public Generateur() {
        this.exercice = new DefaultDiagramModel();
    }

    public abstract void generer(List<String> portes);

    public DefaultDiagramModel getExercice() {
        return this.exercice;
    }

    public void setExercice(final DefaultDiagramModel exercice) {
        this.exercice = exercice;
    }

}
