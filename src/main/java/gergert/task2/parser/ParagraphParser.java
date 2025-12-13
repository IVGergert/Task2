package gergert.task2.parser;

import gergert.task2.composite.TextComposite;
import gergert.task2.composite.TextType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParagraphParser extends AbstractTextParser{
    private static final Logger logger = LogManager.getLogger();
    private static final String PARAGRAPH_REGEX_SPLIT = "\\n";

    @Override
    public void parse(TextComposite composite, String text) {
        if (text == null || text.isBlank()){
            logger.warn("Text is null or empty");
            return;
        }

        logger.info("Start parsing text into paragraphs");
        String[] paragraphs = text.split(PARAGRAPH_REGEX_SPLIT);
        logger.debug("Found {} paragraphs in text.", paragraphs.length);

        for (String paragraphStr : paragraphs) {
            if (!paragraphStr.trim().isEmpty()) {
                TextComposite paragraphComposite = new TextComposite(TextType.PARAGRAPH);
                composite.add(paragraphComposite);

                if (nextParser != null) {
                    nextParser.parse(paragraphComposite, paragraphStr);
                }
            } else {
                logger.trace("Skipped empty paragraph line.");
            }
        }

        logger.info("Paragraph parsing completed.");
    }
}
