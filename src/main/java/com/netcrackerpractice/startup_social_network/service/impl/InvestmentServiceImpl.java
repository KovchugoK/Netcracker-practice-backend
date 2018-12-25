package com.netcrackerpractice.startup_social_network.service.impl;

import com.netcrackerpractice.startup_social_network.entity.Investment;
import com.netcrackerpractice.startup_social_network.repository.InvestmentRepository;
import com.netcrackerpractice.startup_social_network.service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class InvestmentServiceImpl implements InvestmentService {

    @Autowired
    InvestmentRepository investmentRepository;

    @Override
    public Optional<Investment> findById(UUID id) {
        return investmentRepository.findById(id);
    }

    @Override
    public Investment saveInvestment(Investment investment) {
        return investmentRepository.save(investment);
    }

    @Override
    public Set<Investment> findTopInvestorForStartup(UUID startupId) {
        return investmentRepository.findTopInvestorForStartup(startupId);
    }
}
