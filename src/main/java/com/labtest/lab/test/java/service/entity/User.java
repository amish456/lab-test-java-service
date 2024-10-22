package com.labtest.lab.test.java.service.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "user")
public class User {

    @Id
    private String username;
    private String password;
    private boolean enabled;
    private List<String> roles;

}
