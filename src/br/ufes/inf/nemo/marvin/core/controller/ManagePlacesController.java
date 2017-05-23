
	package br.ufes.inf.nemo.marvin.core.controller;


import javax.ejb.EJB;
	import javax.enterprise.context.SessionScoped;
	import javax.inject.Named;

	import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
	import br.ufes.inf.nemo.jbutler.ejb.application.filters.LikeFilter;
	import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
	import br.ufes.inf.nemo.marvin.core.application.ManagePlacesService;
	import br.ufes.inf.nemo.marvin.core.domain.Place;

	@Named
	@SessionScoped
	public class ManagePlacesController extends CrudController<Place> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@EJB private ManagePlacesService managePlacesService;
		
		@Override
		protected CrudService<Place> getCrudService() {
			return managePlacesService;
		}

		@Override
		protected void initFilters() {
			addFilter(new LikeFilter("managePlaces.filter.byName", "name", getI18nMessage("msgsCore", "managePlaces.text.filter.byName")));		
		}
	}
	