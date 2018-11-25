package com.netcrackerpractice.startup_social_network.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.UUID;

public interface ImageService {
    void saveImage(MultipartFile file, UUID accountId) throws GeneralSecurityException, IOException;

}
