package mooc.utils;

/**
 * Constantes.
 *
 * @author colas.picard
 */
public final class Constants {

    /** Constante utilisée pour identifier l'instance de l'utilisateur connecté en session. */
    public static final String UTILISATEUR_CONNECTE = "authUser";

    /** Langue de l'utilisateur */
    public static final String UTILISATEUR_LANGUE = "langue";
    /** Langue par défaut */
    public static final String LANGUE_DEFAUT = "fr";
    /** Theme par defaut */
    public static final String THEME_DEFAUT = "le-frog";

    /** Constante OUI */
    public static final String OUI = "oui";
    /** Constante NON */
    public static final String NON = "non";

    /** Porte OU */
    public static final String OU_LIB = "OU / OR";
    /** Porte ET */
    public static final String ET_LIB = "ET / AND";
    /** Porte NOT */
    public static final String NOT = "NOT";
    /** Porte XOR */
    public static final String XOR = "XOR";

    /**
     * Non instantiable
     */
    private Constants() {
    }

}
