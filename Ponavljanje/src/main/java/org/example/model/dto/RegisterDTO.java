package org.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.enums.Gender;
import org.example.model.enums.UserType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String address;
    private String city;
    private String country;
    private String mobile;
    private String uniquePersonalId; //jmbg
    private String profession;
    private Gender gender;
    private int centreId;
    private String information;
}
