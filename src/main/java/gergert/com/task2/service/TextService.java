package gergert.com.task2.service;

import gergert.com.task2.composite.TextComposite;

import java.util.List;

public interface TextService {
    int findMaxSentencesWithSimilarWords(TextComposite textComposite);
    List<TextComposite> sortSentencesByLexemeCount(TextComposite textComposite);
    void swapFirstAndLastLexemes(TextComposite textComposite);
}
