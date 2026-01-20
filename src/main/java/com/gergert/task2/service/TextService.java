package com.gergert.task2.service;

import com.gergert.task2.composite.TextComposite;

import java.util.List;

public interface TextService {
    int findMaxSentencesWithSimilarWords(TextComposite textComposite);
    List<TextComposite> sortSentencesByLexemeCount(TextComposite textComposite);
    void swapFirstAndLastLexemes(TextComposite textComposite);
}
