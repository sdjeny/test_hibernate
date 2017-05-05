package sdjen.test.hibernate;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.metamodel.EntityType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sdjen.test.hibernate.beans.Person;

public class PersonTest {
	EntityManagerFactory factory;

	@Before
	public void begin() {
		System.out.println("B");
		factory = Persistence.createEntityManagerFactory("persistence-unit_demo");
	}

	@Test
	public void save() {
		for (EntityType entityType : factory.getMetamodel().getEntities()) {
			System.out.println(entityType.getName());
		}
		System.out.println("-------------------");
		查();
	}

	//@Test
	public void 查() {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		// manager.persist(new Person("大天天"));
		javax.persistence.Query query = manager.createQuery("FROM Person WHERE name=:name");
		query.setParameter("name", "大天天");
		List<Person> persons = query.getResultList();
		for (Person person : persons) {
			System.out.println(person.getId() + "	:	" + person.getName());
		}
		manager.getTransaction().commit();
		manager.close();
	}

	@After
	public void after() {
		factory.close();
		System.out.println("E");
	}
}
