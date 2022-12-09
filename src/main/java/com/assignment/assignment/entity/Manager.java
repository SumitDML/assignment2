package com.assignment.assignment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MANAGER")
public class Manager {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "manager_fname")
    private String firstName;
    @Column(name = "manager_lname")
    private String lastName;
    @Column(name = "manager_id")
    private String idNumber;
    @Column(name = "email",unique = true)
    private String email;
    @OneToOne(cascade = CascadeType.MERGE)
    private Agent agent;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name ="manager_teams",
            joinColumns = { @JoinColumn(name ="manager_id") },
            inverseJoinColumns = { @JoinColumn(name = "team_id") })
    private Set<Team> managedTeams;


}
