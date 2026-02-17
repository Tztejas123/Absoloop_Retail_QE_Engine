Absoloop Retail QE Engine
Overview
The Absoloop Retail QE Engine is a robust, high-performance automation framework built to validate the Absoloop Retail application. This project demonstrates an advanced transition from basic sequential testing to a scalable, professional-grade solution. It is designed to explain career gaps by showcasing a deep understanding of the SDET lifecycle, specifically utilizing a narrative of 3.7 years of software testing experience.
Technical Architecture
• Design Pattern: Implemented using Page Object Model (POM) with a shared BasePage and BaseClass to ensure modularity and high maintainability.
• Parallel Execution: Engineered with ThreadLocal<WebDriver> to provide full thread safety, allowing simultaneous cross-browser testing (Chrome, Edge, Firefox) without resource contention.
• No-Argument Page Objects: Refactored Page Objects to automatically fetch driver instances from the static BaseClass.getDriver() method, reducing code complexity.
• Logging & Reporting: Integrated Log4j2 for precise console logging and Extent Reports for professional, interactive HTML reporting.
Technology Stack
• Language: Java
• Test Orchestration: TestNG
• Automation: Selenium WebDriver
• Build Tool: Maven (with customized pom.xml for version control)
• Infrastructure: Integrated Jenkinsfile and Windows Batch (.bat) scripts for CI/CD pipeline automation.
How to Run the Tests

1. Via TestNG XML
   Run parallel cross-browser suites directly:
   • ParallelCross-BrowserTesting.xml (Optimized for Chrome & Edge)
   • maintestng.xml (Full regression suite)
2. Via Command Line (Maven):Ensure your environment variables are set (Java 11 or 17 as per pom.xml configuration):
   mvn clean test

3. Via Batch File:Execute run.bat for quick local execution without an IDE.
   Jenkins Integration
   The project includes a Jenkinsfile for seamless integration into DevOps pipelines. It handles:
   • Automatic build triggers.
   • Parallel test execution on distributed nodes.
   • Automatic archival of Extent Reports and failure screenshots.
   Project Structure
   • src/test/java: Test cases and testBase logic (ThreadLocal management).
   • src/main/java: Page Objects (POM) and Utility managers.
   • screenshots/: Automated capture on test failure.
   • config.properties: Externalized environment and credential management.
