package com.netcrackerpractice.startup_social_network.service.impl;

import com.netcrackerpractice.startup_social_network.dto.AccountDTO;
import com.netcrackerpractice.startup_social_network.entity.*;
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
import java.util.*;
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
        Optional<Account> updatedAccount = accountRepository.findById(id);
        if (updatedAccount.isPresent()) {
            Account newAccount = updatedAccount.get();
            newAccount.setFirstName(account.getFirstName());
            newAccount.setLastName(account.getLastName());
            newAccount.setAboutMe(account.getAboutMe());
            newAccount.setBirthday(account.getBirthday());
            newAccount.setWorkExperiences(account.getWorkExperiences());
            newAccount.setEducations(account.getEducations());
            newAccount.getEducations().forEach(value -> {
                Education education= new Education();
                value.setAccount(newAccount);
                if(value.getId()!=null){ educationService.updateEducation(value.getId(),value); }
                else educationService.saveEducation(value);
            });
            newAccount.getWorkExperiences().forEach(value -> {
                WorkExperience education= new WorkExperience();
                value.setAccount(newAccount);
                if(value.getId()!=null){ workExperienceService.updateWorkExperience(value.getId(),value); }
                else workExperienceService.saveWorkExperience(value);
            });
            newAccount.setFavorites(account.getFavorites());


            try {
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
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return saveAccount(newAccount);
        }
        return  null;
    }


    private List<Account> accountsList(Set<Resume> setList) {
        return setList.stream().map(Resume::getAccount).collect(Collectors.toList());
    }


    @Override
    public List<AccountDTO> buildAccountDTO(List<Account> accountList, List<BusinessRole> businessRoleList) {
        List<AccountDTO> accountDTOS = new ArrayList<>();
        for (int i = 0; i < accountList.size(); i++) {
            accountDTOS.add(new AccountDTO(accountList.get(i), businessRoleList.get(i)));
        }
        return accountDTOS;
    }
}
