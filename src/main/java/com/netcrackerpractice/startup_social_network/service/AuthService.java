package com.netcrackerpractice.startup_social_network.service;

import com.netcrackerpractice.startup_social_network.payload.LoginRequest;
import com.netcrackerpractice.startup_social_network.payload.SignUpRequest;
import org.springframework.http.ResponseEntity;


public interface AuthService {
    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);
    ResponseEntity<?> registerUser(SignUpRequest signUpRequest);
}
