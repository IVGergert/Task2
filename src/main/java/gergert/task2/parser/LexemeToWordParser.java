package gergert.task2.parser;

import gergert.task2.composite.CharacterLeaf;
import gergert.task2.composite.TextComposite;
import gergert.task2.composite.TextType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeToWordParser extends AbstractTextParser{
    private static final Logger logger = LogManager.getLogger();
    private static final String WORD_REGEX = "([a-zA-Zа-яА-ЯёЁ]+)|(.)";

    public LexemeToWordParser() {}

    @Override
    public void parse(TextComposite composite, String text) {
        logger.trace("Parsing lexeme: [{}]", text);

        Pattern pattern = Pattern.compile(WORD_REGEX);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()){
            String word = matcher.group(1);
            String punct = matcher.group(2);

            if (word != null) {
                logger.trace("Found Word: '{}'", word);

                TextComposite wordComposite = new TextComposite(TextType.WORD);
                composite.add(wordComposite);

                if (nextParser != null) {
                    nextParser.parse(wordComposite, word);
                }
            } else if (punct != null) {
                logger.trace("Found Punctuation: '{}'", punct.charAt(0));

                composite.add(new CharacterLeaf(punct.charAt(0)));
            }
        }

        logger.info("Lexemes parsing completed.");
    }
}
