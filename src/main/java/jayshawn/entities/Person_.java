package jayshawn.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Person.class)
public abstract class Person_ {

	public static volatile SingularAttribute<Person, String> lastName;
	public static volatile SingularAttribute<Person, Date> birth;
	public static volatile SingularAttribute<Person, Integer> id;
	public static volatile SingularAttribute<Person, Integer> age;

}

