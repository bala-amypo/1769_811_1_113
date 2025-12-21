package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

import com.example.demo.entity.EvidenceRecord;
import com.example.demo.repository.EvidenceRecordRepository;
import com.example.demo.service.EvidenceRecordService;

@Service
public class EvidenceRecordServiceImpl
        implements EvidenceRecordService {

    private final EvidenceRecordRepository repo;

    public EvidenceRecordServiceImpl(EvidenceRecordRepository repo) {
        this.repo = repo;
    }

    @Override
    public EvidenceRecord submitEvidence(EvidenceRecord evidenceRecord) {

        if (evidenceRecord.getIntegrityCase() == null ||
            evidenceRecord.getIntegrityCase().getId() == null) {
            throw new IllegalArgumentException(
                    "IntegrityCase id must be provided");
        }

        return repo.save(evidenceRecord);
    }

    @Override
    public EvidenceRecord getEvidenceById(Long id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "EvidenceRecord not found"));
    }

    @Override
    public List<EvidenceRecord> getEvidenceByCase(Long caseId) {
        return repo.findByIntegrityCase_Id(caseId);
    }

    @Override
    public List<EvidenceRecord> getAllEvidence() {
        return repo.findAll();
    }
}
