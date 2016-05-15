package mooc.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Dto utilisé pour afficher les infos pour le profil
 */
@Getter
@Setter
public class ProfilDto {

    /** Id */
    private int id;

    /** Nom */
    private String nom;
    /** Prenom */
    private String prenom;

    /** Niveaux */
    private List<NiveauDeverouilleDto> niveaux;

    /** Evolution des competences */
    private List<EvolutionCompetenceDto> evolution;

}
