package io.github.chrisvdalen.cloudsync;

import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import org.glassfish.tyrus.server.Server;

public final class App {
    static final String HOST = "localhost";
    static final int PORT = 8080;

    private App() {
    }

    @ServerEndpoint("/sync")
    public static final class SyncEndpoint {
        @OnOpen
        public void onOpen(Session session) throws java.io.IOException {
            session.getBasicRemote().sendText(welcomeMessage());
        }

        @OnMessage
        public void onMessage(String message, Session session) throws java.io.IOException {
            session.getBasicRemote().sendText(echoMessage(message));
        }

        static String welcomeMessage() {
            return "Connection established";
        }

        static String echoMessage(String message) {
            return "Echo: " + message;
        }
    }

    public static void main(String[] args) throws Exception {
        var server = new Server(HOST, PORT, "/", null, SyncEndpoint.class);
        try {
            server.start();
            System.out.printf("Server started at ws://%s:%d/sync. Press enter to stop.%n", HOST, PORT);
            System.in.read();
        } finally {
            server.stop();
        }
    }
}
