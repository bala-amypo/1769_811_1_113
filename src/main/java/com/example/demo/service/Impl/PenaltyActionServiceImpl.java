package com.example.demo.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.demo.entity.PenaltyAction;
import com.example.demo.repository.PenaltyActionRepository;
import com.example.demo.service.PenaltyActionService;

@Service
public class PenaltyActionServiceImpl implements PenaltyActionService {

    private final PenaltyActionRepository repo;

    public PenaltyActionServiceImpl(PenaltyActionRepository repo) {
        this.repo = repo;
    }

    public PenaltyAction addPenalty(PenaltyAction p) {
        return repo.save(p);
    }

    public List<PenaltyAction> getPenaltiesByCase(Long caseId) {
        return repo.findByIntegrityCase_Id(caseId);
    }

    public PenaltyAction getPenaltyById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<PenaltyAction> getAllPenalties() {
        return repo.findAll();
    }
}
