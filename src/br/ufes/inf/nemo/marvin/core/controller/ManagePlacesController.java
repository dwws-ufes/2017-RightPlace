
	package br.ufes.inf.nemo.marvin.core.controller;


import org.apache.jena.query.QueryException;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import javax.ejb.EJB;
	import javax.enterprise.context.SessionScoped;
	import javax.inject.Named;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;

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
		
		private Place place = new Place();
		public Place getPlace(){
			return place;
			}
		
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
	

//		public void suggestPlace(){
		//			String name = placer.getName();
		//if(name!=null&& name.length()>3){
		//	String query = "PREFIX dbpedia-owl:<http://dbpedia.org/ontology/>"
		//			+ "PREFIX dbpprop: <http://dbpedia.org/property/> "
		//			+ " SELECT ?name ?x ?code WHERE {?x a dbo:PopulatedPlace; dbprop:name ?name ;dbo:country ?code  }";
		//	//		+ "SELECT ?alt WHERE {?x a dbpedia-owl:Place; dbprop:name ?name ;dbpedia-owl:altitude ?alt . "			
		//			//+ "FILTER (lcase(str(?name)) = \""+ name.toLowerCase() +"\")FILTER(langMatches(lang(?desc),\"EN\")) }";	
		//	
		//	QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
		//	
		//	try{ 
		//		ResultSet results = queryExecution.execSelect();
		//	
		//	System.out.println(results);
		//	if(results.hasNext()){
		//		QuerySolution querySolution = results.next();
		//		Literal literal = querySolution.getLiteral("country");
		//		placer.setDescription(""+literal.getValue());
		//		System.out.println(literal.getValue());
		//	}
		//	}
		//	finally {
		//		queryExecution.close();
		//			}
		//}
		//}
	}
	