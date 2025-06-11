package com.example.protocol;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Serializes ProtocolMessage instances to an OutputStream.
 */
public class ProtocolSerializer {
    public void serialize(ProtocolMessage msg, OutputStream out) throws IOException {
        out.write((msg.getStartLine() + "\r\n").getBytes(StandardCharsets.UTF_8));
        for (Map.Entry<String, String> e : msg.getHeaders().entrySet()) {
            String line = e.getKey() + ": " + e.getValue() + "\r\n";
            out.write(line.getBytes(StandardCharsets.UTF_8));
        }
        out.write("\r\n".getBytes(StandardCharsets.UTF_8));
        if (msg.getBody().isPresent()) {
            out.write(msg.getBody().get());
        }
    }
}
