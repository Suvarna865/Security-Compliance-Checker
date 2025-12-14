package com.example.scc.checks;

import java.util.Map;

public interface ComplianceCheck {
    /**
     * Run compliance check for the given target (URL or path) and return results map.
     */
    Map<String, Object> run(String target) throws Exception;

    String getName();
}
