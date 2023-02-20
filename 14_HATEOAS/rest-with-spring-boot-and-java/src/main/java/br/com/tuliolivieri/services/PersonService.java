package br.com.tuliolivieri.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import br.com.tuliolivieri.controllers.PersonController;
import br.com.tuliolivieri.data.vo.v1.PersonVO;
import br.com.tuliolivieri.exceptions.ResourceNotFoundException;
import br.com.tuliolivieri.mapper.DozerMapper;
import br.com.tuliolivieri.models.Person;
import br.com.tuliolivieri.repositories.PersonRepository;

@Service
public class PersonService {
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	public PersonVO findById(Long id) {
		logger.info("Finding person...");
		
		var entity = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("No records found for this Id"));
		
		var vo = DozerMapper.parseObject(entity, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		
		return vo;	
	}

	public List<PersonVO> findAll() {
		logger.info("Returning all persons...");
		
		var persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
		
		persons
			.stream()
			.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		
		return persons;
	}
	
	public PersonVO create(PersonVO person) {
		logger.info("Creating person...");
		
		var entity = DozerMapper.parseObject(person, Person.class);
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		
		return vo;	
	}
	
	public PersonVO update(PersonVO person) {
		logger.info("Updating person...");
		
		Person entity = repository.findById(person.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this Id"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());

		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		
		return vo;	
	}
	
	public void delete(Long id) {
		logger.info("Deleting person...");
		
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this Id"));
		
		repository.delete(entity);
	}
}
