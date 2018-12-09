
package com.netcrackerpractice.startup_social_network.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.entity.Resume;
import com.netcrackerpractice.startup_social_network.entity.enums.BusinessRoleEnum;
import com.netcrackerpractice.startup_social_network.service.FavoriteService;
import com.netcrackerpractice.startup_social_network.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class InvestorController {

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private FavoriteService favoriteService;

    @GetMapping("/investor-list")
    public List<Resume> getAllInvestors() {
        return resumeService.searchAccountsByRole(BusinessRoleEnum.INVESTOR);
    }

    @PostMapping(value = "/investor-list")
    public ResponseEntity<Account> addAccountToFav(@RequestBody Account account) {
        System.out.println(account);
        favoriteService.addAccountToFavorite(account);
        return ResponseEntity.ok(account);
    }

}