package org.example.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.model.Schedule;
import org.example.model.ScheduleConverter;
import org.example.model.dto.ScheduleDTO;
import org.example.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@SecurityRequirement(name = "BearerAuth") //za dodavanje dugmeta auth u swageru
@Tag(name = "Schedule Controller") //dodaje ime grupe i dodaje opis u swaggeru
@RestController
@RequestMapping("api/schedule")
@CrossOrigin(origins = "http://localhost:4200")
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<ScheduleDTO>> getAllSchedules(){
        List<Schedule> schedules = scheduleService.findAll();

        List<ScheduleDTO> dto = new ArrayList<>();
        for (Schedule schedule : schedules){
            dto.add(new ScheduleConverter().convertToDto(schedule));
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/admin/{id}")
    public ResponseEntity<List<ScheduleDTO>>  findScheduleByAdminId(@PathVariable int id){
        List<Schedule> schedules = scheduleService.findScheduleByAdminId(id);

        List<ScheduleDTO> dto = new ArrayList<>();
        for (Schedule schedule : schedules){
            dto.add(new ScheduleConverter().convertToDto(schedule));
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/patient/{id}")
    public ResponseEntity<List<ScheduleDTO>> findScheduleByUserId(@PathVariable int id){
        List<Schedule> schedules =scheduleService.findScheduleByUserId(id);

        List<ScheduleDTO> dto = new ArrayList<>();
        for (Schedule schedule : schedules){
            dto.add(new ScheduleConverter().convertToDto(schedule));
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/centre/{id}")
    public ResponseEntity<List<ScheduleDTO>> findScheduleByCentreId(@PathVariable int id){
        List<Schedule> schedules =scheduleService.findCentreSchedule(id);

        List<ScheduleDTO> dto = new ArrayList<>();
        for (Schedule schedule : schedules){
            dto.add(new ScheduleConverter().convertToDto(schedule));
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
