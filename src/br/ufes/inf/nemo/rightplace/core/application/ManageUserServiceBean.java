package br.ufes.inf.nemo.rightplace.core.application;

import java.util.Date;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.rightplace.core.domain.User;
import br.ufes.inf.nemo.rightplace.core.persistence.UserDAO;

@Stateless @PermitAll
public class ManageUserServiceBean extends CrudServiceBean<br.ufes.inf.nemo.rightplace.core.domain.User>
		implements ManageUserService {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	@EJB private UserDAO userDAO;


	
	public UserDAO getDAO(){
		return userDAO;
	};
	@Override
	protected User validate(User newEntity, User oldEntity) {
		// New users must have their creation date set.
		Date now = new Date(System.currentTimeMillis());
		if (oldEntity == null) newEntity.setCreationDate(now);

		// All users have their last update date set when persisted.
		newEntity.setLastUpdateDate(now);

		return newEntity;
	}
}
