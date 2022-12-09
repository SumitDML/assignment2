package com.assignment.assignment.service;


import com.assignment.assignment.model.request.CreateTeamRequest;
import com.assignment.assignment.model.response.UIBean;

public interface TeamService {
    UIBean createNewTeam(CreateTeamRequest createTeamRequest);
    UIBean findTeamById(Long id);
    UIBean getAllTeams(Integer pageSize ,Integer pageNumber);

    UIBean assignAgent(Long teamId,Long agentId);


    UIBean findEmptyTeams();
}
