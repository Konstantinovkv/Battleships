package com.example.battleships_0.pojos;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private List<Question> questions;

    public Category() {
    }

    public Category(List<Question> questions) {
        this.questions = new ArrayList<>();
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
