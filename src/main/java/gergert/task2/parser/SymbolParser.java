package gergert.task2.parser;

import gergert.task2.composite.CharacterLeaf;
import gergert.task2.composite.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SymbolParser extends AbstractTextParser{
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void parse(TextComposite composite, String text) {
        char[] chars = text.toCharArray();

        for (char c : chars) {
            composite.add(new CharacterLeaf(c));
        }
    }
}
