package org.example.service;

import org.example.model.Schedule;
import org.example.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;

    public List<Schedule> findAll() {return scheduleRepository.findAll();}

    public List<Schedule> findScheduleByAdminId(int id){
        return  scheduleRepository.findScheduleByAdminId(id);
    }

    public List<Schedule> findScheduleByUserId(int id){
        return  scheduleRepository.findScheduleByUserId(id);
    }

    public List<Schedule> findCentreSchedule(int id){
        List<Schedule> centreSchedule = scheduleRepository.findScheduleByCentreId(id);

        return centreSchedule;
    }
}
