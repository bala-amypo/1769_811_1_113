package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.EvidenceRecord;
import com.example.demo.repository.EvidenceRecordRepository;
import com.example.demo.service.EvidenceRecordService;

@Service
public class EvidenceRecordServiceImpl implements EvidenceRecordService {

    @Autowired
    private EvidenceRecordRepository evidenceRecordRepository;

    @Override
    public EvidenceRecord addEvidence(EvidenceRecord evidenceRecord) {
        if (evidenceRecord.getIntegrityCase() == null) {
            throw new IllegalArgumentException("IntegrityCase cannot be null");
        }
        return evidenceRecordRepository.save(evidenceRecord);
    }
}
