package br.com.tuliolivieri.services;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.tuliolivieri.controllers.BookController;
import br.com.tuliolivieri.data.vo.v1.BookVO;
import br.com.tuliolivieri.exceptions.RequiredObjectIsNullException;
import br.com.tuliolivieri.exceptions.ResourceNotFoundException;
import br.com.tuliolivieri.mapper.DozerMapper;
import br.com.tuliolivieri.models.Book;
import br.com.tuliolivieri.repositories.BookRepository;

@Service
public class BookService {
	private Logger logger = Logger.getLogger(BookService.class.getName());
	
	@Autowired
	BookRepository repository;
	
	public BookVO findById(Long id) {
		logger.info("Finding book...");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this Id"));
		
		var vo = DozerMapper.parseObject(entity, BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		
		return vo;
	}
	
	public List<BookVO> findAll() {
		logger.info("Returning all books...");
		
		var books = DozerMapper.parseListObjects(repository.findAll(), BookVO.class);
		
		books
			.stream()
			.forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
		
		return books;
	}
	
	public BookVO create(BookVO book) {
		if(book == null) throw new RequiredObjectIsNullException();
		
		logger.info("Creating book...");
		
		var entity = DozerMapper.parseObject(book, Book.class);
		var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		
		return vo;	
	}
	
	public BookVO update(BookVO book) {
		if(book == null) throw new RequiredObjectIsNullException();
		
		logger.info("Updating book...");
		
		Book entity = repository.findById(book.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this Id"));
		
		entity.setTitle(book.getTitle());
		entity.setAuthor(book.getAuthor());
		entity.setPrice(book.getPrice());
		entity.setLaunchDate(book.getLaunchDate());

		var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		
		return vo;	
	}
	
	public void delete(Long id) {
		logger.info("Deleting book...");
		
		Book entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this Id"));
		
		repository.delete(entity);
	}
}
