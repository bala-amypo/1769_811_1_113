package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.PenaltyAction;
import com.example.demo.service.PenaltyActionService;

@RestController
@RequestMapping("/api/penalties")
public class PenaltyActionController {

    private final PenaltyActionService service;

    public PenaltyActionController(PenaltyActionService service) {
        this.service = service;
    }

    @PostMapping
    public PenaltyAction add(@RequestBody PenaltyAction p) {
        return service.addPenalty(p);
    }

    @GetMapping("/case/{caseId}")
    public List<PenaltyAction> byCase(@PathVariable Long caseId) {
        return service.getPenaltiesByCase(caseId);
    }

    @GetMapping("/{id}")
    public PenaltyAction get(@PathVariable Long id) {
        return service.getPenaltyById(id);
    }

    @GetMapping
    public List<PenaltyAction> all() {
        return service.getAllPenalties();
    }
}
