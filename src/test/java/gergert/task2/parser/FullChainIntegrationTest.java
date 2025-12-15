package gergert.task2.parser;

import gergert.task2.composite.TextComposite;
import gergert.task2.composite.TextType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FullChainIntegrationTest {

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

        this.rootParser = p1;
    }


    @Test
    void testFullParsingProcess() {
        String text = "Hi, Java.";
        TextComposite root = new TextComposite(TextType.TEXT);
        rootParser.parse(root, text);

        assertEquals(1, root.getComponents().size());

        TextComposite paragraph = (TextComposite) root.getComponents().get(0);
        assertEquals(1,paragraph.getComponents().size());
        assertEquals(TextType.PARAGRAPH, paragraph.getType());

        TextComposite sentence = (TextComposite) paragraph.getComponents().get(0);
        assertEquals(2, sentence.getComponents().size());
        assertEquals(TextType.SENTENCE, sentence.getType());

        TextComposite lexeme1 = (TextComposite) sentence.getComponents().get(0);
        assertEquals(2, lexeme1.getComponents().size());

        TextComposite word = (TextComposite) lexeme1.getComponents().get(0);
        assertEquals(TextType.WORD, word.getType());
        assertEquals(2, word.getComponents().size());
        assertEquals("H", word.getComponents().get(0).toString());
        assertEquals("i", word.getComponents().get(1).toString());

        assertEquals(",", lexeme1.getComponents().get(1).toString());
    }
}
