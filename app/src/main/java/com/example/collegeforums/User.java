package com.example.collegeforums;

public class User {

    public String firstName,lastName,email,contact,institute;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public User(String firstName, String lastName, String email, String contact, String institute) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

        this.contact = contact;
        this.institute = institute;
    }
    public User(){

    }




}
