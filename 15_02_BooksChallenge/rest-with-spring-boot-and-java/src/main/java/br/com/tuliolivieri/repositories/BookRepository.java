package br.com.tuliolivieri.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tuliolivieri.models.Book;

public interface BookRepository extends JpaRepository<Book, Long>{}
