package login;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;
import utils.Constants;

/**
 * Classe d'abstraction des beans jsf regroupant diverses méthodes utiles pour les beans JSF de l'application.
 */
public abstract class AbstractMBean implements Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = 5317543083482002972L;

    /**
     * Mode d'affichage des écrans (consultation/modification)
     *
     * @see Constants#MODE_CONSULTATION
     * @see Constants#MODE_MODIFICATION
     */
    @Getter
    @Setter
    private String modeAffichage;

    /**
     * Ajoute un message au contexte JSF.
     *
     * @param clientId l'identifiant du client dans lequel le message doit être affiché, ou <code>null</code> pour un message global
     * @param severity la sévérité {@link Severity} du message
     * @param codeMsgTitre la clé du message titre
     * @param codeMsgDetail la clé du message détaillé
     * @param args les éventuels paramètres à passer aux deux messages
     */
    protected void addFacesMessage(final String clientId, final Severity severity, final String codeMsgTitre, final String codeMsgDetail, final Object... args) {
        final FacesContext fc = FacesContext.getCurrentInstance();
        final FacesMessage message = new FacesMessage(severity, codeMsgTitre, codeMsgDetail);
        fc.addMessage(clientId, message);
    }

    /**
     * Ajoute un message au contexte JSF.
     *
     * @param severity la sévérité {@link Severity} du message
     * @param codeMsgTitre la clé du message titre
     * @param keepMessages true pour garder le message après un redirect
     */
    protected void addFacesMessage(final Severity severity, final String codeMsgTitre, final boolean keepMessages) {
        this.addFacesMessage(severity, codeMsgTitre, "", keepMessages);
    }

    /**
     * Ajoute un message au contexte JSF.
     *
     * @param severity la sévérité {@link Severity} du message
     * @param codeMsgTitre la clé du message titre
     */
    protected void addFacesMessage(final Severity severity, final String codeMsgTitre) {
        this.addFacesMessage(severity, codeMsgTitre, "", false);
    }

    /**
     * Ajoute un message au contexte JSF.
     *
     * @param severity la sévérité {@link Severity} du message
     * @param codeMsgTitre la clé du message titre
     * @param codeMsgDetail la clé du message détaillé
     */
    protected void addFacesMessage(final Severity severity, final String codeMsgTitre, final String codeMsgDetail) {
        this.addFacesMessage(severity, codeMsgTitre, codeMsgDetail, false);
    }

    /**
     * Ajoute un message au contexte JSF.
     *
     * @param severity la sévérité {@link Severity} du message
     * @param codeMsgTitre la clé du message titre
     * @param codeMsgDetail la clé du message détaillé
     * @param keepMessages true pour garder le message après un redirect
     */
    protected void addFacesMessage(final Severity severity, final String codeMsgTitre, final String codeMsgDetail, final boolean keepMessages) {
        final FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage(severity, codeMsgTitre, codeMsgDetail));
        if (keepMessages) {
            fc.getExternalContext().getFlash().setKeepMessages(true);
        }
    }

    protected String getLangue() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String langue = (String) request.getSession().getAttribute(Constants.UTILISATEUR_LANGUE);
        if (langue == null || "".equals(langue)) {
            // Langue par d�faut : fr
            langue = "fr";
        }
        return langue;
    }

}
