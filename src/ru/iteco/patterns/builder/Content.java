package ru.iteco.patterns.builder;

public class Content {

    private final String body;
    private final String signature;

    public Content(String body, String signature) {
        this.body = body;
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "Content{" +
                "body='" + body +
                "', signature='" + signature +
                "'}";
    }
}