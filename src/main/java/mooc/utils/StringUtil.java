package mooc.utils;

/**
 * Classe utilitaire de gestion des chaines de caractères
 *
 */
public final class StringUtil {

	/** Constructeur privé pour ne pas pour voir l'implémenter car seulement la classe gère que des méthodes statiques */
	private StringUtil() {

	}

	public static String nullToEmpty(final String string) {
		if (string == null) {
			return "";
		}
		return string;
	}

	public static boolean isNotEmpty(final String string) {
		if (string == null) {
			return false;
		}
		return !string.isEmpty();
	}

	/**
	 * Convertir un Boolean avec la chaine de caractère "oui" ou "non" paramétré dans le fichier ApplicationRessource.properties
	 *
	 * @param bool Boolean à convertir
	 * @param valeurParDefaut Valeur par défaut souhaité si le Boolean est null
	 * @return libellé "oui" si <code>true</code>, libellé de "non" si <code>false</code>
	 */
	public static String convertirBooleanOuiNon(final Boolean bool, final String valeurParDefaut) {

		if (bool == null) {
			return valeurParDefaut;
		} else {
			if (bool) {
				return Constants.OUI;
			} else {
				return Constants.NON;
			}
		}
	}

	/**
	 * Convertir un Integer en chaine de caractère
	 *
	 * @param val Valeur à convertir
	 * @return "" si null sinon la chaine de conversion
	 */
	public static String convertirInteger(final Integer val) {
		if (val == null) {
			return "";
		} else {
			return val.toString();
		}
	}

	/**
	 * Convertir un Long en chaine de caractère
	 *
	 * @param val Valeur à convertir
	 * @return "" si null sinon la chaine de conversion
	 */
	public static String convertirLong(final Long val) {
		if (val == null) {
			return "";
		} else {
			return val.toString();
		}
	}

	/**
	 * Convertir un double en une chaîne de caractère en enlevant la partie décimale si la valeur est entière (300.0 -> 300)
	 *
	 * @param val
	 * @return "" si null sinon la chaine de conversion
	 */
	public static String convertirDoubleGestionSupprPointSiEntier(final Double val) {
		if (val != null) {

			long valInt = (long) (double) val;
			if (Double.compare(val, valInt) == 0) {
				return String.format("%s", new Object[] { valInt });
			} else {
				return String.format("%s", new Object[] { val });
			}
		}
		return "";
	}
}
