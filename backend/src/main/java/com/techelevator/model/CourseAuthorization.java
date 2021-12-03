package com.techelevator.model;

import java.security.Principal;

public class CourseAuthorization {
    
    private final Principal principal;
    private final Course course;

    public CourseAuthorization(Principal principal, Course course) {
        this.principal = principal;
        this.course = course;
    }

    public boolean isAllowedToView() {
        return true;
    }


}
