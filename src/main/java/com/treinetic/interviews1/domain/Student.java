package com.treinetic.interviews1.domain;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * @author tharindu
 */
@org.springframework.data.mongodb.core.mapping.Document(collection = "students")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String password;

    private boolean enabled;

    @DBRef
    private Set<com.treinetic.interviews1.domain.Role> roles;

    public Student() {
    }

    public Student(String id, String firstName, String lastName, String email, String phoneNumber, String password, boolean enabled, Set<com.treinetic.interviews1.domain.Role> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<com.treinetic.interviews1.domain.Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Student id(String id) {
        setId(id);
        return this;
    }

    public Student firstName(String firstName) {
        setFirstName(firstName);
        return this;
    }

    public Student lastName(String lastName) {
        setLastName(lastName);
        return this;
    }

    public Student email(String email) {
        setEmail(email);
        return this;
    }

    public Student phoneNumber(String phoneNumber) {
        setPhoneNumber(phoneNumber);
        return this;
    }

    public Student password(String password) {
        setPassword(password);
        return this;
    }

    public Student enabled(boolean enabled) {
        setEnabled(enabled);
        return this;
    }

    public Student roles(Set<Role> roles) {
        setRoles(roles);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Student)) {
            return false;
        }
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName) && Objects.equals(email, student.email) && Objects.equals(phoneNumber, student.phoneNumber) && Objects.equals(password, student.password) && enabled == student.enabled && Objects.equals(roles, student.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, phoneNumber, password, enabled, roles);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", password='" + getPassword() + "'" +
            ", enabled='" + isEnabled() + "'" +
            ", roles='" + getRoles() + "'" +
            "}";
    }

   }