package test;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import jayshawn.entities.Person;
import jayshawn.entities.Person_;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestCriteria {

	
	@PersistenceContext
	EntityManager manager;

	CriteriaBuilder criteriaBuilder;
	
	@Rollback(false)
	@Transactional
	@Before
	public void init(){
		// EntityManager 创建 CriteriaBuilder 的一个实例。CriteriaBuilder 是 CriteriaQuery 的工厂。
		criteriaBuilder = manager.getCriteriaBuilder();
	}
	
	
	/**
	 * CriteriaQuery 中的函数表达式
	 */
	@Test
	public void test1(){
		
		// 创建CriteriaQuery 实例, 泛型是返回结果的类型
		CriteriaQuery<Double> criteriaQuery = criteriaBuilder.createQuery(Double.class);
		// Root< T> 实际上表示：“对所有类型为 T 的实例计算这个查询。”
		Root<Person> person = criteriaQuery.from(Person.class);
		// person.get(Person_.age) 是一个路径表达式。路径表达式是通过一个或多个持久化属性从根表达式进行导航得到的结果。
		// 因此，表达式 person.get(Person_.age) 表示使用 Person 的 age 属性从根表达式 person 导航。
		criteriaQuery.select(criteriaBuilder.avg(person.get(Person_.age)));
		// 构造查询语句
		TypedQuery<Double> query = manager.createQuery(criteriaQuery);
		double result = query.getSingleResult();
		System.out.println(result);
	}
	
	/**
	 * CriteriaQuery 中的 where() 谓词
	 */
	@Test
	public void test2(){
		CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
		Root<Person> person = criteriaQuery.from(Person.class);
		Path<Integer> age = person.get(Person_.age);
		criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.greaterThan(age, 1), criteriaBuilder.lessThan(age, 100)));
		TypedQuery<Person> query = manager.createQuery(criteriaQuery);
		List<Person> persons = query.getResultList();
		System.out.println(persons.size());
	}
	
	
	/**
	 * CriteriaQuery 中的多值表达式
	 */
	@Test
	public void test3(){
		CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
		Root<Person> person = criteriaQuery.from(Person.class);
		Path<Integer> id = person.get(Person_.id);
		criteriaQuery.where(criteriaBuilder.in(id).value(6).value(7).value(8));
		TypedQuery<Person> query = manager.createQuery(criteriaQuery);
		List<Person> persons = query.getResultList();
		System.out.println(persons.size());
		
	}
}
