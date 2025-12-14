package com.example.scc.model;

import java.time.Instant;
import java.util.Map;

public class ComplianceResult {
    private String target;
    private Instant checkedAt;
    private Map<String, Object> results;

    public ComplianceResult() {}

    public ComplianceResult(String target, Instant checkedAt, Map<String, Object> results) {
        this.target = target;
        this.checkedAt = checkedAt;
        this.results = results;
    }

    // getters and setters

    public String getTarget() { return target; }
    public void setTarget(String target) { this.target = target; }
    public Instant getCheckedAt() { return checkedAt; }
    public void setCheckedAt(Instant checkedAt) { this.checkedAt = checkedAt; }
    public Map<String, Object> getResults() { return results; }
    public void setResults(Map<String, Object> results) { this.results = results; }
}
