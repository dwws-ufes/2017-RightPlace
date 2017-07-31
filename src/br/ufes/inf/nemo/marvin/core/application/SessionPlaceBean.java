package br.ufes.inf.nemo.marvin.core.application;


import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.faces.bean.SessionScoped;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Resource;

import br.ufes.inf.nemo.marvin.core.application.showPlace;
import br.ufes.inf.nemo.marvin.core.domain.Place;
import br.ufes.inf.nemo.marvin.core.persistence.PlaceDAO;

@SessionScoped
@Stateful
public class SessionPlaceBean implements SessionPlace {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private PlaceDAO placeDAO;

	Place place = new Place();

	/** The current user logged in. */
	
	public Place getCurrentPlace() {
		return place;
	}
	
	public void saveTestSearch(String city_name, String description){
		place.setName(city_name);
		place.setDescription(description);
		placeDAO.save(place);
		return;
	}

	
	public void savePlaceSearch(ResultSet results){
		
		QuerySolution querySolution = results.next();
		String city_name = querySolution.get("city_name").toString();
		String country_name = querySolution.get("country_name").toString();
		String description = querySolution.get("description").toString();
		
		String climate = querySolution.get("climate").toString();
		Literal population =  querySolution.getLiteral("population");
		Literal height = querySolution.getLiteral("height");
		Literal area = querySolution.getLiteral("area");

		place.setName(city_name);
		place.setCountry(country_name);
		place.setDescription(description);
		
		place.setClimate(climate);
		place.setHeight(height.getLong());
		place.setArea(area.getLong());
		place.setPopulation(population.getLong());
		
		placeDAO.save(place);

		//System.out.println("place.country" + place.getCountry());				
		//System.out.println(querySolution.get("country_name").toString());
		return;
		}

}