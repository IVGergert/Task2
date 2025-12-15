package gergert.task2.parser;

import gergert.task2.composite.TextComposite;
import gergert.task2.composite.TextType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParagraphToSentenceParserTest {

    @Test
    void parse() {
        ParagraphToSentenceParser parser = new ParagraphToSentenceParser();
        parser.setNextParser(null);

        String text = "Hello world. How are you? I am fine!";
        TextComposite composite = new TextComposite(TextType.PARAGRAPH);

        parser.parse(composite, text);

        assertEquals(3, composite.getComponents().size());
        assertEquals(TextType.SENTENCE, ((TextComposite)composite.getComponents().get(0)).getType());
    }
}