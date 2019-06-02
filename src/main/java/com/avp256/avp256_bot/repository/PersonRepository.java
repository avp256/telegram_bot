package com.avp256.avp256_bot.repository;

import com.avp256.avp256_bot.model.Person;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "persons", path = "persons")
public interface PersonRepository extends PagingAndSortingRepository<Person, Integer> {
    Optional<Person> findByAuthCode(String authCode);
}
