package com.assignment.assignment.service.impl;

import com.assignment.assignment.entity.Agent;
import com.assignment.assignment.entity.Manager;
import com.assignment.assignment.exception.InvalidArguementExceptions;
import com.assignment.assignment.exception.ItemNotFoundException;
import com.assignment.assignment.model.request.CreateAgentRequest;
import com.assignment.assignment.model.response.AgentResponse;
import com.assignment.assignment.model.response.UIBean;
import com.assignment.assignment.repository.AgentRepository;
import com.assignment.assignment.repository.ManagerRepository;
import com.assignment.assignment.service.AgentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AgentServiceImpl implements AgentService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AgentRepository agentRepository;

    @Autowired
    ManagerRepository managerRepository;


    @Override
    public UIBean createNewAgent(CreateAgentRequest createAgentRequest) {
            Agent existingAgent = agentRepository.findAgentByEmail(createAgentRequest.getEmail());
            if(existingAgent == null){
                String id = UUID.randomUUID().toString().replace("-","").substring(0,8);
                Agent agent = modelMapper.map(createAgentRequest, Agent.class);
                agent.setIdNumber(id);
                Manager existingManager = managerRepository.findById(createAgentRequest.getManagerId()).orElse(null);
                if(existingManager != null){
                    agent.setReportingManager(existingManager);
                    existingManager.setAgent(agent);
                }else {
                    throw new InvalidArguementExceptions("Invalid Manager ID");
                }
                Agent savedAgent = agentRepository.save(agent);

                AgentResponse agentResponse = modelMapper.map(savedAgent,AgentResponse.class);
                return new UIBean(agentResponse) ;
            }
            else {
                throw new InvalidArguementExceptions("Agent Already Exists!");
            }

    }

    @Override
    public UIBean findAgentById(Long id) {
        Agent existingAgent = agentRepository.findById(id).orElse(null);
        if(existingAgent != null){
            AgentResponse agentResponse = modelMapper.map(existingAgent, AgentResponse.class);
            return new UIBean(agentResponse);
        }
        else {
            throw new ItemNotFoundException("Sorry! Agent Does not Exist!");
        }
    }

    @Override
    public UIBean getAllAgents(Integer pageSize, Integer pageNumber) {
        Pageable p = PageRequest.of(pageNumber,pageSize);

        Page<Agent> pageItems= agentRepository.findAll(p);
        List<Agent>  allAgents = pageItems.getContent();
        if(allAgents != null){
            List<AgentResponse> agentsList = new ArrayList<>();
            allAgents.forEach(a-> {
                agentsList.add(modelMapper.map(a, AgentResponse.class));
            });
            return new UIBean(agentsList);
        }
        else {
            throw new ItemNotFoundException("Sorry! No Agent Found! ");
        }
    }
}
