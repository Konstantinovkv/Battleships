package com.example.battleships_0.context;

import com.example.battleships_0.Cell;

public class ApplicationContext {

    private String category;
    private int question;
    private Cell[][] playerField;
    private Integer result;
    private boolean isWinner;
    private static ApplicationContext context = new ApplicationContext();

    private ApplicationContext() {
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

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public Cell[][] getPlayerField() {
        return playerField;
    }

    public void setPlayerField(Cell[][] playerField) {
        this.playerField = playerField;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }
}
