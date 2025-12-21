package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.PenaltyAction;
import com.example.demo.repository.PenaltyActionRepository;
import com.example.demo.service.PenaltyActionService;

@Service
public class PenaltyActionServiceImpl implements PenaltyActionService {

    @Autowired
    private PenaltyActionRepository penaltyActionRepository;

    @Override
    public PenaltyAction addPenalty(PenaltyAction penaltyAction) {
        if (penaltyAction.getIntegrityCase() == null) {
            throw new IllegalArgumentException("IntegrityCase cannot be null");
        }
        return penaltyActionRepository.save(penaltyAction);
    }
}
