package com.example.scc.checks.impl;

import com.example.scc.checks.ComplianceCheck;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ContentSecurityPolicyCheck implements ComplianceCheck {
    @Override
    public Map<String, Object> run(String target) throws Exception {
        Map<String, Object> out = new HashMap<>();
        URL url = new URL(target);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        String csp = conn.getHeaderField("Content-Security-Policy");
        out.put("cspPresent", csp != null && !csp.isEmpty());
        out.put("cspHeader", csp);
        return out;
    }

    @Override
    public String getName() { return "ContentSecurityPolicyCheck"; }
}
