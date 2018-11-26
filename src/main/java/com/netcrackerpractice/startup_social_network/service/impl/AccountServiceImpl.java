package com.netcrackerpractice.startup_social_network.service.impl;

import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.repository.AccountRepository;
import com.netcrackerpractice.startup_social_network.service.AccountService;
import com.netcrackerpractice.startup_social_network.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ImageService imageService;

    @Override
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> findAccountById(UUID uuid) {
        return accountRepository.findById(uuid);
    }

    @Override
    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public void saveImages(MultipartFile image, Account account) throws IOException, GeneralSecurityException {

            File imageFile = imageService.convertMultipartToFile(image);
            String imageId = imageService.saveImageToGoogleDrive(imageFile);

            String comressedImagePath = imageService.compressionImage(imageFile);
            File comressedImageFile = new File(comressedImagePath);
            String comressedImageId = imageService.saveImageToGoogleDrive(comressedImageFile);

            account.setImageId(imageId);
            account.setCompressedImageId(comressedImageId);
            accountRepository.save(account);

            imageFile.delete();
            comressedImageFile.delete();

    }
}
