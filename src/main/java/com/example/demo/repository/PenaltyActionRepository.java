package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.PenaltyAction;

public interface PenaltyActionRepository extends JpaRepository<PenaltyAction, Long> {
}
