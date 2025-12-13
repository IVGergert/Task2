package gergert.task2.parser;

import gergert.task2.composite.TextComposite;
import gergert.task2.composite.TextType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LexemeParser extends AbstractTextParser{
    private static final Logger logger = LogManager.getLogger();
    private static final String LEXEME_REGEX_SPLIT = "\\s+";

    @Override
    public void parse(TextComposite composite, String text) {
        logger.trace("Parsing sentence: [{}]", text);

        String[] lexemes = text.split(LEXEME_REGEX_SPLIT);

        logger.debug("Sentence split into {} lexemes.", lexemes.length);

        for (String lexeme : lexemes) {
            TextComposite lexemeComposite = new TextComposite(TextType.LEXEME);
            composite.add(lexemeComposite);

            if (nextParser != null) {
                nextParser.parse(lexemeComposite, lexeme);
            }
        }
    }
}
