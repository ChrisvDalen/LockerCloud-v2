package com.example.protocol;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

class ProtocolParserTest {
    @Test
    void roundTripGet() throws Exception {
        String msgStr = "GET /download?file=a.txt\r\n" +
                "Content-Length: 0\r\n" +
                "\r\n";
        ProtocolParser parser = new ProtocolParser();
        ProtocolMessage msg = parser.parse(new ByteArrayInputStream(msgStr.getBytes(StandardCharsets.UTF_8)));
        assertEquals("GET /download?file=a.txt", msg.getStartLine());
        assertEquals("0", msg.getHeaders().get(HeaderNames.CONTENT_LENGTH));
        assertTrue(msg.getBody().isEmpty());

        ProtocolSerializer serializer = new ProtocolSerializer();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        serializer.serialize(msg, out);
        assertEquals(msgStr, out.toString(StandardCharsets.UTF_8));
    }

    @Test
    void parseInvalidHeader() {
        String input = "GET /\r\nInvalidHeader\r\n\r\n";
        ProtocolParser parser = new ProtocolParser();
        assertThrows(ProtocolParseException.class, () -> parser.parse(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8))));
    }
}
