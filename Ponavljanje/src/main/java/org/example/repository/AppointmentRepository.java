package org.example.repository;

import org.example.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    @Query(value = "SELECT * FROM Appointment WHERE date_and_time BETWEEN ?1 AND ?2 AND reservate = false", nativeQuery = true)
    List<Appointment> findAllByDateAndTimeBetween(Date startTime, Date endTime, boolean b);

}
