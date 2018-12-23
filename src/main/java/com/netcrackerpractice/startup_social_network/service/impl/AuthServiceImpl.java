package com.netcrackerpractice.startup_social_network.service.impl;

import com.netcrackerpractice.startup_social_network.dto.UserDTOwithToken;
import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.entity.Role;
import com.netcrackerpractice.startup_social_network.entity.User;
import com.netcrackerpractice.startup_social_network.entity.enums.RoleEnum;
import com.netcrackerpractice.startup_social_network.exception.AppException;
import com.netcrackerpractice.startup_social_network.mapper.UserWithTokenMapper;
import com.netcrackerpractice.startup_social_network.payload.ApiResponse;
import com.netcrackerpractice.startup_social_network.payload.JwtAuthenticationResponse;
import com.netcrackerpractice.startup_social_network.payload.LoginRequest;
import com.netcrackerpractice.startup_social_network.payload.SignUpRequest;
import com.netcrackerpractice.startup_social_network.repository.AccountRepository;
import com.netcrackerpractice.startup_social_network.repository.RoleRepository;
import com.netcrackerpractice.startup_social_network.repository.UserRepository;
import com.netcrackerpractice.startup_social_network.security.JwtTokenProvider;
import com.netcrackerpractice.startup_social_network.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthServiceImpl implements AuthService {

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

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserWithTokenMapper userWithTokenMapper;

    @Override
    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
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
            UserDTOwithToken userDTOwithToken = userWithTokenMapper.entityToDto(user);
            userDTOwithToken.setToken(new JwtAuthenticationResponse(jwt));
            // user.setToken(new JwtAuthenticationResponse(jwt));

//            return ResponseEntity.ok(user);
            return ResponseEntity.ok(userDTOwithToken);
        } catch (AuthenticationException ex) {
            return new ResponseEntity(new ApiResponse(false, "Incorrect login or password"),
                    HttpStatus.BAD_REQUEST);
        }
    }


    @Override
    public ResponseEntity<?> registerUser(SignUpRequest signUpRequest) {
        if (userRepository.existsByLogin(signUpRequest.getLogin())) {
            return new ResponseEntity(new ApiResponse(false, "Username or email already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Username or email already in use!"),
                    HttpStatus.BAD_REQUEST);
        }


        User user = new User();
        user.setLogin(signUpRequest.getLogin());
        user.setHashedPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setEmail(signUpRequest.getEmail());
        user.setNonBlock(true);

        Role userRole = roleRepository.findByRoleName(RoleEnum.USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));
        userRepository.save(user);

        Account account = new Account();
        account.setUser(user);
        accountRepository.save(account);
        return new ResponseEntity(new ApiResponse(true, "User registered successfully"), HttpStatus.OK);

    }
}
