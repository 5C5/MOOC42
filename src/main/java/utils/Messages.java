package utils;

import java.util.ResourceBundle;

/**
 * The Class Messages.
 *
 */
public class Messages {

    /**
     * Instantiates a new messages.
     */
    private Messages() {
    }

    /** The resource bundle. */
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("fr");

    /**
     * Message.
     *
     * @param keyChamp the key champ
     * @param args the args
     * @return the string
     */
    public static String message(final String keyChamp, final Object... args) {
        String pattern = Messages.resourceBundle.getString(keyChamp);
        return MessageFormatter.instance().format(pattern, args);
    }

}
