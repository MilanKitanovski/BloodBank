package org.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ScheduleDTO {
    private int adminid;

    private Date startWork;

    private Date endWork;

    private int userid;

    private int centreId;
}
