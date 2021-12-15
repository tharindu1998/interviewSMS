package com.treinetic.interviews1.repository;

import com.treinetic.interviews1.domain.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author tharindu
 */
public interface StudentRepository extends MongoRepository<Student, String> {

        Student findByEmail(String email);
        void delete(Student var1);
}
