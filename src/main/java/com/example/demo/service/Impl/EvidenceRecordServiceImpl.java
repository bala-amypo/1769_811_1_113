package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;

@Service
public class EvidenceRecordServiceImpl implements EvidenceRecordService {

private final EvidenceRecordRepository evidenceRepo;
private final IntegrityCaseRepository caseRepo;

public EvidenceRecordServiceImpl(
EvidenceRecordRepository evidenceRepo,
IntegrityCaseRepository caseRepo){
this.evidenceRepo=evidenceRepo;
this.caseRepo=caseRepo;
}

public EvidenceRecord submitEvidence(EvidenceRecord e){
caseRepo.findById(e.getIntegrityCase().getId()).orElseThrow();
return evidenceRepo.save(e);
}
}
