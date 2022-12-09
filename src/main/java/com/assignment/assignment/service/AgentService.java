package com.assignment.assignment.service;


import com.assignment.assignment.model.request.CreateAgentRequest;
import com.assignment.assignment.model.response.UIBean;

public interface AgentService {

   UIBean createNewAgent(CreateAgentRequest createAgentRequest);
   UIBean findAgentById(Long id);
    UIBean getAllAgents(Integer pageSize ,Integer pageNumber);
}
