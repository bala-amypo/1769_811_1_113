package com.example.demo.repository;
import com.example.demo.entity.EvidenceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EvidenceRecordRepository extends JpaRepository<EvidenceRecord, Long> {
    List<EvidenceRecord> findByIntegrityCase_Id(Long caseId);
}