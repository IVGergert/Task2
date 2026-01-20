package com.gergert.task2.parser;

import com.gergert.task2.composite.TextComposite;
import com.gergert.task2.composite.TextType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SentenceToLexemeParser extends AbstractTextParser {
    private static final Logger logger = LogManager.getLogger();
    private static final String LEXEME_REGEX_SPLIT = "\\s+";

    public SentenceToLexemeParser() {}

    @Override
    public void parse(TextComposite composite, String text) {
        logger.trace("Parsing sentence: [{}]", text);

        String[] lexemes = text.trim().split(LEXEME_REGEX_SPLIT);

        logger.debug("Sentence split into {} lexemes.", lexemes.length);

        for (String lexeme : lexemes) {
            TextComposite lexemeComposite = new TextComposite(TextType.LEXEME);
            composite.add(lexemeComposite);

            if (nextParser != null) {
                nextParser.parse(lexemeComposite, lexeme.strip());
            }
        }

        logger.info("Sentence parsing completed.");
    }
}
