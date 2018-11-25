package com.netcrackerpractice.startup_social_network.controller;


import com.netcrackerpractice.startup_social_network.entity.Role;
import com.netcrackerpractice.startup_social_network.entity.User;
import com.netcrackerpractice.startup_social_network.entity.enums.RoleEnum;
import com.netcrackerpractice.startup_social_network.repository.RoleRepository;
import com.netcrackerpractice.startup_social_network.service.ImageService;
import com.netcrackerpractice.startup_social_network.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    ImageService imageService;

    @GetMapping("/user-list")
    public List<User> getStartupList() {
        return userService.findAll();
    }

    @PostMapping("/image")
    private ResponseEntity<?> saveImage(@RequestParam("image") MultipartFile image,
                                             @RequestParam(name = "Id") UUID accountId) {


        try {
            imageService.saveImage(image, accountId);
            return ResponseEntity.status(HttpStatus.OK).body("You successfully uploaded");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("FAIL to upload");
        }

    }
}
