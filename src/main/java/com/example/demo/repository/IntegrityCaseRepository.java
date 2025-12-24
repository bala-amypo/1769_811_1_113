package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.example.demo.entity.*;

public interface IntegrityCaseRepository extends JpaRepository<IntegrityCase,Long>
{
java.util.List<IntegrityCase> findByStudentProfile_Id(Long id);
}

