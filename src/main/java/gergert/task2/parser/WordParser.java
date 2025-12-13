package gergert.task2.parser;

import gergert.task2.composite.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WordParser extends AbstractTextParser{
    private static final Logger logger = LogManager.getLogger();
    private static final String WORD_REGEX_SPLIT = "";

    @Override
    public void parse(TextComposite composite, String text) {
        logger.trace("Parsing lexeme: [{}]", text);


    }
}
