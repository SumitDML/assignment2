package com.assignment.assignment.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


public class CreateManagerRequest {
    @NotBlank(message = "First name cannot be null!")
    private String firstName;
    @NotBlank(message = "Last name cannot be null")
    private String lastName;
    @Email
    @NotBlank(message = "Email name cannot be null")
    private String email;

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

    public CreateManagerRequest(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
