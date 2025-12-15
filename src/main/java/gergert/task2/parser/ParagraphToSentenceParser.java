package gergert.task2.parser;

import gergert.task2.composite.TextComposite;
import gergert.task2.composite.TextType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParagraphToSentenceParser extends AbstractTextParser{
    private static final Logger logger = LogManager.getLogger();
    private static final String SENTENCE_REGEX_SPLIT = "(?<=[.!?])[\\s\\n]+";

    public ParagraphToSentenceParser() {}

    @Override
    public void parse(TextComposite composite, String text) {
        logger.trace("Parsing paragraph: [{}]", text);

        String[] sentences = text.trim().split(SENTENCE_REGEX_SPLIT);
        logger.debug("Paragraph split into {} sentences.", sentences.length);

        for (String sentence : sentences) {
            TextComposite sentenceComposite = new TextComposite(TextType.SENTENCE);
            composite.add(sentenceComposite);

            if (nextParser != null) {
                nextParser.parse(sentenceComposite, sentence.trim());
            }
        }

        logger.info("Paragraphs parsing completed.");
    }
}
