package br.com.tuliolivieri.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tuliolivieri.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {}
