package gergert.com.task2.parser;

import com.gergert.task2.composite.TextComposite;
import com.gergert.task2.composite.TextType;
import com.gergert.task2.parser.TextToParagraphParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TextToParagraphParserTest {

    @Test
    void parse() {
        TextToParagraphParser parser = new TextToParagraphParser();
        parser.setNextParser(null);

        String text = "Para 1.\nPara 2.\nPara 3.";
        TextComposite composite = new TextComposite(TextType.TEXT);

        parser.parse(composite, text);

        assertEquals(3, composite.getComponents().size());
        assertEquals(TextType.PARAGRAPH, ((TextComposite)composite.getComponents().get(0)).getType());
    }
}