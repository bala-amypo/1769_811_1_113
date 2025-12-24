package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.PenaltyAction;
import com.example.demo.entity.IntegrityCase;
import com.example.demo.repository.PenaltyActionRepository;
import com.example.demo.repository.IntegrityCaseRepository;
import com.example.demo.service.PenaltyActionService;

@Service
@Transactional
public class PenaltyActionServiceImpl implements PenaltyActionService {

private final PenaltyActionRepository penaltyActionRepository;
private final IntegrityCaseRepository integrityCaseRepository;

public PenaltyActionServiceImpl(
PenaltyActionRepository penaltyActionRepository,
IntegrityCaseRepository integrityCaseRepository
) {
this.penaltyActionRepository = penaltyActionRepository;
this.integrityCaseRepository = integrityCaseRepository;
}

@Override
public PenaltyAction addPenalty(PenaltyAction penaltyAction) {

if(penaltyAction.getIntegrityCase() == null ||
penaltyAction.getIntegrityCase().getId() == null) {
throw new IllegalArgumentException("IntegrityCase is required to add a penalty");
}

IntegrityCase integrityCase =
integrityCaseRepository.findById(
penaltyAction.getIntegrityCase().getId()
).orElseThrow(() ->
new IllegalArgumentException("IntegrityCase not found")
);

penaltyAction.setIntegrityCase(integrityCase);

if("OPEN".equals(integrityCase.getStatus())) {
integrityCase.setStatus("UNDER_REVIEW");
}

integrityCaseRepository.save(integrityCase);

return penaltyActionRepository.save(penaltyAction);
}
}
