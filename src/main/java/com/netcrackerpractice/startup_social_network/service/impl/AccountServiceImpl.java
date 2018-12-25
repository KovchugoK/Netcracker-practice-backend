package com.netcrackerpractice.startup_social_network.service.impl;

import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.entity.Resume;
import com.netcrackerpractice.startup_social_network.repository.AccountRepository;
import com.netcrackerpractice.startup_social_network.repository.BusinessRoleRepository;
import com.netcrackerpractice.startup_social_network.repository.ResumeRepository;
import com.netcrackerpractice.startup_social_network.service.AccountService;
import com.netcrackerpractice.startup_social_network.service.EducationService;
import com.netcrackerpractice.startup_social_network.service.ImageService;
import com.netcrackerpractice.startup_social_network.service.WorkExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    private ResumeRepository resumeRepository;

    @Autowired
    private BusinessRoleRepository businessRoleRepository;


    @Autowired
    private ImageService imageService;

    @Autowired
    private EducationService educationService;

    @Autowired
    private WorkExperienceService workExperienceService;

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
    public Account updateAccount(UUID id, Account account, String image){
        if(accountRepository.findById(id)!=null){
            Account updatedAccount=account;
            try {
                if(image != null && !image.isEmpty()) {
                    imageService.deleteImageFromGoogleDrive(updatedAccount.getImageId(), updatedAccount.getCompressedImageId());
                    File imageFile = imageService.convertStringToFile(image);
                    String imageId = imageService.saveImageToGoogleDrive(imageFile);

                    String comressedImagePath = imageService.compressionImage(imageFile);
                    File comressedImageFile = new File(comressedImagePath);
                    String comressedImageId = imageService.saveImageToGoogleDrive(comressedImageFile);

                    updatedAccount.setImageId(imageId);
                    updatedAccount.setCompressedImageId(comressedImageId);

                    imageFile.delete();
                    comressedImageFile.delete();
                }
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return saveAccount(updatedAccount);
        }
        return  null;
    }


    private List<Account> accountsList(Set<Resume> setList) {
        return setList.stream().map(Resume::getAccount).collect(Collectors.toList());
    }


}
