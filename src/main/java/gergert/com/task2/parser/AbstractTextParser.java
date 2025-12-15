package gergert.com.task2.parser;

import gergert.com.task2.composite.TextComposite;

public abstract class AbstractTextParser {
    protected AbstractTextParser nextParser;

    public void setNextParser(AbstractTextParser nextParser) {
        this.nextParser = nextParser;
    }

    public abstract void parse(TextComposite composite, String text);
}
