package com.netcrackerpractice.startup_social_network.controller;

import com.netcrackerpractice.startup_social_network.dto.StartupDTO;
import com.netcrackerpractice.startup_social_network.entity.Investment;
import com.netcrackerpractice.startup_social_network.entity.Startup;
import com.netcrackerpractice.startup_social_network.mapper.StartupMapper;
import com.netcrackerpractice.startup_social_network.payload.SearchStartupsRequest;
import com.netcrackerpractice.startup_social_network.service.InvestmentService;
import com.netcrackerpractice.startup_social_network.service.StartupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        StartupDTO startupDTO = startupMapper.entityToDto(startupService.findStartupById(id).get());
//        if (startupDTO.getStartupInvestments() != null) {
//            Map<Account, Integer> map = (startupDTO.getStartupInvestments().stream().collect(Collectors.groupingBy(Investment::getInvestor, Collectors.summingInt(Investment::getSumOfInvestment))));
//            Set<Investment> inv = new HashSet<>();
//            Startup sWithId = new Startup();
//            sWithId.setId(id);
//            for (Map.Entry<Account, Integer> entry : map.entrySet()) {
//                inv.add(new Investment(UUID.randomUUID(), entry.getKey(), sWithId, entry.getValue()));
//            }
//            startupDTO.setStartupInvestments(inv);
//        }

//        startupDTO.setStartupInvestments(investmentService.findTopInvestorForStartup(id));
        return startupDTO;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStartup(@PathVariable(name = "id") UUID id) {
        startupService.deleteStartupById(id);
    }

    @PostMapping("/create")
    public Startup saveStartup(@RequestBody StartupDTO startup) {
        return startupService.saveStartup(startupMapper.dtoToEntity(startup), startup.getImage());
    }

    @PutMapping("/update/{id}")
    public StartupDTO updateStartup(@PathVariable(name = "id") UUID id, @RequestBody StartupDTO startup) {
        try {
            return startupMapper.entityToDto(startupService.updateStartup(id, startupMapper.dtoToEntity(startup), startup.getImage()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/make-investment")
    public Investment makeInvestment(@RequestBody Investment investment) {
        return investmentService.saveInvestment(investment);
    }

}