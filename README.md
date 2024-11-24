
# FitPeo Revenue Calculator Automation

<div align="center">
    <img src="https://github.com/yuvrajchavale/FitPeo/blob/main/154388_8b1a872b040dcaa8_001full.jpg" alt="FitPeo Logo" >
</div>

## 🌟 Overview
This repository contains a Selenium-based automation framework designed to test the **Revenue Calculator** on the FitPeo website. The project automates critical functionalities, including slider adjustments, CPT code selection, and reimbursement validation, ensuring the system behaves as expected.

---

## 🚀 Features
- **Automated Navigation**: Opens the FitPeo homepage and navigates to the Revenue Calculator page.
- **Slider Adjustment**: Precisely adjusts slider values dynamically or via the text field.
- **CPT Code Selection**: Automates selection of specific CPT codes:
  - CPT-99091
  - CPT-99453
  - CPT-99454
  - CPT-99474
- **Reimbursement Validation**: Ensures the displayed "Total Recurring Reimbursement" value is correct.
- **Robust Error Handling**: Incorporates exception handling and clear logs.

---

## 📑 Project Structure
```plaintext
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── secondLogin
│   │   │   │   └── RevenueCalculatorAutomation.java
├── README.md
├── pom.xml (if Maven is used)
```

---

## 🛠️ Technologies Used
- **Programming Language**: Java
- **Automation Framework**: Selenium WebDriver
- **Browser**: Google Chrome
- **Build Tool**: Maven (optional)
- **IDE**: IntelliJ IDEA / Eclipse

---

## ⚙️ Setup Instructions
### Prerequisites
1. Install **Java JDK** (version 8 or higher).
2. Install **ChromeDriver** compatible with your Chrome browser version.
3. Install an IDE such as IntelliJ IDEA or Eclipse.

### Installation
1. Clone this repository:
   ```bash
   git clone https://github.com/YOUR_USERNAME/FitPeo_Revenue_Calculator_Automation.git
   ```
2. Navigate to the project directory:
   ```bash
   cd FitPeo_Revenue_Calculator_Automation
   ```
3. Add Selenium dependencies to your project:
   - If using Maven, add the following to `pom.xml`:
     ```xml
     <dependency>
         <groupId>org.seleniumhq.selenium</groupId>
         <artifactId>selenium-java</artifactId>
         <version>4.11.0</version>
     </dependency>
     ```
4. Configure ChromeDriver in your system's PATH.

### Execution
Run the test script:
```bash
java -cp bin secondLogin.RevenueCalculatorAutomation
```

---

## 📝 Test Scenarios
### Automated Steps
1. Navigate to the FitPeo homepage.
2. Open the Revenue Calculator page.
3. Adjust the slider to **820** (using the text box for precise control).
4. Validate the slider value and its effect on the text box.
5. Update the text box to **560** and confirm the slider reflects this value.
6. Select CPT codes:
   - CPT-99091
   - CPT-99453
   - CPT-99454
   - CPT-99474
7. Validate the Total Recurring Reimbursement value matches **$110700**.

---

## 🎯 Example Output
```plaintext
Navigated to Revenue Calculator page.
Slider value successfully set to: 820
Slider value validated successfully. Value: 820
Text field updated to value: 560
Slider value validated successfully. Value: 560
CPT codes selected successfully.
Reimbursement validation passed. Total: $110700
```

---

## 📂 Future Enhancements
- Add compatibility for multiple browsers (Firefox, Edge).
- Include detailed reporting with tools like Allure.
- Implement a CI/CD pipeline for automated test execution.

---

## 🤝 Contributing
Contributions are welcome! Please fork this repository and submit a pull request for any improvements or bug fixes.

---

## 📄 License
This project is licensed under the MIT License. See the `LICENSE` file for details.

---

<div align="center">
    <strong>Happy Testing! 🚀</strong>
</div>
```

---

### **How to Use**:
1. Save the above content in a file named `README.md` in your repository root.
2. Replace placeholder links (e.g., `YOUR_USERNAME`, screenshot URL) with actual values.
3. Commit and push to your GitHub repository:
   ```bash
   git add README.md
   git commit -m "Added README for FitPeo Automation"
   git push origin main
   ```

This README will provide clear project documentation and enhance the repository's presentation on GitHub.
