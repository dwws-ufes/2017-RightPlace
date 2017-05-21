package br.ufes.inf.nemo.rightplace.core.controller;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.LikeFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.inf.nemo.rightplace.core.application.ManageUserService;
import br.ufes.inf.nemo.rightplace.core.domain.User;

@Named
@SessionScoped
public class ManageUserController extends CrudController<User> {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB private  ManageUserService manageUserService;

	@Override
	protected CrudService<User> getCrudService() {
		// TODO Auto-generated method stub
		return manageUserService;
	}

	@Override
	protected void initFilters() {
		// TODO Auto-generated method stub
		addFilter(new LikeFilter("manageUser.filter.byName", "name", getI18nMessage("msgsCore", "manageUser.text.filter.byName")));
	}

}
