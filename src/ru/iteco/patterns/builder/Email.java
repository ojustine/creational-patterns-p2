package ru.iteco.patterns.builder;

import java.util.Set;

public class Email {

    private static final String FROM_DEFAULT = "%username%";
    private final String subject;
    private final String from;
    private final Set<String> to;
    private final Set<String> copy;
    private final Content content;

    protected Email(String subject, String from, Set<String> to, Set<String> copy, Content content) {
        this.subject = subject;
        this.from = from != null ? from : FROM_DEFAULT;
        this.to = to;
        this.copy = copy;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Email{" +
                "subject='" + subject +
                "', from='" + from +
                "', to=" + to +
                ", copyTo=" + copy +
                ", " + content +
                "}";
    }
}