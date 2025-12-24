package com.example.demo.service;

public class RepeatOffenderCalculator {

public String calculateSeverity(int totalCases){
if(totalCases==1) return "LOW";
if(totalCases==2) return "MEDIUM";
if(totalCases>=4) return "HIGH";
return "NONE";
}

public boolean isRepeatOffender(int totalCases){
return totalCases>=2;
}
}
