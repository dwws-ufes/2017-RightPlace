
	package br.ufes.inf.nemo.marvin.core.controller;

	import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
	import javax.enterprise.context.SessionScoped;
	import javax.inject.Named;

	import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
	import br.ufes.inf.nemo.jbutler.ejb.application.filters.LikeFilter;
	import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
	import br.ufes.inf.nemo.marvin.core.application.ManageUsersService;
	import br.ufes.inf.nemo.marvin.core.domain.User;

	@Named
	@SessionScoped
	public class ManageUsersController extends CrudController<User> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private User user = new User();
		private static final Logger logger = Logger.getLogger(ManageUsersController.class.getCanonicalName());

		@EJB private ManageUsersService manageUsersService;
		
		public User getUser() {
			return user;
		}
		
		@Override
		protected CrudService<User> getCrudService() {
			return manageUsersService;
		}

		@Override
		protected void initFilters() {
			addFilter(new LikeFilter("manageUsers.filter.byName", "name", getI18nMessage("msgsCore", "manageUsers.text.filter.byName")));		
		}
			
		public void suggestShortName() {
			// If the name was filled and the short name is still empty, suggest the first name as short name.
			String name = user.getName();
			String shortName = user.getShortName();
			if ((name != null) && ((shortName == null) || (shortName.length() == 0))) {
				int idx = name.indexOf(" ");
				user.setShortName((idx == -1) ? name : name.substring(0, idx).trim());
				logger.log(Level.FINE, "Suggested \"{0}\" as short name for \"{1}\"", new Object[] { user.getShortName(), name });
			}
			else logger.log(Level.FINEST, "Short name not suggested: empty name or short name already filled (name is \"{0}\", short name is \"{1}\")", new Object[] { name, shortName });
		}

	}

