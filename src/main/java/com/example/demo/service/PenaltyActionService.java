package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.PenaltyAction;

public interface PenaltyActionService {

    PenaltyAction addPenalty(PenaltyAction penalty);

    List<PenaltyAction> getPenaltiesByCase(Long caseId);

    PenaltyAction getPenaltyById(Long id);

    List<PenaltyAction> getAllPenalties();
}
