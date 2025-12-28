package com.example.demo.service.impl;

import com.example.demo.entity.EvidenceRecord;
import com.example.demo.repository.EvidenceRecordRepository;
import com.example.demo.repository.IntegrityCaseRepository;
import com.example.demo.service.EvidenceRecordService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EvidenceRecordServiceImpl implements EvidenceRecordService {

    private final EvidenceRecordRepository evidenceRepo;
    private final IntegrityCaseRepository caseRepo;

    public EvidenceRecordServiceImpl(EvidenceRecordRepository evidenceRepo, IntegrityCaseRepository caseRepo) {
        this.evidenceRepo = evidenceRepo;
        this.caseRepo = caseRepo;
    }

    @Override
    public EvidenceRecord submitEvidence(EvidenceRecord evidence) {
        if (evidence.getIntegrityCase() != null) {
            caseRepo.findById(evidence.getIntegrityCase().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Case not found"));
        }
        return evidenceRepo.save(evidence);
    }

    @Override
    public List<EvidenceRecord> getEvidenceByCase(Long caseId) {
        return evidenceRepo.findByIntegrityCase_Id(caseId);
    }

    @Override
    public Optional<EvidenceRecord> getEvidenceById(Long id) {
        return evidenceRepo.findById(id);
    }

    @Override
    public List<EvidenceRecord> getAllEvidence() {
        return evidenceRepo.findAll();
    }
}