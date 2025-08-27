# Banking API (Spring Boot)

Secure, high-throughput API for accounts, balances, and transfers. Includes a tiny React client for interactive testing.

## Tech Stack
- Backend: Java 21, Spring Boot 3, Spring Security (JWT), Spring Data JPA (H2 for demo)
- Frontend: React + Vite

## Quick Start

### Backend
```bash
cd backend
./mvnw spring-boot:run
```

### Frontend
```bash
cd frontend
npm install
npm run dev
```

## Endpoints
- `POST /auth/register`
- `POST /auth/login`
- `GET /accounts/{id}`
- `POST /transfers`

All protected endpoints require `Authorization: Bearer <token>`.
