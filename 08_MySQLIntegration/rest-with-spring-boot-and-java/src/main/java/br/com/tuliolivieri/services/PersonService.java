package br.com.tuliolivieri.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tuliolivieri.exceptions.ResourceNotFoundException;
import br.com.tuliolivieri.models.Person;
import br.com.tuliolivieri.repositories.PersonRepository;

@Service
public class PersonService {
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	public Person findById(Long id) {
		logger.info("Finding person...");
		
		return repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("No records found for this Id"));
	}

	public List<Person> findAll() {
		logger.info("Returning all persons...");
		
		return repository.findAll();
	}
	
	public Person create(Person person) {
		logger.info("Creating person...");

		return repository.save(person);
	}
	
	public Person update(Person person) {
		logger.info("Updating person...");
		
		Person entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this Id"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		return repository.save(entity);
	}
	
	public void delete(Long id) {
		logger.info("Deleting person...");
		
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this Id"));
		
		repository.delete(entity);
	}
}
