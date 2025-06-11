package com.example.cloudsync;

import org.glassfish.tyrus.server.Server;

import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

public class App {
    @ServerEndpoint("/sync")
    public static class SyncEndpoint {
        @OnOpen
        public void onOpen(Session session) throws Exception {
            session.getBasicRemote().sendText("Connection established");
        }

        @OnMessage
        public void onMessage(String message, Session session) throws Exception {
            session.getBasicRemote().sendText("Echo: " + message);
        }
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server("localhost", 8080, "/", null, SyncEndpoint.class);
        server.start();
        System.out.println("Server started. Press enter to stop.");
        System.in.read();
        server.stop();
    }
}
