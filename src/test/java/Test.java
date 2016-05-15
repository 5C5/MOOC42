import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import mooc.model.Apprenant;


/**
 *
 * @author colas
 *
 *         Classe de test
 */
public class Test {

	@org.junit.Test
	public void testSetupMapping() {

		Apprenant p = new Apprenant("marie", "rancon");

		EntityManager em = Persistence.createEntityManagerFactory("persistenceUnit").createEntityManager();

		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
	}
}
