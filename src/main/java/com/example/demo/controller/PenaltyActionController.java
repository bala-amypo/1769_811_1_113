package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.PenaltyAction;
import com.example.demo.service.PenaltyActionService;

@RestController
@RequestMapping("/api/penalty-actions")
public class PenaltyActionController {

    @Autowired
    private PenaltyActionService penaltyActionService;

    @PostMapping
    public PenaltyAction addPenalty(@RequestBody PenaltyAction penaltyAction) {
        return penaltyActionService.addPenalty(penaltyAction);
    }
}
