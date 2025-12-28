package com.example.demo.controller;

import com.example.demo.entity.PenaltyAction;
import com.example.demo.service.PenaltyActionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/penalties")
@Tag(name = "Penalty Actions")
public class PenaltyActionController {

private final PenaltyActionService penaltyActionService;

public PenaltyActionController(PenaltyActionService penaltyActionService) {
this.penaltyActionService = penaltyActionService;
}

@PostMapping
public PenaltyAction addPenalty(@RequestBody PenaltyAction penalty) {
return penaltyActionService.addPenalty(penalty);
}

@GetMapping("/case/{caseId}")
public List<PenaltyAction> getPenaltiesByCase(@PathVariable Long caseId) {
return penaltyActionService.getPenaltiesByCase(caseId);
}

@GetMapping("/{id}")
public Optional<PenaltyAction> getPenaltyById(@PathVariable Long id) {
return penaltyActionService.getPenaltyById(id);
}

@GetMapping
public List<PenaltyAction> getAllPenalties() {
return penaltyActionService.getAllPenalties();
}
}
