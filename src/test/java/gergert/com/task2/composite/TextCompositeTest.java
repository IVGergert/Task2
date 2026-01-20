package gergert.com.task2.composite;

import com.gergert.task2.composite.TextComposite;
import com.gergert.task2.composite.TextType;
import com.gergert.task2.parser.*;
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
        String originalText = "Hello world. How are you?\nI am fine.";

        TextComposite textComposite = new TextComposite(TextType.TEXT);
        rootParser.parse(textComposite, originalText);

        String rebuildText = textComposite.toString();

        String expected = "\tHello world. How are you?\n\n\tI am fine.";

        assertEquals(expected, rebuildText);

        System.out.println(rebuildText);
    }

    @Test
    void count() {
        String originalText = "Hello world. \nJava is cool!";

        TextComposite textComposite = new TextComposite(TextType.TEXT);
        rootParser.parse(textComposite, originalText);

        int countSymbols = textComposite.count();

        assertEquals(22, countSymbols);
    }
}