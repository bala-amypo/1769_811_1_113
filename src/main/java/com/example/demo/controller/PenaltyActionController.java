package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.PenaltyAction;
import com.example.demo.service.PenaltyActionService;

@RestController
@RequestMapping("/api/penalties")
public class PenaltyActionController {

private final PenaltyActionService penaltyActionService;

public PenaltyActionController(PenaltyActionService penaltyActionService) {
this.penaltyActionService = penaltyActionService;
}

@PostMapping
public PenaltyAction add(@RequestBody PenaltyAction penaltyAction) {
return penaltyActionService.addPenalty(penaltyAction);
}
}
