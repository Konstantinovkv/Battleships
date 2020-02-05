package com.example.battleships;

public class ApplicationContext {

    private boolean bonus;

    private Integer playerShipsHit = 0;
    private Integer computerShipsHit = 0;

    private Cell[][] playerField;

    private Integer result = 0;
    private Integer numberOfBullets = 0;
    private Integer finalScore = 0;
    private Integer numberOfTurns = 1;

    private boolean isWinner;

    private Fields fields;

    private boolean gameHasFinished;
    private boolean gameHasStarted;

    private String category;
    private Integer question = 1;

    public void setQuestion(Integer question) {
        this.question = question;
    }

    private static ApplicationContext context = new ApplicationContext();

    private ApplicationContext() {
    }

    public boolean isBonus() {
        return bonus;
    }

    public void setBonus(boolean bonus) {
        this.bonus = bonus;
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

    public boolean isGameHasStarted() {
        return gameHasStarted;
    }

    public void setGameHasStarted(boolean gameHasStarted) {
        this.gameHasStarted = gameHasStarted;
    }

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }

    public boolean isGameHasFinished() {
        return gameHasFinished;
    }

    public void setGameHasFinished(boolean gameHasFinished) {
        this.gameHasFinished = gameHasFinished;
    }

    public Integer getPlayerShipsHit() {
        return playerShipsHit;
    }

    public void setPlayerShipsHit(Integer playerShipsHit) {
        this.playerShipsHit = playerShipsHit;
    }

    public Integer getComputerShipsHit() {
        return computerShipsHit;
    }

    public void setComputerShipsHit(Integer computerShipsHit) {
        this.computerShipsHit = computerShipsHit;
    }

    public Integer getNumberOfBullets() {
        return numberOfBullets;
    }

    public void setNumberOfBullets(Integer numberOfBullets) {
        this.numberOfBullets = numberOfBullets;
    }

    public Integer getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(Integer finalScore) {
        this.finalScore = finalScore;
    }

    public Integer getNumberOfTurns() {
        return numberOfTurns;
    }

    public void setNumberOfTurns(Integer numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    public Integer getQuestion() {
        return question;
    }
}
