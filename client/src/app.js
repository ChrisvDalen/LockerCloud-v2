const ws = new WebSocket("ws://localhost:8080/sync");
ws.addEventListener("open", () => console.log("WS open"));
ws.addEventListener("message", e => console.log("From server:", e.data));
