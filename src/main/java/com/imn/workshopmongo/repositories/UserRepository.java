package com.imn.workshopmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.imn.workshopmongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
