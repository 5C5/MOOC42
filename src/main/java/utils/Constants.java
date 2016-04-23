package utils;

/**
 * Constantes.
 *
 * @author xavier.bourdaud
 */
public final class Constants {

	/** authentification LDAP */
	public static final String MESSAGE_ERREUR = "La combinaison identifiant/mot de passe est incorrecte.";
	/** authentification LDAP */
	public static final String MESSAGE_ERREUR_NO_PROFIL = "L'utilisateur ne poss�de aucun profil";
	/** authentification LDAP */
	public static final String MESSAGE_ERREUR_PROFIL = "L'utilisateur doit poss�der un et un seul profil";

	/** Constante utilisée pour identifier l'instance de l'utilisateur connecté en session. */
	public static final String UTILISATEUR_CONNECTE = "authUser";

	/**
	 * Formats date par défaut.
	 */
	public static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";
	/** Constant <code>DEFAULT_TIME_FORMAT="dd/MM/yyyy HH:mm:ss"</code> */
	public static final String DEFAULT_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
	/** Constant <code>DEFAULT_ONLY_TIME_FORMAT="HH:mm"</code> */
	public static final String DEFAULT_ONLY_TIME_FORMAT = "HH:mm";
	/** Constant <code>DATE_FORMAT_FILE_RAPPORT="yyyy-MM-dd"</code> */
	public static final String DATE_FORMAT_FILE_RAPPORT = "yyyy-MM-dd";

	/** Key session pour stocker une ligne selectionne */
	public static final String KEY_LIGNE_SELECTIONNE = "keyLigneSelectionne";
	/** Key session pour stocker des listes de données */
	public static final String KEY_LISTE = "keyListe";
	/** Key session pour stocker le numéro de vacation */
	public static final String KEY_NUM_VACATION = "keyNumVacation";
	/** Key session pour stocker le numéro de sous-vacation */
	public static final String KEY_NUM_SOUS_VACATION = "keyNumSousVacation";
	/** Key session pour stocker les criteres de recherche */
	public static final String KEY_CRITERE = "keyCritere";
	/** Key session pour stocker le numéro de lot */
	public static final String KEY_NUMERO = "keyNumero";

	/** Key session pour stocker le mode de l'edition */
	public static final String KEY_MODE = "keyMode";
	/** Constant création */
	public static final String MODE_CREATION = "creation";
	/** Constant modification */
	public static final String MODE_MODIFICATION = "modification";
	/** Constant consultation */
	public static final String MODE_CONSULTATION = "consultation";
	/** Constant ENCODING pour exporter CSV */
	public static final String ENCODING = "ISO-8859-15";

	/** Constant le nom du fichier pour copier */
	public static final String JOB_FILENAME = "jobFileName";

	/** Constant le path pour copier */
	public static final String JOB_PATH = "jobPath";

	/** Constant le path copie */
	public static final String JOB_PATH_COPY = "jobPathCopy";

	/** Constant la liste du nom de fichier copie - step */
	public static final String STEP_LIST_FILENAME = "stepListFileName";

	/** Constant la liste de la path copie */
	public static final String STEP_LIST_PATH = "stepListsPath";

	/** Constant la liste des path copie */
	public static final String STEP_LIST_PATH_COPY = "stepListsPathCopy";

	/** Formats date du nom des fichier PDF générations. */
	public static final String DATE_FORMAT_FILENAME_PDF = "yyyyMMdd_HHmmssSSS";

	/** Constant initialisation du numéro de séquence. */
	public static final int INIT_NUM_SEQUENCE_COMPOSANT = 10;

	/** Constante associée à l'unité "UN" */
	public static final String UNITE_UN = "UN";

	/** Constante OUI */
	public static final String OUI = "Oui";
	/** Constante NON */
	public static final String NON = "Non";

	/**
	 * Non instantiable
	 */
	private Constants() {
	}

}
