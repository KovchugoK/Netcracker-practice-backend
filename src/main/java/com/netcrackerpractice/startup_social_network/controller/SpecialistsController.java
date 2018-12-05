package com.netcrackerpractice.startup_social_network.controller;

import java.util.*;
import java.util.stream.Collectors;

import com.netcrackerpractice.startup_social_network.DTO.AccountDTO;
import com.netcrackerpractice.startup_social_network.entity.*;
import com.netcrackerpractice.startup_social_network.entity.enums.BusinessRoleEnum;
import com.netcrackerpractice.startup_social_network.service.AccountService;
import com.netcrackerpractice.startup_social_network.service.FavoriteService;
import com.netcrackerpractice.startup_social_network.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class   SpecialistsController {

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private FavoriteService favoriteService;


    @GetMapping("/specialist-list")
    public List<AccountDTO> getAllSpescialist(SearchObject _searchObj) {
        if (_searchObj.getSkills() != null || _searchObj.getRoles() != null || _searchObj.getSearchString() != null) {
            return accountService.spesialistsAfterSearching(_searchObj);
        } else {
            List<Account> accountList = resumeService.serchAllSpecialist();
            List<BusinessRole> businessRoleList = resumeService.listBusinessRolesOfSpecialist();
            List<Set<ResumeSkill>> setList = resumeService.listResumeSkillsOfspecialists();
            return accountService.buildAccountDTO(accountList, businessRoleList, setList);
        }
    }


    @PostMapping(value = "/specialist-list")
    public ResponseEntity<Account> addAccountToFav(@RequestBody Account account) {
        favoriteService.addAccountToFavorite(account);
        return ResponseEntity.ok(account);
    }


}
