package com.example.battleships_0.pojos;

import java.util.List;

public class Question {

    private String question;
    private List<String> answers;
    private Integer correctAnswer;
    private String category;

    public Question() {
    }

    public Question(String question, List<String> answers, Integer correctAnswer, String category) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public Integer getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Integer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
