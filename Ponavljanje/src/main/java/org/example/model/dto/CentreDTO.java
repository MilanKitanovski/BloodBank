package org.example.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CentreDTO {
    private String name;
    private String address;
    private double longitude;
    private double latitude;
    private String description;
    private String city;
    private Date startWork;
    private Date endWork;
}
