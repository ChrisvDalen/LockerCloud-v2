package com.example.protocol;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Parses protocol messages from an InputStream.
 */
public class ProtocolParser {
    public ProtocolMessage parse(InputStream in) throws ProtocolParseException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
        String startLine = reader.readLine();
        if (startLine == null || startLine.isEmpty()) {
            throw new ProtocolParseException("Missing start line");
        }
        Map<String, String> headers = new HashMap<>();
        String line;
        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            int idx = line.indexOf(":");
            if (idx <= 0) {
                throw new ProtocolParseException("Malformed header: " + line);
            }
            String name = line.substring(0, idx).trim();
            String value = line.substring(idx + 1).trim();
            headers.put(name, value);
        }
        Optional<byte[]> body = Optional.empty();
        String cl = headers.get(HeaderNames.CONTENT_LENGTH);
        if (cl != null) {
            int length = Integer.parseInt(cl);
            ByteArrayOutputStream baos = new ByteArrayOutputStream(length);
            for (int i = 0; i < length; i++) {
                int b = in.read();
                if (b == -1) {
                    throw new ProtocolParseException("Unexpected end of stream");
                }
                baos.write(b);
            }
            body = Optional.of(baos.toByteArray());
        }
        return new ProtocolMessage(startLine, headers, body);
    }
}
