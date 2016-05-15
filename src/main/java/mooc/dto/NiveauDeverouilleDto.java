package mooc.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Dto pour gerer les niveaux deverouilles de l'apprenant
 */
@Getter
@Setter
public class NiveauDeverouilleDto {

    /** Nom */
    private String nom;
    /** Niveau */
    private int niveau;

}
