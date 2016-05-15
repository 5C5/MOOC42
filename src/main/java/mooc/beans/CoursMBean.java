package mooc.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import lombok.Data;
import lombok.NoArgsConstructor;
import mooc.login.AbstractMBean;

import org.springframework.context.annotation.Scope;

/**
 * Bean de gestion des cours
 */
@Data
@NoArgsConstructor
@ManagedBean
@Scope("view")
public class CoursMBean extends AbstractMBean implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = -7390696210036765913L;

	/** Langue de l'utilisateur */
	private String langue;

	@PostConstruct
	public void init() {
		this.langue = this.getLangue();
	}


}
