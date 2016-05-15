package mooc.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Dto pour gerer les evolutions des competences de l'apprenant
 */
@Getter
@Setter
public class EvolutionCompetenceDto {

    /** Date */
    private String date;
    /** Niveau */
    private int niveau;
    /** Remarque */
    private String remarque;
}
