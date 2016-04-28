package dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.LockOptions;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.collection.spi.PersistentCollection;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Ensemble de methodes generiques d'acces a� la base de donnees.
 */
public class AbstractDAO<T> {

    /** serialVersionUID. */
    private static final long serialVersionUID = 8677286990004284805L;

    /** Type d'objet � traiter. */
    @SuppressWarnings("unchecked")
    private final Class<T> persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    /** sessionFactory. */
	@Autowired
    @Qualifier("sessionFactory")
    private transient SessionFactory sessionFactory;

    /**
     * Get Hibernate Session Factory.
     *
     * @return SessionFactory - Hibernate Session Factory
     */
    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    /**
     * getter de la session
     *
     * @return la session courante
     */
    public final Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    /**
     * Set Hibernate Session Factory.
     *
     * @param sessionFactory a {@link org.hibernate.SessionFactory} object.
     */
    public void setSessionFactory(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * afterPropertiesSet.
     *
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() {
        if (this.sessionFactory == null) {
            throw new IllegalStateException(this.getClass().getName() + " : sessionFactory est null");
        }
    }

    /**
     * M�thode g�n�rique retournant l'ensemble des donn�es d'une table.
     *
     * @return List<T> la liste des donn�es de la table
     */
    public List<T> rechercherTous() {
        final Session currentSession = this.getSession();
        final Criteria criteria = currentSession.createCriteria(this.persistentClass);

        final List<T> references = new LinkedList<T>();
        for (final Object obj : criteria.list()) {
            final T ref = this.persistentClass.cast(obj);
            references.add(ref);
        }
        return references;
    }

    /**
     * Recherche d'un objet en base par son identifiant.
     *
     * @param id L'identifiant de l'objet
     * @return L'objet
     */
    public T rechercherParIdentifiant(final Serializable id) {
        return (T) this.getSession().get(this.persistentClass, id);
    }

    /**
     * Supprime un objet en base.
     *
     * @param entity L'objet
     */
    public void supprimer(final T entity) {
        this.getSession().delete(entity);

    }

    /**
     * Cr�er un nouvel objet en base.
     *
     * @param entity L'objet
     */
    public void creer(final T entity) {
        this.getSession().save(entity);
    }

    /**
     * Save or update.
     *
     * @param entity the entity
     */
    public void saveOrUpdate(final T entity) {
        this.getSession().saveOrUpdate(entity);
    }

    /**
     * Met � jour l'objet en base.
     *
     * @param entity L'objet
     */
    public void mettreAjour(final T entity) {
        this.getSession().update(entity);
    }

    /**
     * Chargement d'un objet.
     *
     * @param objectToInitialize l'objet � initialiser
     * @return l'objet initialis��
     */
    public Object loadProperty(final Object objectToInitialize) {
        if (objectToInitialize == null) {
            return null;
        }
        if (objectToInitialize instanceof HibernateProxy) {
            try {
                this.getSession().buildLockRequest(LockOptions.NONE).lock(objectToInitialize);
            } catch (NonUniqueObjectException exp) {
                Object object = this.getSession().get(((HibernateProxy) objectToInitialize).getHibernateLazyInitializer().getPersistentClass(),
                        ((HibernateProxy) objectToInitialize).getHibernateLazyInitializer().getIdentifier());
                if (object instanceof HibernateProxy) {
                    return ((HibernateProxy) object).getHibernateLazyInitializer().getImplementation();
                } else {
                    return object;
                }
            }
            return ((HibernateProxy) objectToInitialize).getHibernateLazyInitializer().getImplementation();

        } else {
            return objectToInitialize;
        }

    }

    /** Attache l'objet parent de la collection � la session afin de charger cette derni�re. */
    @SuppressWarnings("rawtypes")
    public Collection loadCollection(final Collection collectionToInitialize) {
        if (collectionToInitialize == null) {
            return null;
        }
        if (collectionToInitialize instanceof PersistentCollection) {
            if (((PersistentCollection) collectionToInitialize).getOwner() != null) {
                this.getSession().buildLockRequest(LockOptions.NONE).lock(((PersistentCollection) collectionToInitialize).getOwner());
            }
            Hibernate.initialize(collectionToInitialize);
            return collectionToInitialize;
        } else {
            return collectionToInitialize;
        }

    }

}
