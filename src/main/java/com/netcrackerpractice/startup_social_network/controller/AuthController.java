package com.netcrackerpractice.startup_social_network.controller;


import com.netcrackerpractice.startup_social_network.entity.Role;
import com.netcrackerpractice.startup_social_network.entity.User;
import com.netcrackerpractice.startup_social_network.entity.enums.RoleEnum;
import com.netcrackerpractice.startup_social_network.exception.AppException;
import com.netcrackerpractice.startup_social_network.payload.ApiResponse;
import com.netcrackerpractice.startup_social_network.payload.JwtAuthenticationResponse;
import com.netcrackerpractice.startup_social_network.payload.LoginRequest;
import com.netcrackerpractice.startup_social_network.payload.SignUpRequest;
import com.netcrackerpractice.startup_social_network.repository.RoleRepository;
import com.netcrackerpractice.startup_social_network.repository.UserRepository;
import com.netcrackerpractice.startup_social_network.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getLogin(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.generateToken(authentication);

            User user = userRepository.findByLogin(loginRequest.getLogin()).get();
            user.setToken(new JwtAuthenticationResponse(jwt));

            return ResponseEntity.ok(user);
        }

        catch (AuthenticationException ex){
            return new ResponseEntity(new ApiResponse(false, "Incorrect login or password"),
                    HttpStatus.BAD_REQUEST);
             }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByLogin(signUpRequest.getLogin())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User();

        user.setLogin(signUpRequest.getLogin());
        user.setHashedPassword(signUpRequest.getPassword());
        user.setEmail(signUpRequest.getEmail());

        user.setHashedPassword(passwordEncoder.encode(user.getHashedPassword()));

//        Role userRole = roleRepository.findById(UUID.fromString("c4ffe785-0c75-4563-b777-f52e1da52d2a"))
//                .orElseThrow(() -> new AppException("User Role not set."));
//
//        user.setRoles(userRole);

        Role userRole = roleRepository.findByRoleName(RoleEnum.USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);
        result.getId();


        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getLogin()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
}