package io.github.chrisvdalen.cloudsync;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {
    @Test
    void createsWelcomeMessage() {
        assertEquals("Connection established", App.SyncEndpoint.welcomeMessage());
    }

    @Test
    void createsEchoMessage() {
        assertEquals("Echo: hello", App.SyncEndpoint.echoMessage("hello"));
    }
}
