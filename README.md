# HerBalance UI Automation (Stub‑Mode Framework)

This repository contains the **UI automation framework for the HerBalance Application **.  

## About the HerBalance Application
HerBalance is a women’s health and wellness platform designed to make weight loss more personalized, sustainable, and aligned with the body’s natural rhythms. The application provides a tailored experience through:

### Cycle‑Synced Workouts & Nutrition
HerBalance adapts fitness and nutrition recommendations based on the user’s hormonal cycle phases. Each phase comes with unique energy levels, metabolic changes, and recovery needs — the app adjusts plans accordingly to help users feel better and perform better.

### Blood Work–Driven Insights
Users can upload their lab results, and the system analyzes key biomarkers related to metabolism, inflammation, thyroid health, and nutrient levels. These insights help personalize recommendations and highlight areas that may impact weight management.

### Personalized Health Dashboard
The app provides a unified dashboard where users can track:

Cycle phase

Weight trends

Activity levels

Nutrition habits

Supplement or medication use

This creates a holistic view of the user’s health journey.

##  Key Features

Since the real application is **not available**, this framework is built in a **stubbed-out, UI‑agnostic mode**, allowing development to continue without waiting for the frontend.

The goal is to create a **scalable, maintainable, JSON‑driven automation framework** that can seamlessly switch from **stub mode** to **real UI mode** once the application becomes available.

### 🔹 **Stub‑Mode Architecture**
- Allows automation development **before the application exists**
- All page validations and flows are simulated using **stub logic**
- Real UI interactions can be enabled later with zero architectural changes

### 🔹 **JSON‑Driven Test Data**
- All onboarding steps use structured JSON files
- No hardcoded values in step definitions
- Easy to extend, modify, or add new scenarios

### 🔹 **Clean Page Object Model**
- Page Objects contain **only UI logic**
- No business rules or JSON parsing inside Page Objects
- Step Definitions handle all business logic and JSON lookup

### 🔹 **Cucumber BDD**
- Human‑readable feature files
- Scenario Outlines for data‑driven testing
- Clear separation of behavior vs. implementation

### 🔹 **Scalable Framework Design**
- Supports onboarding flows such as:
  - Personal Details  
  - Menstrual Cycle  
  - Diet Preferences  
  - Food Allergies  
  - Physical Activity  
  - Medications & Supplements  
- Easy to plug in new onboarding steps

### 🔹 **Conditional Hooks**
This eliminates repetitive Given steps and keeps feature files clean and business‑focused.

### 🔹 **Centralized navigation logic**
All navigation is handled in one place: NavigationHelper.

### 🔹 **Parallel Runs**
Parallel execution is enabled using TestNG’s parallel DataProvider mechanism. Each Cucumber scenario is supplied to TestNG through a DataProvider with parallel = true. This allows scenarios to run concurrently across multiple threads. The TestNG suite (testng.xml) controls the thread count and parallel mode.

### 🔹 **Allure Reporting**
Allure reporting is integrated into the framework to generate rich, interactive reports that capture scenario steps, attachments, and failure screenshots from both the main run and the automatic rerun suite.

### 🔹 **Rerun failed tests**
This framework supports automatic rerun of failed Cucumber scenarios using the Cucumber rerun plugin and a dedicated rerun test runner. When the main suite finishes, the rerun suite is triggered automatically and executes only the scenarios listed in target/failed_scenarios.txt. 

## Setup

### Clone the repository
git clone https://github.com/srividya-sundaravadivelu/herbalance_ui_automation.git

cd herbalance_ui_automation

### Install dependencies
mvn clean install

### Configuration

Edit src/test/resources/config/config.properties:

stubMode=true

browser=chrome

## Running Tests

### Run all tests

mvn clean test

### Run tests by tag

mvn clean test -Dcucumber.filter.tags="@fileUpload"

## Tech Stack

Java 17

Selenium WebDriver

Cucumber BDD

TestNG

Maven

Jackson (JSON parsing)

## Allure Report

After the test run (main + automatic rerun), Allure results are stored in target/allure-results.
Generate and open the interactive report with:
allure serve target/allure-results

<img width="1919" height="933" alt="image" src="https://github.com/user-attachments/assets/1f0b31d5-2e80-4db2-b345-8bc518b52ba0" />
<img width="1916" height="722" alt="image" src="https://github.com/user-attachments/assets/119fa883-7cba-4d5a-a571-e49322d05d72" />
<img width="1919" height="973" alt="image" src="https://github.com/user-attachments/assets/29c36d96-f7fc-4893-b82b-56c42857f3d1" />
<img width="1917" height="966" alt="image" src="https://github.com/user-attachments/assets/dbb3ee36-e323-465a-9389-51c346db6915" />
<img width="1919" height="967" alt="image" src="https://github.com/user-attachments/assets/8d559be8-f066-4213-9af5-4741e17fb989" />
<img width="1919" height="966" alt="image" src="https://github.com/user-attachments/assets/96bfa5db-bf94-4c41-a39d-11b176ae031b" />











