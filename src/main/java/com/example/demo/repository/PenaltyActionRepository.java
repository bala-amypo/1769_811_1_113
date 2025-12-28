package com.example.demo.repository;
import com.example.demo.entity.PenaltyAction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PenaltyActionRepository extends JpaRepository<PenaltyAction, Long> {
    List<PenaltyAction> findByIntegrityCase_Id(Long caseId);
}