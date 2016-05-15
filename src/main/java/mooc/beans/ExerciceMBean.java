package mooc.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.diagram.Element;
import org.springframework.context.annotation.Scope;

import lombok.Getter;
import lombok.Setter;
import mooc.login.AbstractMBean;

@Getter
@Setter
@ManagedBean
@Scope("view")
public class ExerciceMBean extends AbstractMBean implements Serializable{

    /**
     * ID
     */
    private static final long serialVersionUID = -8124356110804029220L;

    private Element root;

    @PostConstruct
    public void init() {

    }

    public Element getRoot() {
        return this.root;
    }

    public void setRoot(final Element root) {
        this.root = root;
    }
}
