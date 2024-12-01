# Chat App

A simple chat application built with modern Android development practices and guidelines to be used for interview purposes.

---

## **Planned Improvements**

This section outlines the features and improvements that could be added to the project:

### **1. Add Pagination**
- Implement pagination for loading conversations and messages incrementally.
- Use the `Paging 3` library to ensure smooth scrolling and efficient data loading.

### **2. Add Unit Tests**
- Write unit tests for the ViewModel and Repository layers to ensure logic correctness.
- Use the `JUnit` and `MockK` libraries for mocking dependencies.

### **3. Add UI Tests**
- Create UI tests to validate the user interface and interactions.
- Use the `Jetpack Compose Testing` library to test composable screens.
- Cover major user flows such as viewing conversations and sending messages.

### **4. Add Room Database Tests**
- Add tests to ensure Room database queries and data persistence work as expected.
- Use an in-memory database setup for isolated testing.

### **5. Decouple Navigation from UI**
- Separate navigation logic from UI composables for better modularity.
- Use a centralized navigation controller or implement dependency injection for navigators.

---
