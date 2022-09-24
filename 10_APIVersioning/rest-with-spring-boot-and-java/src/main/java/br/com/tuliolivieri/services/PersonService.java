package br.com.tuliolivieri.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tuliolivieri.data.vo.v1.PersonVO;
import br.com.tuliolivieri.data.vo.v2.PersonVOV2;
import br.com.tuliolivieri.exceptions.ResourceNotFoundException;
import br.com.tuliolivieri.mapper.DozerMapper;
import br.com.tuliolivieri.mapper.custom.PersonMapper;
import br.com.tuliolivieri.models.Person;
import br.com.tuliolivieri.repositories.PersonRepository;

@Service
public class PersonService {
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	@Autowired
	PersonMapper mapper;
	
	public PersonVO findById(Long id) {
		logger.info("Finding person...");
		
		var entity = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("No records found for this Id"));
		
		return DozerMapper.parseObject(entity, PersonVO.class);
	}

	public List<PersonVO> findAll() {
		logger.info("Returning all persons...");
		
		return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
	}
	
	public PersonVO create(PersonVO person) {
		logger.info("Creating person...");
		
		var entity = DozerMapper.parseObject(person, Person.class);
		var entityVO = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		return entityVO;
	}
	
	public PersonVOV2 createV2(PersonVOV2 person) {
		logger.info("Creating person with V2...");
		
		var entity = mapper.convertVOToEntity(person);
		var entityVO = mapper.convertEntityToVO(repository.save(entity));
		return entityVO;
	}
	
	public PersonVO update(PersonVO person) {
		logger.info("Updating person...");
		
		Person entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this Id"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());

		var entityVO = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		return entityVO;
	}
	
	public void delete(Long id) {
		logger.info("Deleting person...");
		
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this Id"));
		
		repository.delete(entity);
	}
}
