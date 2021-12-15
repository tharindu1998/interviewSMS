package com.treinetic.interviews1.controllers;

import com.treinetic.interviews1.config.JwtTokenProvider;
import com.treinetic.interviews1.domain.AuthBody;
import com.treinetic.interviews1.domain.Student;
import com.treinetic.interviews1.repository.StudentRepository;
import com.treinetic.interviews1.service.StudentUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping({"/api/auth"})
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    private StudentUserDetailsService studentUserDetailsService;

    public AuthController() {
    }

    @PostMapping({"/login"})
    public ResponseEntity login(@RequestBody AuthBody data) {
        try {
            String username = data.getEmail();
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            String token = this.jwtTokenProvider.createToken(username, this.studentRepository.findByEmail(username).getRoles());
            Map<Object, Object> model = new HashMap();
            model.put("username", username);
            model.put("token", token);
            return ResponseEntity.ok(model);
        } catch (AuthenticationException var5) {
            throw new BadCredentialsException("Invalid email/password supplied");
        }
    }

    @PostMapping({"/register"})
    public ResponseEntity register(@RequestBody Student user) {
        Student userExists = this.studentUserDetailsService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            throw new BadCredentialsException("User with username: " + user.getEmail() + " already exists");
        } else {
            this.studentUserDetailsService.saveUser(user);
            Map<Object, Object> model = new HashMap();
            model.put("message", "User registered successfully");
            return ResponseEntity.ok(model);
        }
    }
}
