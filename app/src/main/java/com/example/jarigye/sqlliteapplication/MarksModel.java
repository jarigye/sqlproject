package com.example.jarigye.sqlliteapplication;

/**
 * Created by jarigye on 11/17/2017.
 */

public class MarksModel {
    private String ID, mark, course,student,quiz;
    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public String getMark() {
        return mark;
    }
    public String getCourse() {
        return course;
    }
    public String getQuiz() {
        return quiz;
    }
    public String getStudent() {
        return student;
    }
    public void setMark(String mark) {
        this.mark = mark;
    }
    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }
    public void setCourse(String student) {
        this.student = student;
    }
}
