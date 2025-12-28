package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.PenaltyActionService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PenaltyActionServiceImpl implements PenaltyActionService {

private final PenaltyActionRepository penaltyRepo;
private final IntegrityCaseRepository caseRepo;

/* constructor order EXACT as test */
public PenaltyActionServiceImpl(
PenaltyActionRepository penaltyRepo,
IntegrityCaseRepository caseRepo
) {
this.penaltyRepo = penaltyRepo;
this.caseRepo = caseRepo;
}

@Override
public PenaltyAction addPenalty(PenaltyAction penalty) {
IntegrityCase c = caseRepo.findById(
penalty.getIntegrityCase().getId()
).orElseThrow(() -> new IllegalArgumentException("Case not found"));

c.setStatus("UNDER_REVIEW");
caseRepo.save(c);

penalty.setIntegrityCase(c);
return penaltyRepo.save(penalty);
}

@Override
public List<PenaltyAction> getPenaltiesByCase(Long caseId) {
return Collections.emptyList();
}

@Override
public Optional<PenaltyAction> getPenaltyById(Long id) {
return Optional.empty();
}

@Override
public List<PenaltyAction> getAllPenalties() {
return Collections.emptyList();
}
}
