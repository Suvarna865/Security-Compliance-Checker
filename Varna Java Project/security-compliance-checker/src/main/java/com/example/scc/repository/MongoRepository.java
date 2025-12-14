package com.example.scc.repository;

import com.example.scc.config.MongoConfig;
import org.bson.Document;
import java.time.Instant;
import java.util.Map;

public class MongoRepository {
    private final MongoConfig mongoConfig;

    public MongoRepository(MongoConfig mongoConfig) {
        this.mongoConfig = mongoConfig;
    }

    public void save(String target, Map<String, Object> results) {
        Document doc = new Document();
        doc.append("target", target);
        doc.append("checkedAt", Instant.now().toString());
        doc.append("results", results);
        mongoConfig.getCollection().insertOne(doc);
    }
}
