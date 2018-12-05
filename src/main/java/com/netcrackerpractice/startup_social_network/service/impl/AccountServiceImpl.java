package com.netcrackerpractice.startup_social_network.service.impl;

import com.netcrackerpractice.startup_social_network.DTO.AccountDTO;
import com.netcrackerpractice.startup_social_network.entity.*;
import com.netcrackerpractice.startup_social_network.repository.AccountRepository;
import com.netcrackerpractice.startup_social_network.repository.BusinessRoleRepository;
import com.netcrackerpractice.startup_social_network.repository.ResumeRepository;
import com.netcrackerpractice.startup_social_network.repository.ResumeSkillRepository;
import com.netcrackerpractice.startup_social_network.service.AccountService;
import com.netcrackerpractice.startup_social_network.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.*;
import java.util.stream.Collectors;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private BusinessRoleRepository businessRoleRepository;

    @Autowired
    private ResumeSkillRepository resumeSkillRepository;

    @Autowired
    private AccountRepository accountRepository;


    @Autowired
    ImageService imageService;

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
    public Account updateAccount(UUID id, Account account) {
        Optional<Account> updatedAccount = accountRepository.findById(id);
        if (updatedAccount.isPresent()) {
            Account _account = updatedAccount.get();
            _account.setFirstName(account.getFirstName());
            _account.setLastName(account.getLastName());
            _account.setAboutMe(account.getAboutMe());
            _account.setBirthday(account.getBirthday());
            _account.setEducations(account.getEducations());
            _account.setFavorites(account.getFavorites());
            return saveAccount(_account);
        }
        return null;
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

    @Override
    public List<AccountDTO> spesialistsAfterSearching(SearchObject searchObject) {
        List<Account> accountList = new ArrayList<>();
        List<BusinessRole> businessRoleList = new ArrayList<>();
        List<Set<ResumeSkill>> setList = new ArrayList<>();
        List<AccountDTO> accountDTOS = new ArrayList<>();
        if (searchObject.getSkills() == null && searchObject.getRoles() == null && searchObject.getSearchString() != null) {
            List<Resume> resumeList = resumeRepository.findResumeByName(searchObject.getSearchString());
            for (Resume resume : resumeList) {
                businessRoleList.add(resume.getBusinessRole());
                setList.add(resume.getResumeSkills());
            }
            accountDTOS = buildAccountDTO(resumeList.stream().map(Resume::getAccount).collect(Collectors.toList()), businessRoleList, setList);
            return accountDTOS;
        } else if (searchObject.getSkills() != null && searchObject.getRoles() == null && searchObject.getSearchString() == null) {
            Set<Resume> setResume = new HashSet<>();
            for (String skillName : searchObject.getSkills()) {
                setResume.addAll(resumeRepository.findResumeBySkiillName(skillName));
            }
            return formAccountDTO(setResume);
        } else if (searchObject.getSkills() == null && searchObject.getRoles() != null && searchObject.getSearchString() == null) {
            Set<Resume> setResume = new HashSet<>();
            for (String roleName : searchObject.getRoles()) {
                List<Resume> resumeList = resumeRepository.findResumeByBusinessRoleName(roleName.toUpperCase());
                setResume.addAll(resumeList);
            }
            return formAccountDTO(setResume);
        } else if (searchObject.getSkills() != null && searchObject.getRoles() == null && searchObject.getSearchString() != null) {
            Set<Resume> setResume = new HashSet<>();
            for (String skillName : searchObject.getSkills()) {
                setResume.addAll(resumeRepository.findResumeBySkillNameAndAccountName(skillName, searchObject.getSearchString()));
            }
            return formAccountDTO(setResume);
        } else if (searchObject.getSkills() == null && searchObject.getRoles() != null && searchObject.getSearchString() != null) {
            Set<Resume> setResume = new HashSet<>();
            for (String roleName : searchObject.getRoles()) {
                setResume.addAll(resumeRepository.findResumeByRoleNameAndAccountName(roleName.toUpperCase(), searchObject.getSearchString()));
            }
            return formAccountDTO(setResume);
        } else if (searchObject.getSkills() != null && searchObject.getRoles() != null && searchObject.getSearchString() == null) {
            Set<Resume> setResume = new HashSet<>();
            for (String roleName : searchObject.getRoles()) {
                for (String skillName : searchObject.getSkills()) {
                    setResume.addAll(resumeRepository.findResumeByRoleNameAndSkillName(roleName.toUpperCase(), skillName));
                }
            }
            return formAccountDTO(setResume);
        } else {
            Set<Resume> setResume = new HashSet<>();
            for (String roleName : searchObject.getRoles()) {
                for (String skillName : searchObject.getSkills()) {
                    setResume.addAll(resumeRepository.findResumeByRoleNameAndSkillNameAndName(roleName.toUpperCase(), skillName, searchObject.getSearchString()));
                }
            }
            return formAccountDTO(setResume);
        }
    }

    private List<Account> accountsList(Set<Resume> setList) {
        return setList.stream().map(Resume::getAccount).collect(Collectors.toList());
    }

    private List<AccountDTO> formAccountDTO(Set<Resume> setResume){
        List<BusinessRole> businessRoleList = new ArrayList<>();
        List<Set<ResumeSkill>> setList = new ArrayList<>();
        for (Resume resume : setResume) {
            businessRoleList.add(resume.getBusinessRole());
            setList.add(resume.getResumeSkills());
        }
      return buildAccountDTO(accountsList(setResume), businessRoleList, setList);
    }

    @Override
    public List<AccountDTO> buildAccountDTO(List<Account> accountList, List<BusinessRole> businessRoleList, List<Set<ResumeSkill>> setList) {
        List<AccountDTO> accountDTOS = new ArrayList<>();
        for (int i = 0; i < accountList.size(); i++) {
            accountDTOS.add(new AccountDTO(accountList.get(i), businessRoleList.get(i), setList.get(i)));
        }
        return accountDTOS;
    }
}
