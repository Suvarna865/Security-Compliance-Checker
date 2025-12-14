package com.example.scc.checks.impl;

import com.example.scc.checks.ComplianceCheck;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CookieSecurityCheck implements ComplianceCheck {
    @Override
    public Map<String, Object> run(String target) throws Exception {
        Map<String, Object> out = new HashMap<>();
        URL url = new URL(target);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setInstanceFollowRedirects(false);
        conn.connect();

        Map<String, List<String>> headers = conn.getHeaderFields();
        List<String> setCookie = headers.get("Set-Cookie");
        boolean cookieSecure = false;
        boolean cookieHttpOnly = false;
        if (setCookie != null) {
            for (String c : setCookie) {
                if (c.toLowerCase().contains("secure")) cookieSecure = true;
                if (c.toLowerCase().contains("httponly")) cookieHttpOnly = true;
            }
        }
        out.put("setCookieHeadersFound", setCookie != null);
        out.put("cookieSecure", cookieSecure);
        out.put("cookieHttpOnly", cookieHttpOnly);
        return out;
    }

    @Override
    public String getName() { return "CookieSecurityCheck"; }
}
