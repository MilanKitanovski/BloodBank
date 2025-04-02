package org.example.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.SneakyThrows;
import org.example.model.Appointment;
import org.example.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SecurityRequirement(name = "BearerAuth") //za dodavanje dugmeta auth u swageru
@Tag(name = "Centre Controller") //dodaje ime grupe i dodaje opis u swaggeru
@RestController
@RequestMapping("api/centres")
@CrossOrigin(origins = "http://localhost:4200")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(final AppointmentService appointmentService){
        this.appointmentService = appointmentService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CenterAdministrator')")
    public ResponseEntity<Appointment> save(@RequestBody Appointment appointment){
        Appointment appointment1 = appointmentService.save(appointment);

        if(appointment1 != null){
            return new ResponseEntity<>(appointment1, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointment(){
        return new ResponseEntity<>(appointmentService.findAll(), HttpStatus.OK);
    }

    @SneakyThrows
    @GetMapping("/{startTimeD}/{endTimeD}/{reservate}")
    public ResponseEntity<List<Appointment>> findAllByDateAndTimeBetween(@PathVariable String startTimeD,
                                                                         @PathVariable String endTimeD,
                                                                         @PathVariable boolean reservate){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date startTime = format.parse(startTimeD);
        Date endTime = format.parse(endTimeD);
        return new ResponseEntity<>(appointmentService.findAllByDateAndTimeBetween(startTime, endTime, reservate), HttpStatus.OK);
    }
}
