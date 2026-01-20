package gergert.com.task2.service.impl;

import com.gergert.task2.composite.TextComposite;
import com.gergert.task2.composite.TextType;
import com.gergert.task2.parser.*;
import com.gergert.task2.service.impl.TextServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TextServiceImplTest {

    TextServiceImpl service = new TextServiceImpl();
    AbstractTextParser rootParser;

    @BeforeEach
    void setUp() {
        service = new TextServiceImpl();

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
    void findMaxSentencesWithSimilarWords() {
        String text = "Hello world. Java is cool. World is beautiful";
        TextComposite root = new TextComposite(TextType.TEXT);
        rootParser.parse(root, text);

        int maxCount = service.findMaxSentencesWithSimilarWords(root);

        assertEquals(2, maxCount);

    }

    @Test
    void sortSentencesByLexemeCount() {
        String text = "Hello world and people. I Ilya. Last.";

        TextComposite root = new TextComposite(TextType.TEXT);
        rootParser.parse(root, text);

        List<TextComposite> sortedSentences = service.sortSentencesByLexemeCount(root);

        assertEquals(3, sortedSentences.size());
        assertEquals(1, sortedSentences.get(0).getComponents().size());
        assertEquals(2, sortedSentences.get(1).getComponents().size());
        assertEquals(4, sortedSentences.get(2).getComponents().size());

    }

    @Test
    void swapFirstAndLastLexemes() {
        String text = "Hello world and peoples.";
        TextComposite root = new TextComposite(TextType.TEXT);
        rootParser.parse(root, text);

        service.swapFirstAndLastLexemes(root);

        String result = root.toString();

        assertTrue(result.contains("peoples. world and Hello"));
    }
}