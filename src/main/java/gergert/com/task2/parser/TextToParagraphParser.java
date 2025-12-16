package gergert.com.task2.parser;

import gergert.com.task2.composite.TextComposite;
import gergert.com.task2.composite.TextType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextToParagraphParser extends AbstractTextParser {
    private static final Logger logger = LogManager.getLogger();
    private static final String PARAGRAPH_REGEX_SPLIT = "[\\n]";

    public TextToParagraphParser() {}

    @Override
    public void parse(TextComposite composite, String text) {
        if (text == null || text.isBlank()){
            logger.warn("Text is null or empty - nothing to parse");
            return;
        }

        logger.info("Start parsing text into paragraphs");
        String[] paragraphs = text.split(PARAGRAPH_REGEX_SPLIT);
        logger.debug("Found {} paragraphs in text.", paragraphs.length);

        for (String paragraph : paragraphs) {
            if (!paragraph.isBlank()) {
                TextComposite paragraphComposite = new TextComposite(TextType.PARAGRAPH);
                composite.add(paragraphComposite);

                if (nextParser != null) {
                    nextParser.parse(paragraphComposite, paragraph.trim());
                }
            }
        }

        logger.info("Paragraph parsing completed.");
    }
}
