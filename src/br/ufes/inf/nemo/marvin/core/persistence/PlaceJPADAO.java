package br.ufes.inf.nemo.marvin.core.persistence;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.inf.nemo.marvin.core.domain.Place;
import br.ufes.inf.nemo.marvin.core.domain.Place_;

/**
 * TODO: document this type.
 *
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Stateless
public class PlaceJPADAO extends BaseJPADAO<Place> implements PlaceDAO {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(PlaceJPADAO.class.getCanonicalName());

	/** The application's persistent context provided by the application server. */
	@PersistenceContext
	private EntityManager entityManager;

	/** @see br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO#getEntityManager() */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	/** @see br.ufes.inf.nemo.marvin.core.persistence.PlaceDAO#retrieveByname(java.lang.String) */
	@Override
	public Place retrieveByname(String name) throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException {
		logger.log(Level.FINE, "Retrieving the Place whose e-mail is \"{0}\"...", name);

		// Constructs the query over the Place class.
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Place> cq = cb.createQuery(Place.class);
		Root<Place> root = cq.from(Place.class);

		// Filters the query with the name.
		cq.where(cb.equal(root.get(Place_.name), name));
		Place result = executeSingleResultQuery(cq, name);
		logger.log(Level.INFO, "Retrieve Place by the name \"{0}\" returned \"{1}\"", new Object[] { name, result });
		return result;
	}
}