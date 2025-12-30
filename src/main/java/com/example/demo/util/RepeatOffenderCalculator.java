package com.example.demo.util;

import java.util.List;
import org.springframework.stereotype.Component;
import com.example.demo.entity.IntegrityCase;

@Component
public class RepeatOffenderCalculator {

public boolean isRepeatOffender(List<IntegrityCase> cases) {

if (cases == null || cases.size() < 2) {
return false;
}

long seriousCases = cases.stream()
.filter(c -> c.isConfirmed())
.count();

return seriousCases >= 2;
}
}
