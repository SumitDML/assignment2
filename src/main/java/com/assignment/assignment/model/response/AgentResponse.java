package com.assignment.assignment.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgentResponse {
    private String idNumber;
    private String firstName;

    private String lastName;
    private String email;
    private TeamResponse team;

}
