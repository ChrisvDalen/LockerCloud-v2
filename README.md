# LockerCloud-v2

Java 25 WebSocket server with a small Vite browser client.

## Server

```bash
cd server
./mvnw verify
java -jar target/server-1.0-SNAPSHOT.jar
```

The endpoint starts at `ws://localhost:8080/sync`.

## Client

```bash
cd client
npm ci
npm test
npm run build
npm start
```

Node.js 22.12 or 24 and npm 11 are supported.
