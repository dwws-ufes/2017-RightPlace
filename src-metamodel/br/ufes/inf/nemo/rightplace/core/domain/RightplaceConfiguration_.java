package br.ufes.inf.nemo.rightplace.core.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import br.ufes.inf.nemo.rightplace.core.domain.RightplaceConfiguration;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-07-28T09:06:58.136-0300")
@StaticMetamodel(RightplaceConfiguration.class)
public class RightplaceConfiguration_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<RightplaceConfiguration, Date> creationDate;
	public static volatile SingularAttribute<RightplaceConfiguration, String> institutionAcronym;
}
