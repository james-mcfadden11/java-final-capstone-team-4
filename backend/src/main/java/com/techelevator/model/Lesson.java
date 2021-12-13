package com.techelevator.model;

import java.util.Date;

public class Lesson {
    private int courseID;
    private int lessonID;
    private int lessonNumber;
    private String lessonName;
    private String description;
    private String youtubeURL;
    private String youtubeText;
    private String lessonURL1;
    private String lessonURL2;


    public Lesson(int lessonNumber, String lessonName, String description) {
        this.lessonNumber = lessonNumber;
        this.lessonName = lessonName;
        this.description = description;
    }

    public Lesson(int lessonID, int lessonNumber, String lessonName, String description) {
        this.lessonID = lessonID;
        this.lessonNumber = lessonNumber;
        this.lessonName = lessonName;
        this.description = description;
    }



    public Lesson() {
        super();
    }

    public Lesson(int lesson_id, int lesson_number, String lesson_name, String description, String youtube_url, String youtube_text, String lesson_url1, String lesson_url2) {
        this.lessonID = lesson_id;
        this.lessonNumber = lesson_number;
        this.lessonName = lesson_name;
        this.description = description;
        this.youtubeURL = youtube_url;
        this.youtubeText = youtube_text;
        this.lessonURL1 = lesson_url1;
        this.lessonURL2 = lesson_url2;
    }

    public int getLessonID() {
        return lessonID;
    }

    public void setLessonID(int lessonID) {
        this.lessonID = lessonID;
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

    public String getYoutubeURL() {
        return youtubeURL;
    }

    public void setYoutubeURL(String youtubeURL) {
        this.youtubeURL = youtubeURL;
    }

    public String getYoutubeText() {
        return youtubeText;
    }

    public void setYoutubeText(String youtubeText) {
        this.youtubeText = youtubeText;
    }

    public String getLessonURL1() {
        return lessonURL1;
    }

    public void setLessonURL1(String lessonURL1) {
        this.lessonURL1 = lessonURL1;
    }

    public String getLessonURL2() {
        return lessonURL2;
    }

    public void setLessonURL2(String lessonURL2) {
        this.lessonURL2 = lessonURL2;
    }
}
