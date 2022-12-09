package com.assignment.assignment.model.request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
public class CreateAgentRequest {
   @NotBlank(message = "First name cannot be null!")
    private String firstName;
    @NotBlank(message = "Last name cannot be null")
    private String lastName;
    @Email
    @NotNull(message = "Email name cannot be null")
    private String email;
    @NotBlank(message = "managerId cannot be null")
    private Long managerId;

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

 public Long getManagerId() {
  return managerId;
 }

 public void setManagerId(Long managerId) {
  this.managerId = managerId;
 }
}
