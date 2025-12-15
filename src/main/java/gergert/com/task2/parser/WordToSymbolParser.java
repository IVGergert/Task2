package gergert.com.task2.parser;

import gergert.com.task2.composite.CharacterLeaf;
import gergert.com.task2.composite.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WordToSymbolParser extends AbstractTextParser {
    private static final Logger logger = LogManager.getLogger();

    public WordToSymbolParser() {}

    @Override
    public void parse(TextComposite composite, String text) {
        logger.info("Start parsing words into symbols");

        char[] chars = text.toCharArray();

        for (char symbols : chars) {
            composite.add(new CharacterLeaf(symbols));
        }

        logger.info("Words parsing completed.");
    }
}
