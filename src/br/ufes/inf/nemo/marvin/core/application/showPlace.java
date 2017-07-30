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
import org.apache.jena.rdf.model.Resource;

@Stateful
@LocalBean
@Model
public class showPlace {
	
	private List<Place> placeList;
	public String searchString;
	
	private Place place = new Place();
	
	public List<Place> getPlaces(){
		return placeList;
	}
	
	public Place getPlace(){
		return place;
	}
private String searchSSem;

public String getsearchSSem() {
	System.out.printf("Place:",searchSSem);
	return searchSSem;
}
public void setsearchSSem(String searchSSem) {
	System.out.println("SET");
	this.searchSSem = searchSSem;
	System.out.printf("Place:",searchSSem);
}

	
public String searchSemantic(){
	System.out.println("Podcast Semantic");
	System.out.println(getsearchSSem());
	if(searchSSem == null || searchSSem.equals("")){
		System.out.println("vazio");
	}else{
		System.out.println(searchSSem);
	  
		if(searchSSem == null || searchSSem.equals("")){

		}else{
		
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
					+" 	FILTER (LANG(?description) = 'en' && contains(?city_name,\""+ searchSSem +"\"))\n "
					 +"}"; 
			QueryExecution queryExecution = 
			QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
			ResultSet results = queryExecution.execSelect();
		    System.out.println("query feita");


			while (results.hasNext()) {
				
				QuerySolution querySolution = results.next();
				//System.out.println(querySolution);
				Resource name = (Resource) querySolution.get("city_name");
			    System.out.println("city_name: "+name.getURI());
			    Literal country = querySolution.getLiteral("country");
				System.out.println("country::"+country.getValue());
				Literal literalDesc = querySolution.getLiteral("description");
				System.out.println("Description:"+literalDesc.getValue());
		
		  }
			
		}
	}
	return null;
}





//	public void suggestPlace(String name){
	public void suggestPlace(){
		
		String searchName = searchString;

		System.out.println("suggest Place chamado");
		if(searchName == null || searchName.equals("")){
						System.out.println("vazio");
		}else{
			System.out.println("Entra pesquisando: \n");
			System.out.println(searchName);
	//	List<Place> placeList = new ArrayList<Place>();
	//	Place place = new Place();
	//String searchName = place.getName();
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
			+" 	FILTER (LANG(?description) = 'en' && contains(?city_name,\""+ searchName +"\"))\n "
			 +"}"; 
			
			QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
//			System.out.println("query feita");

			try{ 
				ResultSet results = queryExecution.execSelect();
			
			if(results.hasNext()){
				System.out.println("resultado:");

				QuerySolution querySolution = results.next();
		//		Place place = new Place();
				String city_name = querySolution.get("city_name").toString();
				String country_name = querySolution.get("country_name").toString();
		//		String climate = querySolution.get("climate").toString();
				Literal population =  querySolution.getLiteral("country_name");
				Literal height = querySolution.getLiteral("altitude");
				Literal area = querySolution.getLiteral("area");

				place.setName(city_name);
				place.setName(country_name);
//				place.setClimate(climate);
				place.setHeight(height.getLong());
				place.setArea(area.getLong());
				place.setPopulation(population.getLong());
				System.out.println(querySolution.get("city_name").toString());

			}
			}
			finally {
				queryExecution.close();
					}
		
			}		
		return ;

	}
}

