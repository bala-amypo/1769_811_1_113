package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.example.demo.entity.*;


public interface PenaltyActionRepository extends JpaRepository<PenaltyAction,Long>{}

