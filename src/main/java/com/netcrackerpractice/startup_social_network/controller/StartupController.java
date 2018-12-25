package com.netcrackerpractice.startup_social_network.controller;

import com.netcrackerpractice.startup_social_network.dto.StartupDTO;
import com.netcrackerpractice.startup_social_network.entity.Investment;
import com.netcrackerpractice.startup_social_network.entity.Startup;
import com.netcrackerpractice.startup_social_network.mapper.StartupMapper;
import com.netcrackerpractice.startup_social_network.payload.SearchStartupsRequest;
import com.netcrackerpractice.startup_social_network.service.InvestmentService;
import com.netcrackerpractice.startup_social_network.service.StartupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/startup")
public class StartupController {


    @Autowired
    private StartupMapper startupMapper;

    @Autowired
    private StartupService startupService;

    @Autowired
    private InvestmentService investmentService;


    @GetMapping("/startup-list")
    public List<Startup> getStartupList() {
        return startupService.findAll();
    }

    @GetMapping("/search-startups")
    public List<StartupDTO> searchStartups(SearchStartupsRequest searchStartupsRequest) {
        return startupService.searchStartups(
                searchStartupsRequest.getStartupNameContains(),
                searchStartupsRequest.getCreator(),
                searchStartupsRequest.getSortBy(),
                searchStartupsRequest.getSortDirection(),
                searchStartupsRequest.getAccountID()
        );
    }

    @GetMapping("/{id}")
    public StartupDTO getStartupById(@PathVariable(name = "id") UUID id) {
        return startupMapper.entityToDto(startupService.findStartupById(id).get());
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStartup(@PathVariable(name = "id") UUID id) {
        startupService.deleteStartupById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<?> saveStartup(@Valid @RequestBody StartupDTO startup) {
        return startupService.saveStartup(startupMapper.dtoToEntity(startup), startup.getImage());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStartup(@PathVariable(name = "id") UUID id, @Valid @RequestBody StartupDTO startup) {
        return startupService.updateStartup(id, startupMapper.dtoToEntity(startup), startup.getImage());
    }

    @PostMapping("/make-investment")
    public Investment makeInvestment(@Valid @RequestBody Investment investment) {
        return investmentService.saveInvestment(investment);
    }

    @GetMapping("/permission-to-edit")
    public Boolean checkPermissionToEdit(@RequestParam(name = "accountId") UUID accountId,
                                         @RequestParam(name = "startupId") UUID startupId) {
        if (accountId != null && !accountId.toString().equals("")) {
            return this.startupService.checkPermissionToEditStartup(accountId, startupId);
        } else {
            return false;
        }
    }

}