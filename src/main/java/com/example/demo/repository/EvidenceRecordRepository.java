package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.EvidenceRecord;

public interface EvidenceRecordRepository extends JpaRepository<EvidenceRecord, Long> {
}
