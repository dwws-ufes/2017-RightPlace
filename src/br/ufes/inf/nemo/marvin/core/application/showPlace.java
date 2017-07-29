package br.ufes.inf.nemo.marvin.core.application;


import br.ufes.inf.nemo.marvin.core.domain.User;
import br.ufes.inf.nemo.marvin.core.persistence.UserDAO;
import br.ufes.inf.nemo.marvin.core.domain.Place;
import br.ufes.inf.nemo.marvin.core.persistence.PlaceDAO;

import java.text.*;
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
	
	
	
	public void suggestPlace(){
		String name = place.getName();
		if(name!=null&& name.length()>3){
			//String query = "PREFIX dbpedia-owl:<http://dbpedia.org/ontology/>"+
			//"PREFIX dbpprop: <http://dbpedia.org/property/>"+
			//"SELECT ?desc "+
			//"WHERE {"+
			//"?x a dbpedia-owl:Place ; "+
			//"dbprop:name ?name ; " +
			//"dbpedia-owl:abstract ?desc . " +
			//"FILTER (lcase(str(?name)) = \""+ name.toLowerCase() +"\")"+
			//"FILTER (langMatches(lang(?desc), \"EN\")) "+
			//"}";
			String query = "PREFIX dbpedia-owl:<http://dbpedia.org/ontology/>"
					+ "PREFIX dbpprop: <http://dbpedia.org/property/> "
					+ "SELECT ?desc WHERE {?x a dbpedia-owl:Place; dbprop:name ?name ;dbpedia-owl:abstract ?desc . "
					+ "FILTER (lcase(str(?name)) = \""+ name.toLowerCase() +"\")FILTER(langMatches(lang(?desc),\"EN\")) }";
			
			QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
			ResultSet results = queryExecution.execSelect();
			if(results.hasNext()){
				QuerySolution querySolution = results.next();
				Literal literal = querySolution.getLiteral("desc");
				place.setDescription(""+literal.getValue());
				
			}
							
							
							
		}
	}
	

}
