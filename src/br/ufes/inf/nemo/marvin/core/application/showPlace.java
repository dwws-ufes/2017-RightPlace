package br.ufes.inf.nemo.marvin.core.application;


import br.ufes.inf.nemo.marvin.core.domain.User;
import br.ufes.inf.nemo.marvin.core.persistence.UserDAO;
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

@Stateful
@LocalBean
@Model
public class showPlace {
	
	private List<Place> places;
	
	private Place place = new Place();
	
	public List<Place> getPlaces(){
		return places;
	}
	
	public Place getPlace(){
		return place;
	}
	
	
	
	public void suggestPlace(String name){
		List<Place> placeList = new ArrayList<Place>();
			
			String query = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
			+ "	PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
			+" PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
			+ "PREFIX dbo: <http://dbpedia.org/ontology/>"
			+ "PREFIX dbp: <http://dbpedia.org/property/>"
			+ "SELECT DISTINCT ?city_name ?country ?place ?area ?population ?height WHERE {"
			+ "?place rdf:type <http://dbpedia.org/ontology/PopulatedPlace>."
			+ "?place foaf:name ?city_name."
			+ "?place rdfs:comment ?description."
			+ "?place dbo:areaTotal ?area."
			+ "?place dbo:populationTotal ?population."
			+ "?place dbo:country ?country.		"
			+ "?place dbo:elevation ?height.		"
			+" 	FILTER (LANG(?description) = 'en' && contains(?city_name, "+ name.toLowerCase() +")) "
			 +"}"; 
			
			QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
			
			try{ 
				ResultSet results = queryExecution.execSelect();
			
			System.out.println(results);
			if(results.hasNext()){
				QuerySolution querySolution = results.next();
				Place place = new Place();
				String city_name = querySolution.get("city_name").toString();
				String country_name = querySolution.get("country_name").toString();
			//	long population =  querySolution.get("country_name");
				String climate = querySolution.get("climate").toString();
			//	long area =querySolution.get("areaTotal");
			//	int height = querySolution.get("altitude");
				Literal literal = querySolution.getLiteral("area");
				
				place.setArea(Long.parseLong(""+literal.getValue()));
			}
			}
			finally {
				queryExecution.close();
					}
			}
													
		}
}
