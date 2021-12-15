package com.treinetic.interviews1.repository;

import com.treinetic.interviews1.domain.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
    Role findByRole(String var1);
}
