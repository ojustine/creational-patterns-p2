package ru.iteco.patterns.builder;

public class Main {
    public static void main(String[] args) {

        Email email = new MailBuilder().subject("subj")
                .to("to1")
                .to("to1")
                .to("to2")
                .copyToAll("copy1", "copy2")
                .copyTo("copy3")
                .content(new Content("body", "sign"))
                .build();

        System.out.println(email);
    }
}
