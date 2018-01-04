package jayshawn.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Person {

	private Integer id;
	private String lastName;
	private Integer age;
	private Date birth;
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="last_name")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}

	public Person() {
		super();
	}
	public Person(Integer id, String lastName, Integer age, Date birth) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.age = age;
		this.birth = birth;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", lastName=" + lastName + ", age=" + age + ", birth=" + birth + "]";
	}
	
	
	
}
