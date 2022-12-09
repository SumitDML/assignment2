package com.assignment.assignment.service;

import com.assignment.assignment.model.request.CreateManagerRequest;
import com.assignment.assignment.model.response.UIBean;

public interface ManagerService {
    UIBean createNewManager(CreateManagerRequest createManagerRequest);



}
