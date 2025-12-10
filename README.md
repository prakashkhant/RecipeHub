
# **RecipeHub**

**A Java EE-based Recipe Sharing & Management Platform**
A full-stack web application built with JSF, EJB, and MySQL that allows users to upload recipes, manage categories, interact through comments, and browse a rich recipe collection.

---

## 🚀 **Features**

* **User Authentication**: Login & user session management
* **Recipe Management**: Add, edit, delete, and view recipes
* **Image Upload System**: Upload and store recipe images
* **Comments Module**: Users can comment on recipes
* **Admin Dashboard**: Manage users, categories, and content
* **Pagination for Activity Logs**
* **MVC Architecture (JSF + CDI + EJB)**
* **MySQL Database Integration** with entities & JPA
* **Clean Folder Structure** for maintainability

---

## 🧩 **Tech Stack**

* **Java EE / Jakarta EE**
* **JSF (Jakarta Faces)**
* **EJB (Enterprise Java Beans)**
* **JPA / Hibernate**
* **MySQL**
* **PrimeFaces (if used)**
* **Payara / GlassFish / TomEE**
* **NetBeans IDE**

---

## 📁 **Project Structure**

```text
RecipeHub/
├── src/
│   ├── java/
│   │   ├── cdi/              # Managed beans (JSF controllers)
│   │   ├── ejb/              # EJB session beans
│   │   ├── Entity/           # JPA Entities (Recipes, Users, Comments, etc.)
│   │   └── util/             # Helpers, file upload logic
│   └── webapp/
│       ├── pages/            # JSF pages (xhtml)
│       ├── resources/        # CSS, Images, JS
│       └── WEB-INF/          # faces-config, web.xml
├── sql/                      # DB schema (if provided)
└── README.md
```

---

## ⚙️ **Prerequisites**

* JDK 17+ (or depending on your Payara version)
* MySQL running locally
* Payara / GlassFish / Tomcat with JSF support
* MySQL Connector/J added to the server
* NetBeans for deployment

---

## 📦 **Setup Instructions**

### **1. Clone the repository**

```sh
git clone https://github.com/prakashkhant/RecipeHub.git
cd RecipeHub
```

### **2. Create MySQL Database**

Create a database:

```sql
CREATE DATABASE recipehub;
```

Import tables manually or from your generated JPA schema.

### **3. Update Persistence Configuration**

Open:

```
src/META-INF/healthyPU.xml
```

Update your DB credentials:

```xml
<property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/recipehub"/>
<property name="jakarta.persistence.jdbc.user" value="root"/>
<property name="jakarta.persistence.jdbc.password" value="your_password"/>
```

### **4. Configure File Upload Path**

Update your file upload directory in your bean:

```java
String uploadPath = "C:/path/to/uploads/";
```

Make sure the folder exists.

### **5. Deploy on Payara Server**

* Open NetBeans
* Add Payara Server
* Clean & Build the project
* Run → Payara deploys it automatically

Access via:

```
http://localhost:8080/HealthyRecipes
```

---

## 🧪 **Testing & Debugging Tips**

* Enable server logs in Payara Admin Console
* Ensure `MultipartConfig` is applied for file upload
* Check database connectivity using the JDBC connection pool
* If pagination or AJAX doesn’t update, check JSF component IDs

---

## 📈 **Usage**

* Login as a user or admin
* Add new recipes with images
* Comment on recipes
* View recipes with categories
* Admins can manage users & recipes
* Activity logs update and paginate correctly

---

## 🤝 **Contributing**

1. Fork the repo
2. Create a feature branch (`git checkout -b recipe-feature`)
3. Commit with meaningful messages
4. Push your branch and submit a pull request

Suggestions and improvements are always welcome!

---

## 📄 **License**

This project is distributed under the **MIT License**.
You may modify and use the code with attribution.

---

## 📞 **Contact**

**Developed & Maintained by:**
**Prakash Khant**
GitHub: [https://github.com/prakashkhant](https://github.com/prakashkhant)
Email: prakashkhant1923@gmail.com
If you'd like help deploying, improving UI, or fixing JSF/EJB issues, feel free to open an issue or message.


