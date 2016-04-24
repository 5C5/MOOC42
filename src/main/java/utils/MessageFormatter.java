package utils;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Effectue le remplacement des paramètres dans un message. Exemple le message "a{0}c" associé au paramètre "B" donne "aBc".
 *
 */
public final class MessageFormatter {

    /** The Constant LOG. */
    private static final Log LOG = LogFactory.getLog(MessageFormatter.class);

    /** The Constant INSTANCE. */
    private static final MessageFormatter INSTANCE = new MessageFormatter();

    /** The Constant REGULAR_EXP_ARGS. */
    private static final Pattern REGULAR_EXP_ARGS = Pattern.compile("\\{(\\d+)\\}");

    /**
     * Instance.
     *
     * @return the message formatter
     */
    public static MessageFormatter instance() {
        return MessageFormatter.INSTANCE;
    }

    // ******************** fin des déclarations statiques ********************

    /**
     * Constructeur privé pour ne pas pour voir l'implémenter car seulement la classe gère que des méthodes statiques.
     */
    private MessageFormatter() {
    }

    /**
     * Format.
     *
     * @param pattern the pattern
     * @param args the args
     * @return the string
     */
    public String format(final String pattern, final Object... args) {
        Matcher matcher = MessageFormatter.REGULAR_EXP_ARGS.matcher(pattern);
        StringBuilder sb = new StringBuilder();
        int lastAddedIdx = 0;
        while (matcher.find()) {
            sb.append(pattern.substring(lastAddedIdx, matcher.start()));
            int indexArg = Integer.parseInt(matcher.group(1));
            Object arg = this.extractedArgument(args, indexArg, pattern);
            sb.append(String.valueOf(arg));
            lastAddedIdx = matcher.end();
        }
        sb.append(pattern.substring(lastAddedIdx));
        return sb.toString();
    }

    /**
     * Extracted argument.
     *
     * @param args the args
     * @param indexArg the index arg
     * @param pattern the pattern
     * @return the object
     */
    private Object extractedArgument(final Object[] args, final int indexArg, final String pattern) {
        try {
            return args[indexArg];
        } catch (IndexOutOfBoundsException e) {
            MessageFormatter.LOG.warn(
                    "Impossible de récupérer le paramètre d'index " + indexArg + " pour le pattern : " + pattern + ". Les seules paramètres disponibles sont " + Arrays.toString(args), e);
            return "{" + indexArg + "}";
        }
    }

}
