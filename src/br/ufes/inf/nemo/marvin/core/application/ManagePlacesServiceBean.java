package br.ufes.inf.nemo.marvin.core.application;

import java.util.Date;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.marvin.core.domain.Place;
import br.ufes.inf.nemo.marvin.core.persistence.PlaceDAO;
@Stateless
@PermitAll
public class ManagePlacesServiceBean extends CrudServiceBean<Place> implements ManagePlacesService {

	/**
	 * 
	 */
	@EJB private PlaceDAO placeDAO;
	private static final long serialVersionUID = 1L;
	@Override
	public BaseDAO<Place> getDAO() {
		return placeDAO;
	}
	
}