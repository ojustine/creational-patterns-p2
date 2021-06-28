package ru.iteco.patterns.builder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MailBuilder {

    private String subject;
    private String from;
    private Set<String> to;
    private Set<String> copyTo;
    private Content content;

    public MailBuilder() {
        this.to = new HashSet<>();
        this.copyTo = new HashSet<>();
    }

    public FromToMailBuilder subject(String subject) {
        this.subject = subject;
        return new FromToMailBuilderImpl();
    }

    public interface FromMailBuilder {
        ToMailBuilder from(String from);
    }

    public interface ToMailBuilder {
        ToCopyMailBuilder to(String to);

        ToCopyMailBuilder toAll(String... to);
    }

    public interface CopyMailBuilder {
        CopyContentBuilder copyTo(String copyTo);

        CopyContentBuilder copyToAll(String... copyTo);
    }

    public interface ContentMailBuilder {
        ResultMailBuilder content(Content content);
    }

    public interface ResultMailBuilder {
        Email build();
    }

    public interface FromToMailBuilder extends FromMailBuilder, ToMailBuilder {
    }

    public interface ToCopyMailBuilder extends ToMailBuilder, CopyMailBuilder {
    }

    public interface CopyContentBuilder extends CopyMailBuilder, ContentMailBuilder {
    }

    private class FromToMailBuilderImpl implements FromToMailBuilder {
        @Override
        public ToMailBuilder from(String from) {
            MailBuilder.this.from = from;
            return this;
        }

        @Override
        public ToCopyMailBuilder to(String to) {
            return new ToCopyMailBuilderImpl().to(to);
        }

        @Override
        public ToCopyMailBuilder toAll(String... to) {
            return new ToCopyMailBuilderImpl().toAll(to);
        }
    }

    private class ToCopyMailBuilderImpl implements ToCopyMailBuilder {
        @Override
        public ToCopyMailBuilder to(String to) {
            MailBuilder.this.to.add(to);
            return this;
        }

        @Override
        public ToCopyMailBuilder toAll(String... to) {
            MailBuilder.this.to.addAll(Arrays.asList(to));
            return this;
        }

        @Override
        public CopyContentBuilder copyTo(String copyTo) {
            return new CopyContentMailBuilderImpl().copyTo(copyTo);
        }

        @Override
        public CopyContentBuilder copyToAll(String... copyTo) {
            return new CopyContentMailBuilderImpl().copyToAll(copyTo);
        }
    }

    private class CopyContentMailBuilderImpl implements CopyContentBuilder {

        @Override
        public CopyContentBuilder copyTo(String copyTo) {
            MailBuilder.this.copyTo.add(copyTo);
            return this;
        }

        @Override
        public CopyContentBuilder copyToAll(String... copyTo) {
            MailBuilder.this.copyTo.addAll(Arrays.asList(copyTo));
            return this;
        }

        @Override
        public ResultMailBuilder content(Content content) {
            MailBuilder.this.content = content;
            return new ResultMailBuilderImpl();
        }
    }

    private class ResultMailBuilderImpl implements ResultMailBuilder {
        @Override
        public Email build() {
            return new Email(MailBuilder.this.subject,
                    MailBuilder.this.from,
                    MailBuilder.this.to,
                    MailBuilder.this.copyTo,
                    MailBuilder.this.content);
        }
    }
}
