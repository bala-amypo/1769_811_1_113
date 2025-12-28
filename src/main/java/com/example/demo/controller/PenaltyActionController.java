package com.example.demo.controller;

import com.example.demo.entity.PenaltyAction;
import com.example.demo.service.PenaltyActionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/penalties")
public class PenaltyActionController {

    private final PenaltyActionService service;

    public PenaltyActionController(PenaltyActionService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<PenaltyAction> addPenalty(@RequestBody PenaltyAction penalty) {
        return ResponseEntity.ok(service.addPenalty(penalty));
    }

    @GetMapping("/case/{caseId}")
    public ResponseEntity<List<PenaltyAction>> getPenaltiesForCase(@PathVariable Long caseId) {
        return ResponseEntity.ok(service.getPenaltiesByCase(caseId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PenaltyAction> getPenaltyById(@PathVariable Long id) {
        return service.getPenaltyById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PenaltyAction>> listAllPenalties() {
        return ResponseEntity.ok(service.getAllPenalties());
    }
}