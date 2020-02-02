package com.example.battleships_0;

public class Question {

    public static final String TABLE_NAME = "questions";

    public static final String COLUMN_QUESTION = "question";

    private String question;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " ("
                    + COLUMN_QUESTION + " TEXT"
                    + ");";

    public Question(){}
}
