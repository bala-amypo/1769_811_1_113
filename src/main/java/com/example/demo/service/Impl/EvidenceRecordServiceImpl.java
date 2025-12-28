package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.EvidenceRecordService;
package com.example.demo.service.impl;

import com.example.demo.entity.EvidenceRecord;
import com.example.demo.entity.IntegrityCase;
import com.example.demo.repository.EvidenceRecordRepository;
import com.example.demo.repository.IntegrityCaseRepository;
import com.example.demo.service.EvidenceRecordService;

import org.springframework.stereotype.Service;   // 🔴 ADD THIS

@Service
public class EvidenceRecordServiceImpl implements EvidenceRecordService {
...
}

import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Service
public class EvidenceRecordServiceImpl implements EvidenceRecordService {

private final EvidenceRecordRepository evidenceRepo;
private final IntegrityCaseRepository caseRepo;

/* constructor order EXACT as test */
public EvidenceRecordServiceImpl(
EvidenceRecordRepository evidenceRepo,
IntegrityCaseRepository caseRepo
) {
this.evidenceRepo = evidenceRepo;
this.caseRepo = caseRepo;
}

@Override
public EvidenceRecord submitEvidence(EvidenceRecord evidence) {
IntegrityCase c = caseRepo.findById(
evidence.getIntegrityCase().getId()
).orElseThrow(() -> new IllegalArgumentException("Case not found"));

evidence.setIntegrityCase(c);
return evidenceRepo.save(evidence);
}

@Override
public List<EvidenceRecord> getEvidenceByCase(Long caseId) {
return Collections.emptyList();
}

@Override
public Optional<EvidenceRecord> getEvidenceById(Long id) {
return Optional.empty();
}

@Override
public List<EvidenceRecord> getAllEvidence() {
return Collections.emptyList();
}
}
