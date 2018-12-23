package com.netcrackerpractice.startup_social_network.controller;

import com.netcrackerpractice.startup_social_network.dto.ResumeDTO;
import com.netcrackerpractice.startup_social_network.entity.Favorite;
import com.netcrackerpractice.startup_social_network.entity.SearchObject;
import com.netcrackerpractice.startup_social_network.mapper.ResumeMapper;
import com.netcrackerpractice.startup_social_network.repository.BusinessRoleRepository;
import com.netcrackerpractice.startup_social_network.service.AccountService;
import com.netcrackerpractice.startup_social_network.service.FavoriteService;
import com.netcrackerpractice.startup_social_network.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class SpecialistsController {

    @Autowired
    private ResumeMapper resumeMapper;

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private BusinessRoleRepository businessRoleRepository;


    @GetMapping("/specialist-list")
    public List<ResumeDTO> getAllSpecialists(SearchObject _searchObj) {
        List<ResumeDTO> resumeDTOS = new ArrayList<>();
        if (_searchObj.getSkills().length != 0 || _searchObj.getRoles().length != 0 || _searchObj.getSearchString() != null) {
            resumeService.specialistsAfterSearching(_searchObj).forEach(resume -> resumeDTOS.add(resumeMapper.entityToDto(resume)));
            return resumeDTOS;
        } else {
            resumeService.searchAllSpecialist().forEach(resume -> resumeDTOS.add(resumeMapper.entityToDto(resume)));
            System.out.println(resumeDTOS);
            return resumeDTOS;
        }
    }

    @PostMapping(value = "/specialist-list/{id}")
    public ResponseEntity<Favorite> addAccountToFav(@RequestBody Favorite favorite, @PathVariable UUID id) {
        favoriteService.addAccountToFavorite(favorite, id);
        return ResponseEntity.ok(favorite);
    }
}
