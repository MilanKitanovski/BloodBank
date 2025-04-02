package org.example.repository;

import org.example.model.Centre;
import org.example.model.Schedule;
import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    public Schedule findScheduleByAdmin(User admin);

    public List<Schedule> findScheduleByAdminId(int id);

    public List<Schedule> findScheduleByUserId(int id);

    public List<Schedule> findScheduleByCentreId(int id);
}
