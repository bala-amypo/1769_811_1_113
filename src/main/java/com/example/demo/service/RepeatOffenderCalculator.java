package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.IntegrityCase;

public class RepeatOffenderCalculator {

public static int calculateTotalCases(List<IntegrityCase> cases) {
return cases == null ? 0 : cases.size();
}

public static String calculateSeverity(int totalCases) {
if(totalCases < 2) return null;
if(totalCases == 2) return "LOW";
if(totalCases == 3) return "MEDIUM";
return "HIGH";
}

}
