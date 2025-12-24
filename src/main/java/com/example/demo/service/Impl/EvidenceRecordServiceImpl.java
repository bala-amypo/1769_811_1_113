package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.EvidenceRecord;
import com.example.demo.entity.IntegrityCase;
import com.example.demo.repository.EvidenceRecordRepository;
import com.example.demo.repository.IntegrityCaseRepository;
import com.example.demo.service.EvidenceRecordService;

@Service
@Transactional
public class EvidenceRecordServiceImpl implements EvidenceRecordService {

private final EvidenceRecordRepository evidenceRecordRepository;
private final IntegrityCaseRepository integrityCaseRepository;

public EvidenceRecordServiceImpl(
EvidenceRecordRepository evidenceRecordRepository,
IntegrityCaseRepository integrityCaseRepository
) {
this.evidenceRecordRepository = evidenceRecordRepository;
this.integrityCaseRepository = integrityCaseRepository;
}

@Override
public EvidenceRecord submitEvidence(EvidenceRecord evidenceRecord) {

if(evidenceRecord.getIntegrityCase() == null ||
evidenceRecord.getIntegrityCase().getId() == null) {
throw new IllegalArgumentException("IntegrityCase is required to submit evidence");
}

IntegrityCase integrityCase =
integrityCaseRepository.findById(
evidenceRecord.getIntegrityCase().getId()
).orElseThrow(() ->
new IllegalArgumentException("IntegrityCase not found")
);

evidenceRecord.setIntegrityCase(integrityCase);

return evidenceRecordRepository.save(evidenceRecord);
}
}
