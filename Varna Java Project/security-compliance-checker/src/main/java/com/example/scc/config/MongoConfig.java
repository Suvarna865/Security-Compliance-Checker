package com.example.scc.config;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MongoConfig {
    private MongoClient client;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public MongoConfig() {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("src/main/resources/application.properties"));
            String uri = props.getProperty("mongodb.uri", "mongodb://localhost:27017");
            String dbName = props.getProperty("mongodb.database", "scc_db");
            String coll = props.getProperty("mongodb.collection", "compliance_results");

            ConnectionString connString = new ConnectionString(uri);
            client = MongoClients.create(connString);
            database = client.getDatabase(dbName);
            collection = database.getCollection(coll);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration", e);
        }
    }

    public MongoCollection<Document> getCollection() {
        return collection;
    }

    public void close() {
        if (client != null) client.close();
    }
}
