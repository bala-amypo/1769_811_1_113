package com.example.demo.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.demo.entity.EvidenceRecord;
import com.example.demo.repository.EvidenceRecordRepository;
import com.example.demo.service.EvidenceRecordService;

@Service
public class EvidenceRecordServiceImpl implements EvidenceRecordService {

    private final EvidenceRecordRepository repo;

    public EvidenceRecordServiceImpl(EvidenceRecordRepository repo) {
        this.repo = repo;
    }

    public EvidenceRecord submitEvidence(EvidenceRecord e) {
        return repo.save(e);
    }

    public List<EvidenceRecord> getEvidenceByCase(Long caseId) {
        return repo.findByIntegrityCase_Id(caseId);
    }

    public EvidenceRecord getEvidenceById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<EvidenceRecord> getAllEvidence() {
        return repo.findAll();
    }
}
