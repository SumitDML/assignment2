package com.assignment.assignment.repository;


import com.assignment.assignment.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager,Long> {

    Manager findByEmail(String email);
}
