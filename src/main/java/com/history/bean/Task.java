package com.history.bean;

import java.util.*;



/**
 * Created by знаток on 29.01.2017.
 */
public class Task {
    private String text;
    Set<Answer> answerVariants;
    public Task(String text, Set<Answer> answers){
        this.text = text;
        this.answerVariants = answers;
    }

    public String getText() {
        return text;
    }

    public Set<Answer> getAnswerVariants() {
        return answerVariants;
    }

    public boolean isWholeQuestionCorrect() {
        boolean result = true;
        for(Answer eachAnswer : answerVariants){
            if(!eachAnswer.isCorrectVariantChecked()){
                result = false;
                break;
            }
        }
        return result;
    }
}
