package com.netcrackerpractice.startup_social_network.controller;

import java.util.*;
import java.util.stream.Collectors;

import com.netcrackerpractice.startup_social_network.DTO.AccountDTO;
import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.entity.BusinessRole;
import com.netcrackerpractice.startup_social_network.entity.Resume;
import com.netcrackerpractice.startup_social_network.entity.ResumeSkill;
import com.netcrackerpractice.startup_social_network.entity.enums.BusinessRoleEnum;
import com.netcrackerpractice.startup_social_network.service.FavoriteService;
import com.netcrackerpractice.startup_social_network.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class SpecialistsController {

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private FavoriteService favoriteService;


    @GetMapping("/specialist-list")
    public List<AccountDTO> getAllSpescialist(@RequestParam("businessRole") String _businessRole) {
        List<AccountDTO> accountDTOS = new ArrayList<>();
        List<Account> accountList;
        List<BusinessRole> businessRoleList;
        List<Set<ResumeSkill>> setList;
        if (_businessRole.equals(BusinessRoleEnum.DESIGNER.toString())) {
            accountList = resumeService.searchAccountsByRole(BusinessRoleEnum.DESIGNER);
            businessRoleList = resumeService.listBusinessRolesafterFiltering(BusinessRoleEnum.DESIGNER);
            setList = resumeService.listResumeSkillsAfterFiltering(BusinessRoleEnum.DESIGNER);
        } else if (_businessRole.equals(BusinessRoleEnum.DEVELOPER.toString())) {
            accountList = resumeService.searchAccountsByRole(BusinessRoleEnum.DEVELOPER);
            businessRoleList = resumeService.listBusinessRolesafterFiltering(BusinessRoleEnum.DEVELOPER);
            setList = resumeService.listResumeSkillsAfterFiltering(BusinessRoleEnum.DEVELOPER);
        } else if (_businessRole.equals(BusinessRoleEnum.TE.toString())) {
            accountList = resumeService.searchAccountsByRole(BusinessRoleEnum.TE);
            businessRoleList = resumeService.listBusinessRolesafterFiltering(BusinessRoleEnum.TE);
            setList = resumeService.listResumeSkillsAfterFiltering(BusinessRoleEnum.TE);
        } else {
            accountList = resumeService.serchAllSpecialist();
            businessRoleList = resumeService.listBusinessRolesOfSpecialist();
            setList = resumeService.listResumeSkills();
            System.out.println(setList.toString());
        }
        for (int i = 0; i < accountList.size(); i++) {
            accountDTOS.add(new AccountDTO(accountList.get(i), businessRoleList.get(i), setList.get(i)));
        }
        return accountDTOS;
    }


    @PostMapping(value = "/specialist-list")
    public ResponseEntity<Account> addAccountToFav(@RequestBody Account account) {
        favoriteService.addAccountToFavorite(account);
        return ResponseEntity.ok(account);
    }


}
