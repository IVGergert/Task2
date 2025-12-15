package gergert.task2.composite;

import gergert.task2.parser.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextCompositeTest {

    AbstractTextParser rootParser;

    @BeforeEach
    void setUp() {
        AbstractTextParser p1 = new TextToParagraphParser();
        AbstractTextParser p2 = new ParagraphToSentenceParser();
        AbstractTextParser p3 = new SentenceToLexemeParser();
        AbstractTextParser p4 = new LexemeToWordParser();
        AbstractTextParser p5 = new WordToSymbolParser();

        p1.setNextParser(p2);
        p2.setNextParser(p3);
        p3.setNextParser(p4);
        p4.setNextParser(p5);

        rootParser = p1;
    }

    @Test
    void testToString() {
        String originalText = "Hello world. \nJava is cool!";

        TextComposite root = new TextComposite(TextType.TEXT);
        rootParser.parse(root, originalText);

        String rebuildText = root.toString();

        assertTrue(rebuildText.contains("Hello"));
        assertTrue(rebuildText.contains("world"));
        assertTrue(rebuildText.contains("Java"));
        assertTrue(rebuildText.contains("."));
        assertTrue(rebuildText.contains("!"));

        assertFalse(rebuildText.isEmpty());
    }
}