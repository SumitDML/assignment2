package com.assignment.assignment.controller;

import com.assignment.assignment.exception.ConstraintViolationException;
import com.assignment.assignment.model.request.CreateManagerRequest;
import com.assignment.assignment.model.response.ResponseModel;
import com.assignment.assignment.model.response.UIBean;
import com.assignment.assignment.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
public class ManagerController {

    @Autowired
    ManagerService managerService;

    @PostMapping("/manager/createManager")
    public ResponseEntity createNewManager(@Valid @RequestBody CreateManagerRequest createManagerRequest){
        try{
            UIBean returnValue = managerService.createNewManager(createManagerRequest);
            ResponseModel responseModel = new ResponseModel(HttpStatus.OK,"Manager Created Successfully!",null,returnValue);
            return new ResponseEntity(responseModel, HttpStatus.OK);
        }
        catch (ValidationException | ConstraintViolationException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }




}
