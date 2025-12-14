package com.example.scc;

import com.example.scc.config.MongoConfig;
import com.example.scc.service.ComplianceService;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.Scanner;

public class ConsoleApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MongoConfig config = new MongoConfig();
        ComplianceService service = new ComplianceService(config);
        MongoCollection<Document> collection = config.getCollection();

        int choice;
        do {
            System.out.println("\n=== Security Compliance Checker ===");
            System.out.println("1. Run Compliance Check (Create)");
            System.out.println("2. View All Results (Read)");
            System.out.println("3. Update Record");
            System.out.println("4. Delete Record");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter target URL: ");
                    String url = sc.nextLine();
                    service.runChecksAndSave(url);
                    System.out.println("✅ Compliance check completed and saved!");
                }
                case 2 -> {
                    System.out.println("=== Stored Compliance Results ===");
                    for (Document doc : collection.find()) {
                        System.out.println(doc.toJson());
                    }
                }
                case 3 -> {
                    System.out.print("Enter _id to update: ");
                    String id = sc.nextLine();
                    System.out.print("Enter new target URL: ");
                    String newTarget = sc.nextLine();
                    collection.updateOne(new Document("_id", new org.bson.types.ObjectId(id)),
                            new Document("$set", new Document("target", newTarget)));
                    System.out.println("✅ Record updated!");
                }
                case 4 -> {
                    System.out.print("Enter _id to delete: ");
                    String id = sc.nextLine();
                    collection.deleteOne(new Document("_id", new org.bson.types.ObjectId(id)));
                    System.out.println("🗑️ Record deleted!");
                }
                case 5 -> System.out.println("👋 Exiting...");
                default -> System.out.println("❌ Invalid choice, try again.");
            }

        } while (choice != 5);

        sc.close();
        config.close();
    }
}
