package gergert.task2.parser;

import gergert.com.task2.composite.TextComposite;
import gergert.com.task2.composite.TextType;
import gergert.com.task2.parser.SentenceToLexemeParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SentenceToLexemeParserTest {

    @Test
    void parse() {
        SentenceToLexemeParser parser = new SentenceToLexemeParser();
        parser.setNextParser(null);

        String text = "Hello world, meet bread";
        TextComposite composite = new TextComposite(TextType.SENTENCE);

        parser.parse(composite, text);

        assertEquals(4, composite.getComponents().size());
    }
}