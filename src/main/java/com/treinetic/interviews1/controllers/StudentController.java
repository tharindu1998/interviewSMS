package com.treinetic.interviews1.controllers;
import com.treinetic.interviews1.domain.Student;
import com.treinetic.interviews1.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;

/**
 * REST controller for managing {@link com.treinetic.interviews1.domain.Student}.
 */
@RestController
public class StudentController {


    @Autowired
    private StudentRepository studentRepository;

    private final Logger log = LoggerFactory.getLogger(StudentController.class);

    @RequestMapping(
            method = {RequestMethod.GET},
            value = {"/api/students"}
    )
    public Iterable<Student> student() {
        return this.studentRepository.findAll();
    }


    @RequestMapping(
            method = {RequestMethod.POST},
            value = {"/api/students"}
    )
    public String save(@RequestBody Student student) {
        this.studentRepository.save(student);
        return student.getId();
    }

    @RequestMapping(
            method = {RequestMethod.GET},
            value = {"/api/students/{id}"}
    )
    public Optional<Student> show(@PathVariable String id) {
        return this.studentRepository.findById(id);
    }

    @RequestMapping(
            method = {RequestMethod.PUT},
            value = {"/api/students/{id}"}
    )
    public Student update(@PathVariable String id, @RequestBody Student student) {
        Optional<Student> student1 = this.studentRepository.findById(id);
        this.studentRepository.save(student1.get());
        return student1.get();
    }

    @RequestMapping(
            method = {RequestMethod.DELETE},
            value = {"/api/students/{id}"}
    )
    public String delete(@PathVariable String id) {
        Optional<Student> student = this.studentRepository.findById(id);
        this.studentRepository.delete(student.get());
        return "student deleted";
    }
}
