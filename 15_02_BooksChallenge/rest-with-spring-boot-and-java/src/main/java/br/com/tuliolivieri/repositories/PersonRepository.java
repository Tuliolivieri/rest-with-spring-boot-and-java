package br.com.tuliolivieri.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tuliolivieri.models.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {}
