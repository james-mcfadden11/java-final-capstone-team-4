package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class Assignment {

    // Fields
    private int courseID;
    private int assignmentID;
    private int assignmentNumber;
    private String assignmentName;
    private String description;
    private int possiblePoints;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dueDate;

    // Constructors
    public Assignment() {
        super();
    }

    public Assignment(int courseID, int assignmentNumber, String assignmentName, String description, int possiblePoints, LocalDate dueDate) {
        this.courseID = courseID;
        this.assignmentNumber = assignmentNumber;
        this.assignmentName = assignmentName;
        this.description = description;
        this.possiblePoints = possiblePoints;
        this.dueDate = dueDate;
    }

    // Methods


    // Getters & Setters
    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(int assignmentId) {
        this.assignmentID = assignmentId;
    }

    public int getAssignmentNumber() {
        return assignmentNumber;
    }

    public void setAssignmentNumber(int assignmentNumber) {
        this.assignmentNumber = assignmentNumber;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPossiblePoints() {
        return possiblePoints;
    }

    public void setPossiblePoints(int possiblePoints) {
        this.possiblePoints = possiblePoints;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
