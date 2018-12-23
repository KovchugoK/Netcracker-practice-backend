package com.netcrackerpractice.startup_social_network.controller;

import com.netcrackerpractice.startup_social_network.dto.StartupDTO;
import com.netcrackerpractice.startup_social_network.entity.User;
import com.netcrackerpractice.startup_social_network.mapper.StartupMapper;
import com.netcrackerpractice.startup_social_network.payload.ApiResponse;
import com.netcrackerpractice.startup_social_network.service.StartupService;
import com.netcrackerpractice.startup_social_network.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/admin")
public class AdminController {

    @Autowired
    StartupService startupService;

    @Autowired
    UserService userService;

    @Autowired
    StartupMapper startupMapper;

    @PostMapping("/block/startup")
    private ResponseEntity<?> blockStartup(@RequestBody StartupDTO startup) {
        startup.setNonBlock(false);
        startupService.saveStartup(startupMapper.dtoToEntity(startup), startup.getImage());
        return new ResponseEntity<>(new ApiResponse(true, "Startup blocked"), HttpStatus.OK);
    }

    @PostMapping("/unblock/startup")
    private ResponseEntity<?> unBlockStartup(@RequestBody StartupDTO startup) {
        startup.setNonBlock(true);
        startupService.saveStartup(startupMapper.dtoToEntity(startup), startup.getImage());
        return new ResponseEntity<>(new ApiResponse(true, "Startup unblocked"), HttpStatus.OK);
    }

    @GetMapping("/block/user/{id}")
    private ResponseEntity<?> blockUser(@PathVariable UUID id) {
        Optional<User> userOptional = userService.findUserById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setNonBlock(false);
            userService.saveUser(user);
        }
        return new ResponseEntity<>(new ApiResponse(true, "User blocked"), HttpStatus.OK);

    }

    @GetMapping("/unblock/user/{id}")
    private ResponseEntity<?> unBlockUser(@PathVariable UUID id) {
        Optional<User> userOptional = userService.findUserById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setNonBlock(true);
            userService.saveUser(user);
        }
        return new ResponseEntity<>(new ApiResponse(true, "User unblocked"), HttpStatus.OK);

    }
}