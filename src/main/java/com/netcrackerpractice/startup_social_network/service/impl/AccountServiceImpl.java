package com.netcrackerpractice.startup_social_network.service.impl;

import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.repository.AccountRepository;
import com.netcrackerpractice.startup_social_network.service.AccountService;
import com.netcrackerpractice.startup_social_network.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ImageService imageService;

    @Override
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> findAccountById(UUID uuid) {
        return accountRepository.findById(uuid);
    }

    @Override
    public void deleteAccountById(UUID uuid) {  accountRepository.deleteById(uuid); }

    @Override
    public Account updateAccount(UUID id, Account account, String image) throws IOException, GeneralSecurityException{
        Optional<Account> updatedAccount = accountRepository.findById(id);
        if (updatedAccount.isPresent()) {
            Account newAccount = updatedAccount.get();
            newAccount.setFirstName(account.getFirstName());
            newAccount.setLastName(account.getLastName());
            newAccount.setAboutMe(account.getAboutMe());
            newAccount.setBirthday(account.getBirthday());
            newAccount.setEducations(account.getEducations());
            newAccount.setFavorites(account.getFavorites());

            imageService.deleteImageFromGoogleDrive(newAccount.getImageId(), newAccount.getCompressedImageId());
            File imageFile = imageService.convertStringToFile(image);
            String imageId = imageService.saveImageToGoogleDrive(imageFile);

            String comressedImagePath = imageService.compressionImage(imageFile);
            File comressedImageFile = new File(comressedImagePath);
            String comressedImageId = imageService.saveImageToGoogleDrive(comressedImageFile);

            newAccount.setImageId(imageId);
            newAccount.setCompressedImageId(comressedImageId);

            imageFile.delete();
            comressedImageFile.delete();
            return saveAccount(newAccount);
        }
        return  null;
    }
}
