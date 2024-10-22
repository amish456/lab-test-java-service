package com.labtest.lab.test.java.service.repository;

import com.labtest.lab.test.java.service.entity.Failure;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FailureRepository extends MongoRepository<Failure, String> {

}
