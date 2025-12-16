package gergert.com.task2.service.impl;

import gergert.com.task2.composite.TextComponent;
import gergert.com.task2.composite.TextComposite;
import gergert.com.task2.composite.TextType;
import gergert.com.task2.service.TextService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class TextServiceImpl implements TextService {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public int findMaxSentencesWithSimilarWords(TextComposite composite) {
        logger.info("Searching for max sentences with the same word");

        List<TextComposite> sentences = findAllSentences(composite);
        Map<String, Integer> wordCounts = new HashMap<>();

        for (TextComposite sentence : sentences){
            Set<String> wordsInSentence = getWordsFromSentence(sentence);
            for (String word : wordsInSentence) {
                if (wordCounts.containsKey(word)){
                    wordCounts.compute(word, (k, count) -> count + 1);
                } else {
                    wordCounts.put(word, 1);
                }
            }
        }

        int maxCount = 0;
        for (int count : wordCounts.values()){
            if (count > maxCount){
                maxCount = count;
            }
        }

        return maxCount;
    }

    @Override
    public List<TextComposite> sortSentencesByLexemeCount(TextComposite composite) {
        logger.info("Sorting sentences by lexeme count");

        List<TextComposite> sentences = findAllSentences(composite);

        sentences.sort(Comparator.comparingInt(s -> s.getComponents().size()));

        return sentences;
    }

    @Override
    public void swapFirstAndLastLexemes(TextComposite composite) {
        logger.info("Swapping first and last lexemes");

        List<TextComposite> sentences = findAllSentences(composite);

        for (TextComposite sentence : sentences) {
            List<TextComponent> lexemes = sentence.getComponents();

            if (lexemes.size() > 1) {
                Collections.swap(lexemes, 0, lexemes.size() - 1);
            }
        }

        logger.info("Swap lexemes completed.");

    }

    private List<TextComposite> findAllSentences(TextComposite composite) {
        List<TextComposite> sentences = new ArrayList<>();

        if (composite.getType() == TextType.SENTENCE) {
            sentences.add(composite);
            return sentences;
        }

        for (TextComponent child : composite.getComponents()) {
            if (child instanceof TextComposite compositeChild) {
                sentences.addAll(findAllSentences(compositeChild));
            }
        }

        return sentences;
    }

    private Set<String> getWordsFromSentence(TextComposite sentence) {
        Set<String> words = new HashSet<>();

        for (TextComponent lexeme : sentence.getComponents()) {
            if (lexeme instanceof TextComposite lexemeComposite) {
                for (TextComponent child : lexemeComposite.getComponents()) {
                    if (child instanceof TextComposite wordComposite && wordComposite.getType() == TextType.WORD) {
                        words.add(wordComposite.toString().toLowerCase());
                    }
                }
            }
        }
        return words;
    }
}
