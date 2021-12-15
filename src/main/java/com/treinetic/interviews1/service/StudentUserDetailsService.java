package com.treinetic.interviews1.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.treinetic.interviews1.domain.Role;
import com.treinetic.interviews1.domain.Student;
import com.treinetic.interviews1.repository.RoleRepository;
import com.treinetic.interviews1.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class StudentUserDetailsService implements UserDetailsService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;
	
	public Student findUserByEmail(String email) {
	    return studentRepository.findByEmail(email);
	}
	
	public void saveUser(Student student) {
		student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));
		student.setEnabled(true);
	    Role userRole = roleRepository.findByRole("ADMIN");
	    student.setRoles(new HashSet<>(Arrays.asList(userRole)));
	    studentRepository.save(student);
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

	    Student student = studentRepository.findByEmail(email);
	    if(student != null) {
	        List<GrantedAuthority> authorities = getUserAuthority(student.getRoles());
	        return buildUserForAuthentication(student, authorities);
	    } else {
	        throw new UsernameNotFoundException("username not found");
	    }
	}
	
	private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
	    Set<GrantedAuthority> roles = new HashSet<>();
	    userRoles.forEach((role) -> {
	        roles.add(new SimpleGrantedAuthority(role.getRole()));
	    });

	    List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
	    return grantedAuthorities;
	}
	
	private UserDetails buildUserForAuthentication(Student user, List<GrantedAuthority> authorities) {
	    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
	}
}
