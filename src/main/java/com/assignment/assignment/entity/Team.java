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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TEAM")
public class Team {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "team_name",unique = true)
    private String name;
    @ManyToMany(cascade = CascadeType.MERGE, mappedBy = "managedTeams")
    private Set<Manager> managers;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "")
    private Set<Agent> agents;


}
