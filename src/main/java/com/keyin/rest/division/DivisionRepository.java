package com.keyin.rest.division;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DivisionRepository extends CrudRepository<Division, Long> {
    Optional<Division> findByName(String name);

    Optional<Division> findByStartBirthYear(String startBirthYear);
}
