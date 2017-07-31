package br.ufes.inf.nemo.marvin.core.application;


import br.ufes.inf.nemo.marvin.core.domain.User;
import br.ufes.inf.nemo.marvin.core.persistence.UserDAO;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.marvin.core.controller.ManagePlacesController;
import br.ufes.inf.nemo.marvin.core.domain.Place;
import br.ufes.inf.nemo.marvin.core.persistence.PlaceDAO;

import java.text.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

import javax.ejb.*;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.New;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.jena.query.QueryException;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Resource;

@Stateful
@LocalBean
@Model
public class showPlace {
	
	private List<Place>  placeList;
	private Place place = new Place();
	private String searchString;
	
	
	@EJB
	private PlaceDAO placeDAO ;
	
	public List<Place> getPlaces(){
		return placeList;
	}
	
	public void setPlace( Place place){
		this.place  = place;
	}
	public Place getPlace(){
		return place;
	}

	public void setSearchString(String search) {
		this.searchString =search ;
		System.out.println("set search");
	}
	public String getSearchString(){
		System.out.println("get search");
		return searchString;
	};


	public String suggestPlace(){
		System.out.println("teste");

		String searchName = getSearchString();
		//System.out.println(searchString);
	//	System.out.println(searchName);		
		place.setName(searchString);
		placeDAO.save(place);

		System.out.println("Place name:" + place.getName());
		if(searchName == null || searchName.equals("")){
						System.out.println("vazio");
		}else{
			System.out.println("Entra pesquisando:");
			System.out.println(searchName);

	//	List<Place> placeList = new ArrayList<Place>();
	//	Place place = new Place();
	//String searchName = place.getName();
			String query = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
			+ "	PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
			+" PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
			+ "PREFIX dbo: <http://dbpedia.org/ontology/>"
			+ "PREFIX dbp: <http://dbpedia.org/property/>"
	//		+ "SELECT DISTINCT ?city_name ?country ?place ?area ?population ?height WHERE {"
			+ "SELECT DISTINCT ?city_name ?country_name ?place  WHERE {"
			+ "?place rdf:type <http://dbpedia.org/ontology/PopulatedPlace>."
			+ "?place foaf:name ?city_name."
			+ "?place rdfs:comment ?description."
			//+ "?place dbo:areaTotal ?area."
			//+ "?place dbo:populationTotal ?population."
			+ "?place dbo:country ?country_name.		"
			//+ "?place dbo:elevation ?height.		"
			+" 	FILTER (LANG(?description) = 'en' && contains(?city_name,\""+ searchName +"\"))\n "
			 +"}"; 
			System.out.println("query definida");
			QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
			System.out.println("query feita");

			try{ 
				System.out.println("executando query na dbpedia:");
				ResultSet results = queryExecution.execSelect();
				System.out.println("resultados obtidos");
//			if(results.hasNext()){
				System.out.println("resultado:");

				QuerySolution querySolution = results.next();
				String city_name = querySolution.get("city_name").toString();
				String country_name = querySolution.get("country_name").toString();
				String description = querySolution.get("description").toString();
				
		//		String climate = querySolution.get("climate").toString();
		//		Literal population =  querySolution.getLiteral("population");
		//		Literal height = querySolution.getLiteral("height");
		//		Literal area = querySolution.getLiteral("area");

				place.setName(city_name);
				place.setCountry(country_name);
				place.setDescription(country_name);
//				place.setClimate(climate);
		//		place.setHeight(height.getLong());
		//		place.setArea(area.getLong());
		//		place.setPopulation(population.getLong());
				placeDAO.save(place);

				System.out.println("place.country" + place.getCountry());				
				System.out.println(querySolution.get("country_name").toString());	

			//}
			}
			finally {
				queryExecution.close();
					}
		
			}		
		return null;

	}
}

