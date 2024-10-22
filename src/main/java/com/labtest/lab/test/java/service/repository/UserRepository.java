package com.labtest.lab.test.java.service.repository;

import com.labtest.lab.test.java.service.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);

}
