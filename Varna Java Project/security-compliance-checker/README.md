# Security Compliance Checker (Java + MongoDB sync driver)

Simple CLI application that runs a couple of HTTP header based security checks against a target URL
and stores the aggregated results into a MongoDB collection.

## Build & Run

1. Install Java 17+ and Maven.
2. Ensure a MongoDB instance is running and reachable.
3. Configure `src/main/resources/application.properties` if needed.
4. Build:
   ```
   mvn clean package
   ```
5. Run:
   ```
   java -cp target/security-compliance-checker-1.0.0.jar com.example.scc.SccApplication
   ```
