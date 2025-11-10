# Hibernate Restaurant Management System

A Java-based console application built using **JPA (Java Persistence API)** and **Hibernate ORM** to manage restaurant operations such as menu management, customer orders, and order history tracking.

---

## Features
- 🧾 **CRUD Operations** – Create, Read, Update, and Delete menu items using Hibernate.
- 👨‍🍳 **Order Management** – Place new customer orders and calculate totals automatically.
- 📜 **Order History** – View complete history of all customer orders stored in the database.
- ⚙️ **Automatic Menu Seeding** – Loads default items like Tea, Coffee, and Puffs on first run.
- 🧠 **Persistence Layer** – Uses Hibernate and JPA for database communication.

---

## Tech Stack
- **Java 8+**
- **Hibernate ORM**
- **MySQL Database**
- **JPA (Java Persistence API)**
- **Maven** for dependency management
- **Eclipse IDE**

---

##  Project Structure

##  Project Structure

Menucard/
├── src/main/java/MenuCardSystem/
│ ├── Application.java
│ ├── HibernateUtil.java
│ ├── item.java
│ ├── Order.java
│ ├── Removerow.java
├── src/main/resources/META-INF/persistence.xml
├── pom.xml

---

## How It Works
1. The program initializes with default menu items.
2. Users can:
   - View available menu items  
   - Add new items  
   - Place customer orders  
   - View order history  
   - Check customer-specific order records
3. All data is persisted using Hibernate ORM to the database.

---

##  Author
**Eswar D**  
💻 Java Full Stack Learner | 🎨 Creative Developer  
📍 Trichy, Tamil Nadu, India  
🔗 [GitHub Profile](https://github.com/EswarLeon)

---

## 🏁 License
This project is open source and available under the [MIT License](LICENSE).
