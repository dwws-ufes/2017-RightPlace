package br.ufes.inf.nemo.marvin.core.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-04-17T15:51:32.684-0300")
@StaticMetamodel(Place.class)
public class Place_ {
	public static volatile SingularAttribute<Place, String> name;
	public static volatile SingularAttribute<Place, String> country;
	public static volatile SingularAttribute<Place, String> state;
	public static volatile SingularAttribute<Place, Long> area;
	public static volatile SingularAttribute<Place, Long> population;
	public static volatile SingularAttribute<Place, Integer> height;
	public static volatile SingularAttribute<Place, String> climate;
	public static volatile SetAttribute<Place, User> users;
	public static volatile SingularAttribute<Place, Date> creationDate;
}