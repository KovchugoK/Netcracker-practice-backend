package com.netcrackerpractice.startup_social_network.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.UUID;

public interface ImageService {

    java.io.File convertMultipartToFile(MultipartFile multipartFile) throws IOException;
    String saveImageToGoogleDrive(java.io.File image) throws GeneralSecurityException, IOException;
    String compressionImage(java.io.File image) throws IOException, GeneralSecurityException;

}
