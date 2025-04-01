# 🔐 Daily Password Guessing Game API

A fun and competitive project where each day, a new password is randomly generated. Users compete to guess the daily password first. The fastest correct guesser is added to a realtime leaderboard. Once guessed, the password becomes inactive until the next day.

---

## 🧠 How It Works

- Every day, a unique password is generated using the current date + 2 special characters.  
  Example (for March 31, 2025): `31032025@#`
- Users can submit guesses via the API.
- The **first correct guesser** is recorded on the **realtime leaderboard**.
- Once the password is guessed, further attempts are disabled for the day.
- A new password is generated automatically each new day (based on server time).

---

## 🛠️ Tech Stack

- **Java Spring Boot** – Backend API and core logic
- **WebSocket** – Realtime leaderboard updates
- **PostgreSQL** – Persistent storage for guesses and user rankings
- **Slf4j** – Logging system for tracking API activity and errors

---

## 📦 API Features

- `POST /guess` – Submit a password guess
- `GET /leaderboard` – View today's realtime leaderboard
- `GET /status` – Check if today’s password has been guessed

---

## 🚀 Getting Started

### Prerequisites

- Java 17+
- PostgreSQL
- Maven

### Installation

```bash
git clone https://github.com/yourusername/daily-password-game.git
cd daily-password-game
./mvnw spring-boot:run
``` 
### -> Created by the Guess It Team 
