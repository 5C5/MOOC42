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

	/** Role admin */
	public static final String ADMIN = "Admin";
	/** Role apprenant */
	public static final String APPRENANT = "Apprenant";

	/** Niveau facile */
	public static final String NIVEAU_FACILE = "Facile";
	/** Niveau moyen */
	public static final String NIVEAU_MOYEN = "Moyen";
	/** Niveau difficile */
	public static final String NIVEAU_DIFFICILE = "Difficile";

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

	public static boolean isLogicGate(final String gate) {

		return gate == Constants.AND || gate == Constants.OR || gate == Constants.NOT || gate == Constants.XOR || gate == Constants.EMPTY || gate == "";
	}
	/**
	 * Non instantiable
	 */
	private Constants() {
	}


}
