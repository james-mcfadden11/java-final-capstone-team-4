package com.techelevator.model;

import java.util.Date;

public class Lesson {
    private int courseID;
    private int lessonNumber;
    private String lessonName;
    private String description;

    public Lesson(int lessonNumber, String lessonName, String description) {
        this.lessonNumber = lessonNumber;
        this.lessonName = lessonName;
        this.description = description;
    }

    public Lesson() {
        super();
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(int lessonNumber) {
        this.lessonNumber = lessonNumber;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
