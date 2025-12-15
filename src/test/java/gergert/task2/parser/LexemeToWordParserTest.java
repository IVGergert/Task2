package gergert.task2.parser;

import gergert.task2.composite.TextComposite;
import gergert.task2.composite.TextType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LexemeToWordParserTest {

    @Test
    void parse() {
        LexemeToWordParser parser = new LexemeToWordParser();
        parser.setNextParser(null);

        String text = "World,";
        TextComposite composite = new TextComposite(TextType.LEXEME);

        parser.parse(composite, text);

        assertEquals(2, composite.getComponents().size());

        TextComposite wordPart = (TextComposite) composite.getComponents().get(0);
        assertEquals(TextType.WORD, wordPart.getType());
    }
}