package com.example.battleships_0.pojos;

import java.util.ArrayList;
import java.util.List;

public class Quiz {

    private List<Category> categories;

    public Quiz() {
    }

    public Quiz(List<Category> categories) {
        this.categories = new ArrayList<>();
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
