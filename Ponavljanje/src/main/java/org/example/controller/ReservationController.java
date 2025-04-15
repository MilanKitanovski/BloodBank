package org.example.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.example.model.Appointment;
import org.example.model.Reservation;
import org.example.model.dto.ReservationRequestDTO;
import org.example.service.AppointmentService;
import org.example.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/reservation")
@SecurityRequirement(name = "BearerAuth") //za dodavanje dugmeta auth u swageru
@Tag(name = "Reservation Controller", description = "CRUD operacije za korisnike") //dodaje ime grupe i dodaje opis u swaggeru
public class ReservationController {
    private final ReservationService reservationService;
    private final AppointmentService appointmentService;

    public ReservationController(ReservationService reservationService, AppointmentService appointmentService) {
        this.reservationService = reservationService;
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> findAllReservation(@RequestParam int id){
        return new ResponseEntity<>(reservationService.findAllByUserId(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Boolean> isPastSixMonth(@PathVariable int id){
        return new ResponseEntity<>(reservationService.isPastSixMonth(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Reservation> saveAndSendEmail(@RequestBody ReservationRequestDTO reservationRequest){
        Appointment appointment = appointmentService.findById(reservationRequest.getAppointmentId());
        if(appointment.isReserved() == true)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        appointment.setReserved(true);
        appointmentService.update(appointment);
        return new ResponseEntity(reservationService.saveAndSendEmail(reservationRequest), HttpStatus.CREATED);
    }
}
