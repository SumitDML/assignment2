package com.assignment.assignment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "AGENT")
public class Agent {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "email",unique = true)
    private String email;
    @Column(name = "agent_fname")
    private String firstName;
    @Column(name = "agent_lname")
    private String lastName;
    @Column(name = "agent_id")
    private String idNumber;

    @OneToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_manager_id")
    private Manager reportingManager;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team assignedTeams;


}
