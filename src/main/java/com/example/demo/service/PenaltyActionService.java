package com.example.demo.service;

import com.example.demo.entity.PenaltyAction;
import java.util.List;
import java.util.Optional;

public interface PenaltyActionService {

PenaltyAction addPenalty(PenaltyAction penalty);

List<PenaltyAction> getPenaltiesByCase(Long caseId);

Optional<PenaltyAction> getPenaltyById(Long id);

List<PenaltyAction> getAllPenalties();
}
