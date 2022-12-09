package com.assignment.assignment.repository;


import com.assignment.assignment.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {

    Agent findAgentByEmail(String email);

}
