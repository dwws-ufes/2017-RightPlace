package br.ufes.inf.nemo.marvin.core.application;

import javax.ejb.*;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.marvin.core.domain.Place;

@Local
public interface ManagePlacesService extends CrudService<Place> {
	
	
}	