package com.org.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.org.persistence.model.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {

}
