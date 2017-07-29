package br.ufes.inf.nemo.marvin.core.domain;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

import java.util.Date;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;
import br.ufes.inf.nemo.marvin.people.domain.Person;


@Entity
public class Place extends PersistentObjectSupport implements Comparable<Place>{
	
	private static final long serialVersionUID = 1L;

	@Lob
	private String description;
	
	@Basic
	@NotNull
	@Size(max = 20)
	private String name;

	@Basic
	@Size(max = 20)
	private String country;

	@Basic
	@Size(max = 20)
	private String state;
	
	@Basic
	private long area ;
	
	@Basic
	private long population;

	@Basic
	private int height;

	@Basic
	private String climate;
	
	@ManyToMany(mappedBy ="places")
	Set<User> user;
	
	/** The timestamp of the moment this user was created. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	public String getDescription (){
		return description;
	}
	public void setDescription(String description){
		this.description = description;
		
	}
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public long getArea() {
		return area;
	}


	public void setArea(long area) {
		this.area = area;
	}


	public long getPopulation() {
		return population;
	}


	public void setPopulation(long population) {
		this.population = population;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	public String getClimate() {
		return climate;
	}


	public void setClimate(String climate) {
		this.climate = climate;
	}


	public Date getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	 

	/** @see java.lang.Comparable#compareTo(java.lang.Object) */
	@Override
	public int compareTo(Place o) {
		// Compare the persons' names
		if (name == null) return 1;
		if (o.name == null) return -1;
		int cmp = name.compareTo(o.name);
		if (cmp != 0) return cmp;

		// If it's the same name, check if it's the same entity.
		return uuid.compareTo(o.uuid);
	}



}
