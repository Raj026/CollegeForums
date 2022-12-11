package com.example.collegeforums;

public class Search_model {


    public String getfirstName() {
        return firstName;
    }

    public String getemail() {
        return email;
    }

    public Search_model(String firstName, String email) {
        this.firstName = firstName;
        this.email = email;
    }

    public Search_model(){}

    public String firstName,email;
}
