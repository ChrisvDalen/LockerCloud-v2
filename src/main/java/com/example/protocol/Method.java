package com.example.protocol;

/**
 * Enumeration of supported methods.
 */
public enum Method {
    GET,
    POST_UPLOAD,
    POST_SYNC,
    POST_LISTFILES,
    DELETE;

    public static Method fromString(String s) {
        return switch (s.toUpperCase()) {
            case "GET" -> GET;
            case "POST_UPLOAD" -> POST_UPLOAD;
            case "POST_SYNC" -> POST_SYNC;
            case "POST_LISTFILES" -> POST_LISTFILES;
            case "DELETE" -> DELETE;
            default -> throw new IllegalArgumentException("Unknown method: " + s);
        };
    }
}
