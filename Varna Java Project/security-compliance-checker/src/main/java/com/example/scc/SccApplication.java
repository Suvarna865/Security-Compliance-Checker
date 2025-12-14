package com.example.scc;

import com.example.scc.config.MongoConfig;
import com.example.scc.service.ComplianceService;

public class SccApplication {
    public static void main(String[] args) {
        MongoConfig config = new MongoConfig();
        ComplianceService service = new ComplianceService(config);

        // Example target (in real use you may read files or fetch URLs)
        String targetUrl = "https://example.com";

        service.runChecksAndSave(targetUrl);

        // Close resources
        config.close();
        System.out.println("Done.");
    }
}
