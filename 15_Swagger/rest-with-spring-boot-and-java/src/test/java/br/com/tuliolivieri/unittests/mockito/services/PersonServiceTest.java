package br.com.tuliolivieri.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.tuliolivieri.data.vo.v1.PersonVO;
import br.com.tuliolivieri.exceptions.RequiredObjectIsNullException;
import br.com.tuliolivieri.models.Person;
import br.com.tuliolivieri.repositories.PersonRepository;
import br.com.tuliolivieri.services.PersonService;
import br.com.tuliolivieri.unittests.mapper.mocks.MockPerson;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

	MockPerson input;
	
	@InjectMocks
	private PersonService service;
	
	@Mock
	PersonRepository repository;
	
	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockPerson();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindById() {
		Person entity = input.mockEntity(1);
		entity.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		
		var result = service.findById(1L);
		
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		
		assertTrue(result.toString().contains("links: [</person/1>;rel=\"self\"]"));
		
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Addres Test1", result.getAddress());
		assertEquals("Female", result.getGender());
	}

	@Test
	void testFindAll() {
		List<Person> entityList = input.mockEntityList();
		
		when(repository.findAll()).thenReturn(entityList);
		
		var people = service.findAll();
		
		assertNotNull(people);
		assertEquals(14, people.size());
		
		var personOne = people.get(1);
		
		assertNotNull(personOne);
		assertNotNull(personOne.getKey());
		assertNotNull(personOne.getLinks());
		
		assertTrue(personOne.toString().contains("links: [</person/1>;rel=\"self\"]"));
		
		assertEquals("First Name Test1", personOne.getFirstName());
		assertEquals("Last Name Test1", personOne.getLastName());
		assertEquals("Addres Test1", personOne.getAddress());
		assertEquals("Female", personOne.getGender());
		
		var personSix = people.get(6);
		
		assertNotNull(personSix);
		assertNotNull(personSix.getKey());
		assertNotNull(personSix.getLinks());
		
		assertTrue(personSix.toString().contains("links: [</person/6>;rel=\"self\"]"));
		
		assertEquals("First Name Test6", personSix.getFirstName());
		assertEquals("Last Name Test6", personSix.getLastName());
		assertEquals("Addres Test6", personSix.getAddress());
		assertEquals("Male", personSix.getGender());
		
		var personThirteen = people.get(13);
		
		assertNotNull(personThirteen);
		assertNotNull(personThirteen.getKey());
		assertNotNull(personThirteen.getLinks());
		
		assertTrue(personThirteen.toString().contains("links: [</person/13>;rel=\"self\"]"));
		
		assertEquals("First Name Test13", personThirteen.getFirstName());
		assertEquals("Last Name Test13", personThirteen.getLastName());
		assertEquals("Addres Test13", personThirteen.getAddress());
		assertEquals("Female", personThirteen.getGender());
	}

	@Test
	void testCreate() {
		Person entity = input.mockEntity(1);
		
		Person persisted = entity;
		persisted.setId(1L);
		
		PersonVO vo = input.mockVO(1);
		vo.setKey(1L);
		
		when(repository.save(entity)).thenReturn(persisted);
		
		var result = service.create(vo);
		
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		
		assertTrue(result.toString().contains("links: [</person/1>;rel=\"self\"]"));
		
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Addres Test1", result.getAddress());
		assertEquals("Female", result.getGender());
	}

	@Test
	void testCreateWithNullPerson() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.create(null);
		});
		
		String expectedMessage = "It's not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testUpdate() {
		Person entity = input.mockEntity(1);
		entity.setId(1L);
		
		Person persisted = entity;
		persisted.setId(1L);
		
		PersonVO vo = input.mockVO(1);
		vo.setKey(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persisted);
		
		var result = service.update(vo);
		
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		
		assertTrue(result.toString().contains("links: [</person/1>;rel=\"self\"]"));
		
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Addres Test1", result.getAddress());
		assertEquals("Female", result.getGender());
	}

	@Test
	void testUpdateWithNullPerson() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.update(null);
		});
		
		String expectedMessage = "It's not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testDelete() {
		Person entity = input.mockEntity(1);
		entity.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		
		service.delete(1L);
	}

}
