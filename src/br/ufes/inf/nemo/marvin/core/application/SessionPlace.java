package br.ufes.inf.nemo.marvin.core.application;

import java.io.Serializable;

import javax.ejb.Local;

import org.apache.jena.query.ResultSet;

import br.ufes.inf.nemo.marvin.core.domain.Place;
//import br.ufes.inf.nemo.smartcast.persistence.PodcastDAO;

@Local
public interface SessionPlace extends Serializable{

	Place getCurrentPlace();
	void savePlaceSearch(ResultSet results);
	void saveTestSearch(String city_name, String country_name);

}