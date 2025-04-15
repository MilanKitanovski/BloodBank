package org.example.service;

import net.bytebuddy.asm.Advice;
import org.example.model.Reservation;
import org.example.model.dto.ReservationRequestDTO;
import org.example.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserService userService;
    private final AppointmentService appointmentService;
    private final MailService mailService;

    public ReservationService(ReservationRepository reservationRepository, UserService userService, AppointmentService appointmentService, MailService mailService) {
        this.reservationRepository = reservationRepository;
        this.userService = userService;
        this.appointmentService = appointmentService;
        this.mailService = mailService;
    }

    public List<Reservation> findAllByUserId(int id){
        return reservationRepository.findAllByUser_Id(id);
    }

    public boolean isPastSixMonth(int id){
        ArrayList<Reservation> reservations = (ArrayList<Reservation>) findAllByUserId(id);
        if(reservations.size() == 0)
            return  true;
        Reservation reservation = reservations.get(reservations.size()-1);
        LocalDate dateToValidate = LocalDate.now().minusMonths(6);

        LocalDate lastDonationDate = LocalDate.ofInstant(reservation.getAppointment().getDateAndTime().toInstant(),
                ZoneId.of(ZoneId.systemDefault().toString()));
        if(lastDonationDate.isBefore(dateToValidate))
            return  true;
        else
            return false;

    }

    public Reservation saveAndSendEmail(ReservationRequestDTO reservationRequestDTO){
        Reservation r = new Reservation(userService.findUserByEmail(reservationRequestDTO.getUserEmail()),
                appointmentService.findById(reservationRequestDTO.getAppointmentId()));
        Reservation reservation = reservationRepository.save(r);
        if(reservation != null) {
            mailService.sendMail("milankitanovski00@gmail.com", "Uspesno sacuvan termin",
                    "Vas termin" + r.getAppointment().getDateAndTime() + "je uspesno sacuvan");
            return reservation;
        }else{
            mailService.sendMail("milankitanovski00@gmail.com", "Doslo je do greske prilikom zakazivanja",
                    "Vas termin "+ r.getAppointment().getDateAndTime()+" je neuspesno zakazan, probajte ponovo");
            return reservation;
        }
    }
}
