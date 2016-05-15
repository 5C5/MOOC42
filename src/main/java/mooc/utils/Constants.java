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

	/**
	 * ********************* Portes Logiques ******************
	 */
	/** Et */
	public static final String AND = "AND";
	/** OU */
	public static final String OR = "OR";
	/** Negation */
	public static final String NOT = "NOT";
	/** Ou exclusif */
	public static final String XOR = "XOR";
	/** Vide, ne fait rien */
	public static final String EMPTY = "EMPTY";

	public static boolean isLogicGate(String gate) {

		return (gate == AND || gate == OR || gate == NOT || gate == XOR || gate == EMPTY || gate == "");
	}
    /**
     * Non instantiable
     */
    private Constants() {
    }


}
