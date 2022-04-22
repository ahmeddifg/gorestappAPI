package com.gorestapp.gorest.entities.repos;

import com.gorestapp.gorest.entities.UserAuthAccount;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserAuthAccountRepo extends MongoRepository<UserAuthAccount,String> {
    UserAuthAccount findByEmail(String email);
    UserAuthAccount findByUserName(String name);
}
