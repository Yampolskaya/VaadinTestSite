package com.history.bean;

/**
 * Created by знаток on 29.01.2017.
 */
public class Answer {
    private boolean correct;
    private String textAnswer;
    private boolean check;
    Answer(String answer){
        textAnswer = answer;
    }
    public Answer(String answer, boolean correct){
        textAnswer = answer;
        this.correct = correct;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getTextAnswer() {
        return textAnswer;
    }

    public boolean isCheck() {
        return check;
    }

    public boolean isCorrectVariantChecked() {
            boolean resultOfOneVariant = false;
            if (correct == check) {
                resultOfOneVariant = true;
            }
        return resultOfOneVariant;
    }
}
