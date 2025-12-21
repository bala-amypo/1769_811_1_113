package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.PenaltyAction;

public interface PenaltyActionRepository
        extends JpaRepository<PenaltyAction, Long> {

    List<PenaltyAction> findByIntegrityCase_Id(Long caseId);
}
