package com.example.scc.service;

import com.example.scc.checks.ComplianceCheck;
import com.example.scc.checks.impl.ContentSecurityPolicyCheck;
import com.example.scc.checks.impl.CookieSecurityCheck;
import com.example.scc.config.MongoConfig;
import com.example.scc.repository.MongoRepository;

import java.util.HashMap;
import java.util.Map;

public class ComplianceService {
    private final MongoRepository repo;

    public ComplianceService(MongoConfig config) {
        this.repo = new MongoRepository(config);
    }

    public void runChecksAndSave(String target) {
        Map<String, Object> aggregated = new HashMap<>();
        ComplianceCheck[] checks = new ComplianceCheck[] {
                new CookieSecurityCheck(),
                new ContentSecurityPolicyCheck()
        };

        for (ComplianceCheck check : checks) {
            try {
                aggregated.put(check.getName(), check.run(target));
            } catch (Exception e) {
                Map<String, Object> err = new HashMap<>();
                err.put("error", e.getMessage());
                aggregated.put(check.getName(), err);
            }
        }

        repo.save(target, aggregated);
    }
}
