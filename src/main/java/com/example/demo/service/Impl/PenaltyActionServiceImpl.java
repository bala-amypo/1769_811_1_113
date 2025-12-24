package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;

@Service
public class PenaltyActionServiceImpl implements PenaltyActionService {

private final PenaltyActionRepository penaltyRepo;
private final IntegrityCaseRepository caseRepo;

public PenaltyActionServiceImpl(
PenaltyActionRepository penaltyRepo,
IntegrityCaseRepository caseRepo){
this.penaltyRepo=penaltyRepo;
this.caseRepo=caseRepo;
}

public PenaltyAction addPenalty(PenaltyAction p){
IntegrityCase c=caseRepo.findById(p.getIntegrityCase().getId()).orElseThrow();
c.setStatus("UNDER_REVIEW");
caseRepo.save(c);
return penaltyRepo.save(p);
}
}
