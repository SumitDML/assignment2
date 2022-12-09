package com.assignment.assignment.service.impl;


import com.assignment.assignment.entity.Agent;
import com.assignment.assignment.entity.Team;
import com.assignment.assignment.exception.InvalidArguementExceptions;
import com.assignment.assignment.exception.ItemNotFoundException;
import com.assignment.assignment.model.request.CreateTeamRequest;
import com.assignment.assignment.model.response.TeamResponse;
import com.assignment.assignment.model.response.UIBean;
import com.assignment.assignment.repository.AgentRepository;
import com.assignment.assignment.repository.TeamRepository;
import com.assignment.assignment.service.TeamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    TeamRepository teamRepository;

    @Autowired
    AgentRepository agentRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public UIBean createNewTeam(CreateTeamRequest createTeamRequest) {
        Team existingteam = teamRepository.findByName(createTeamRequest.getName());
        if(existingteam == null){
            Team team  = modelMapper.map(createTeamRequest,Team.class);

            Team savedTeam = teamRepository.save(team);
            TeamResponse teamResponse = modelMapper.map(savedTeam,TeamResponse.class);
            return new UIBean(teamResponse) ;
        }
      else {
         throw new InvalidArguementExceptions("Team Already Exists!");
       }
    }

    @Override
    public UIBean findTeamById(Long id) {
        Team existingTeam =teamRepository.findById(id).orElse(null);
        if(existingTeam != null){
            TeamResponse teamResponse = modelMapper.map(existingTeam, TeamResponse.class);
            return new UIBean(teamResponse);
        }
        else {
            throw new ItemNotFoundException("Sorry! Team Does not Exist!");
        }
    }

    @Override
    public UIBean getAllTeams(Integer pageSize, Integer pageNumber) {
        Pageable p = PageRequest.of(pageNumber,pageSize);

        Page<Team> pageItems= teamRepository.findAll(p);
        List<Team> allTeams = pageItems.getContent();
        if(allTeams != null){
            List<TeamResponse> teamsList = new ArrayList<>();
            allTeams.forEach(a-> {
                teamsList.add(modelMapper.map(a, TeamResponse.class));
            });
            return new UIBean(teamsList);
        }
        else {
            throw new ItemNotFoundException("Sorry! No Teams Found! ");
        }
    }

    @Override
    public UIBean assignAgent(Long teamId, Long agentId) {
        Agent existingAgent = agentRepository.findById(agentId).orElse(null);
        if(existingAgent != null){
            Team agentTeam = existingAgent.getAssignedTeams();
            if(agentTeam == null){
                Team existingTeam = teamRepository.findById(teamId).orElse(null);
                if(existingTeam != null){
                   existingAgent.setAssignedTeams(existingTeam);
                   agentRepository.save(existingAgent);
                }else {
                    throw new ItemNotFoundException("Sorry! Team Not Found!");
                }
            }
            else {
                throw new InvalidArguementExceptions("Agent is already assigned to team: "+agentTeam.getName());
            }
        }else {
            throw new ItemNotFoundException("Sorry! Agent Not Found!!");
        }
        return new UIBean("Agent Assigned Successfully!!");
    }

    @Override
    public UIBean findEmptyTeams() {
        List<Team> allTeams  = teamRepository.findAll();
        List<TeamResponse> filteredTeams = new ArrayList<>();

        allTeams.forEach(t -> {
            if(t.getAgents()==null && t.getManagers()==null){
                filteredTeams.add(modelMapper.map(t, TeamResponse.class));
            }
        });
        if (filteredTeams.isEmpty()){
            throw new ItemNotFoundException("No Empty Teams Found!");
        }
        return new UIBean(filteredTeams);
    }
}
