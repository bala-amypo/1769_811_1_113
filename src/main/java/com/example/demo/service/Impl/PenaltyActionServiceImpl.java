package com.example.demo.service.impl;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.entity.PenaltyAction;
import com.example.demo.repository.IntegrityCaseRepository;
import com.example.demo.repository.PenaltyActionRepository;
import com.example.demo.service.PenaltyActionService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PenaltyActionServiceImpl implements PenaltyActionService {

    private final PenaltyActionRepository penaltyRepo;
    private final IntegrityCaseRepository caseRepo;

    public PenaltyActionServiceImpl(PenaltyActionRepository penaltyRepo, IntegrityCaseRepository caseRepo) {
        this.penaltyRepo = penaltyRepo;
        this.caseRepo = caseRepo;
    }

    @Override
    public PenaltyAction addPenalty(PenaltyAction penalty) {
        IntegrityCase c = caseRepo.findById(penalty.getIntegrityCase().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Case not found"));
        
        // Rule: Cases with penalties automatically move to UNDER_REVIEW
        c.setStatus("UNDER_REVIEW");
        caseRepo.save(c);

        return penaltyRepo.save(penalty);
    }

    @Override
    public List<PenaltyAction> getPenaltiesByCase(Long caseId) {
        return penaltyRepo.findByIntegrityCase_Id(caseId);
    }

    @Override
    public Optional<PenaltyAction> getPenaltyById(Long id) {
        return penaltyRepo.findById(id);
    }

    @Override
    public List<PenaltyAction> getAllPenalties() {
        return penaltyRepo.findAll();
    }
}