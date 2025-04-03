package org.example.service;

import org.example.model.Appointment;
import org.example.repository.AppointmentRepository;
import org.example.repository.CentreRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final CentreService centreService;
    public AppointmentService(AppointmentRepository appointmentRepository, CentreService centreService) {
        this.appointmentRepository = appointmentRepository;
        this.centreService = centreService;
    }

    public Appointment save(Appointment appointment){
        if(!isExist(appointment))
            return appointmentRepository.save(appointment);
        else
            return null;
    }

    private boolean isExist(Appointment appointment) {
        List<Appointment> allAppointments = appointmentRepository.findAll();


        for (Appointment a : allAppointments) {
            Date existingStart = a.getDateAndTime();
            Date existingEnd = new Date(a.getDateAndTime().getTime() + ((long) a.getDuration() * 60 * 1000));

            Date newStart = appointment.getDateAndTime();
            Date newEnd = new Date(appointment.getDateAndTime().getTime() + ((long) appointment.getDuration() * 60 * 1000));

            // Provera da li postoji preklapanje
            if(!(newEnd.before(existingStart) || newStart.after(existingEnd))){
                return true; // Preklapanje postoji
            }
        }
        return false; // Nema preklapanja
    }

    public List<Appointment> findAll(){ return appointmentRepository.findAll();}

    public List<Appointment> findAllByDateAndTimeBetween(Date startTime, Date endTime,boolean b){
        return appointmentRepository.findAllByDateAndTimeBetween(startTime, endTime, b);
    }

    public Appointment findById(int id){
        return appointmentRepository.findById(id).get();
    }

    public Appointment update(Appointment appointment){
        return appointmentRepository.saveAndFlush(appointment);
    }

}
