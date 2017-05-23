package br.ufes.inf.nemo.marvin.core.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import java.util.Date;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;


@Entity
public class Place extends PersistentObjectSupport{
	
	private static final long serialVersionUID = 1L;

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


}
