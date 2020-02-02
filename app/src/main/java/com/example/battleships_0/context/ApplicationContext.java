package com.example.battleships_0.context;

public class ApplicationContext {

    private String category;
    private String question;
    private static ApplicationContext context = new ApplicationContext();

    private ApplicationContext() {
        this.category = category;
        this.question = question;
    }

    public static ApplicationContext getContext(){
        return context;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
