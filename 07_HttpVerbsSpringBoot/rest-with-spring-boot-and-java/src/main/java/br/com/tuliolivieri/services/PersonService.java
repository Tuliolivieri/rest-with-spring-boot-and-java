package br.com.tuliolivieri.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.tuliolivieri.models.Person;

@Service
public class PersonService {
	
	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	public Person findById(String id) {
		logger.info("Finding person...");
		
		Person person = new Person();
		
		person.setId(counter.incrementAndGet());
		person.setFirstName("Tulio");
		person.setLastName("Olivieri");
		person.setAddress("Regente Feijó - SP");
		person.setGender("Male");
		
		return person;
	}

	public List<Person> findAll() {
		logger.info("Returning all persons...");
		
		List<Person> persons = new ArrayList<Person>();
		
		for(int i = 0; i < 10; i++) {
			Person person = mockPerson(i);
			persons.add(person);
		}
		
		return persons;
	}
	
	public Person create(Person person) {
		logger.info("Creating person...");

		return person;
	}
	
	public Person update(Person person) {
		logger.info("Updating person...");

		return person;
	}
	
	public void delete(String id) {
		logger.info("Deleting person...");
	}

	private Person mockPerson(int i) {
		Person person = new Person();
		
		person.setId(counter.incrementAndGet());
		person.setFirstName("Person " + i);
		person.setLastName("Last name " + i);
		person.setAddress("Some address in Brazil");
		person.setGender("Male");
		
		return person;
	}
}
