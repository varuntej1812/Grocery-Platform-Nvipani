# 🛒 Grocery Microservices Platform

A microservices-based grocery ordering system using Spring Boot, Spring Cloud, Docker, PostgreSQL, and Firebase Authentication.

---

 ## Set up Firebase Credentials:
   - Download your Firebase Admin SDK private key JSON from your Firebase project.
   - Rename it to `firebase-service-account.json` and place it in:
     ```
     user-service/src/main/resources/
     ```

 ## Build JARs using Maven:
   GitHub does not store `target/` folders (due to size restrictions), so you must build them:
   ```bash
   cd user-service && mvn clean install
   cd ../product-service && mvn clean install
   cd ../cart-service && mvn clean install
   cd ../order-service && mvn clean install
   cd ../api-gateway && mvn clean install
   cd ../config-server && mvn clean install
   ```

## 📁 Project Structure

```
grocery-platform/
│
├── api-gateway/               → Spring Cloud Gateway (Port 8080)
├── config-server/             → Spring Cloud Config Server (Port 8888)
├── user-service/              → Manages user profiles (Port 8081)
├── product-service/           → Manages product catalog (Port 8082)
├── cart-service/              → Manages shopping cart (Port 8083)
├── order-service/             → Manages order placement & status (Port 8084)
├── docker-compose.yml         → Docker orchestration
└── firebase-service-account.json → Firebase Admin SDK credentials
```

---

## 🔐 Authentication (Firebase)

- Uses Firebase Authentication (email/password).
- All services validate Firebase token independently (Option B).
- Use provided `login.html` to sign in and get ID Token.

---

## To check the authentication 

- docker logs user-service | Select-String "Firebase"

---

## ✅ Prerequisites

- Java 21
- Maven
- Docker & Docker Compose
- Firebase Admin SDK JSON (`firebase-service-account.json` in classpath)
- Firebase project with Email/Password authentication enabled

---

## ⚙️ Running the App (via Docker)

```bash
# From root folder
docker-compose up --build
```

This will start:
- API Gateway: http://localhost:8080
- User Service: http://localhost:8081
- Product Service: http://localhost:8082
- Cart Service: http://localhost:8083
- Order Service: http://localhost:8084
- Config Server (optional): http://localhost:8888

---

## 🧪 Testing the App

### 🔑 Get ID Token

1. Open `login.html` in your browser.
2. Sign in using your Firebase email/password.
3. Copy the **ID token** shown.

---

### 📲 Test APIs

**Using PowerShell or Terminal:**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/user/me" `
  -Method GET `
  -Headers @{ Authorization = "Bearer <PASTE_ID_TOKEN_HERE>" }
```

Replace `<PASTE_ID_TOKEN_HERE>` with your real Firebase token.

You can test other services similarly:
- **Product Service:** `GET /products/all` (public)
- **Cart Service:** `GET /cart/view` (auth required)
- **Order Service:** `GET /orders/user` (auth required)

---

## 🔄 Common Endpoints

### 🧑 User Service
- `GET /user/me` – Get user info (Requires token)
- `GET /user/test` – Public test

### 📦 Product Service
- `GET /products/all` – Public products
- `POST /products` – Add product (Auth required)

### 🛒 Cart Service
- `POST /cart/add` – Add item to cart
- `GET /cart/view` – View cart
- `PUT /cart/update` – Update item
- `DELETE /cart/remove/{productId}` – Remove item
- `DELETE /cart/clear` – Clear cart

### 📬 Order Service
- `POST /orders/place` – Place order
- `GET /orders/user` – View user orders
- `GET /orders/all` – View all orders (admin)
- `PUT /orders/status/{orderId}` – Update order status

---

## 🧼 Cleanup

```bash
docker-compose down -v
```

