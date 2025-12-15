package gergert.task2.parser;

import gergert.com.task2.composite.TextComposite;
import gergert.com.task2.composite.TextType;
import gergert.com.task2.parser.WordToSymbolParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordToSymbolParserTest {

    @Test
    void parse() {
        WordToSymbolParser parser = new WordToSymbolParser();

        String text = "Java";
        TextComposite composite = new TextComposite(TextType.WORD);

        parser.parse(composite, text);

        assertEquals(4, composite.getComponents().size());

        assertEquals("J", composite.getComponents().get(0).toString());
        assertEquals("a", composite.getComponents().get(1).toString());
        assertEquals("v", composite.getComponents().get(2).toString());
        assertEquals("a", composite.getComponents().get(3).toString());
    }
}