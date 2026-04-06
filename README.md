# 📧 Gmail Test Automation (Selenium + Java)

This project demonstrates automated UI testing for email functionality using Gmail.  
It focuses on verifying that unread emails can be deleted and successfully moved to the Trash folder.

---

## 🚀 Features

- Open Gmail in Google Chrome
- Manual login support (due to security restrictions)
- Detect unread emails
- Delete selected email
- Navigate to Trash folder
- Verify email deletion
- Console-based test result output (PASS / FAIL)

---

## 🧪 Test Scenario

### TC_MAIL_005 – Delete Unread Email

**Steps:**
1. Open Gmail
2. Login to account
3. Identify unread email
4. Delete email
5. Navigate to Trash
6. Verify email exists in Trash

**Expected Result:**
- Email is moved to Trash successfully

---

## 🛠️ Tech Stack

- **Language:** Java  
- **Automation Tool:** Selenium WebDriver  
- **Test Framework:** TestNG  
- **Build Tool:** Maven  
- **Browser:** Google Chrome  

---

## ⚙️ Setup Instructions

### 1. Install Requirements
- Java JDK 17+
- Maven
- Google Chrome
- VS Code or IntelliJ

---
