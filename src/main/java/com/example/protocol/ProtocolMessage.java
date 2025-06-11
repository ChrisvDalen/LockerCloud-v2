package com.example.protocol;

import java.util.Map;
import java.util.Optional;

/**
 * Represents a parsed protocol message consisting of a start line,
 * headers, and an optional body.
 */
public class ProtocolMessage {
    private final String startLine;
    private final Map<String, String> headers;
    private final Optional<byte[]> body;

    public ProtocolMessage(String startLine, Map<String, String> headers, Optional<byte[]> body) {
        this.startLine = startLine;
        this.headers = headers;
        this.body = body != null ? body : Optional.empty();
    }

    public String getStartLine() {
        return startLine;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Optional<byte[]> getBody() {
        return body;
    }
}
