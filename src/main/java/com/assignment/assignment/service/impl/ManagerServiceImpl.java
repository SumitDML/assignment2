package com.assignment.assignment.service.impl;


import com.assignment.assignment.entity.Manager;
import com.assignment.assignment.model.request.CreateManagerRequest;
import com.assignment.assignment.model.response.UIBean;
import com.assignment.assignment.repository.ManagerRepository;
import com.assignment.assignment.service.ManagerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    ManagerRepository managerRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public UIBean createNewManager(CreateManagerRequest createManagerRequest) {

        Manager existingManager = managerRepository.findByEmail(createManagerRequest.getEmail());
        if(existingManager == null){
            String id = UUID.randomUUID().toString().replace("-","").substring(0,8);
            Manager manager = modelMapper.map(createManagerRequest, Manager.class);
            manager.setIdNumber(id);
            managerRepository.save(manager);
            return new UIBean("Manager Created Successfully!") ;
        }
        else {
            return new UIBean("Manager Already Exists!");
        }

    }

}
