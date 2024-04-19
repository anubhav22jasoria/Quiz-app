package com.anubhav.quizservice.model;


public class QuizDto {
    private String category;
    private int noOfQuestions;
    private String title;

    public int getNoOfQuestions() {
        return noOfQuestions;
    }

    public void setNoOfQuestions(int noOfQuestions) {
        this.noOfQuestions = noOfQuestions;
    }

    public QuizDto() {
    }

    public String getCategory() {
        return category;
    }

    public QuizDto(String category, int noOfQuestions, String title) {
        this.category = category;
        this.noOfQuestions = noOfQuestions;
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
