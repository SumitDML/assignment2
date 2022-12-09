package com.assignment.assignment.controller;

import com.assignment.assignment.exception.ConstraintViolationException;
import com.assignment.assignment.model.request.CreateTeamRequest;
import com.assignment.assignment.model.response.ResponseModel;
import com.assignment.assignment.model.response.UIBean;
import com.assignment.assignment.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
public class TeamController {
    @Autowired
    TeamService teamService;
    @PostMapping("/team/createTeam")
    public ResponseEntity createNewAgent(@Valid @RequestBody CreateTeamRequest createTeamRequest){
        try{
            UIBean returnValue = teamService.createNewTeam(createTeamRequest);
            ResponseModel responseModel = new ResponseModel(HttpStatus.OK,"Team Created Successfully!",null,returnValue);
            return new ResponseEntity(responseModel,HttpStatus.OK);
        }
        catch (ValidationException | ConstraintViolationException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("team/{id}")
    public ResponseEntity findTeam(@PathVariable("id") Long id) {
        UIBean returnValue = teamService.findTeamById(id);
        return new ResponseEntity<>(returnValue,HttpStatus.OK);
    }

    @GetMapping("/team/findAll")
    public ResponseEntity findAllTeam( @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                         @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize)
    {
        UIBean returnValue = teamService.getAllTeams(pageSize,pageNumber);
        return new ResponseEntity(returnValue,HttpStatus.OK);
    }

    @PutMapping("/team/{id}/agent/{aid}")
    public ResponseEntity assignAgent(@PathVariable("id")Long teamId,@PathVariable("aid")Long agentId){
            UIBean returnValue = teamService.assignAgent(teamId,agentId);
            return new ResponseEntity(returnValue,HttpStatus.OK);
    }

    @GetMapping("/team/findEmptyTeams")
    public ResponseEntity findEmptyTeams()
    {
        UIBean returnValue = teamService.findEmptyTeams();
        return new ResponseEntity(returnValue,HttpStatus.OK);
    }


}
