package org.example.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.example.model.User;
import org.example.model.enums.Gender;
import org.example.model.enums.UserType;
@Getter
@Setter
public class UserDTO {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String address;
    private String city;
    private String country;
    private String mobile;
    private String uniquePersonalId;
    private String profession;
    private Gender gender;
    private int centreId;
    private String information;

    public UserDTO(){

    }

    public UserDTO(int id, String name, String surname, String email, String password, String address, String city, String country, String mobile, String uniquePersonalId, String profession, Gender gender, int centreId, UserType userType, String information) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.city = city;
        this.country = country;
        this.mobile = mobile;
        this.uniquePersonalId = uniquePersonalId;
        this.profession = profession;
        this.gender = gender;
        this.centreId = centreId;
        this.information = information;
    }

    public UserDTO convert(User user){
        UserDTO userDto = new UserDTO();

        userDto.setAddress(user.getAddress());
        userDto.setCity(user.getCity());
        userDto.setEmail(user.getEmail());
        userDto.setCountry(user.getCountry());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setInformation(user.getInformation());
        userDto.setMobile(user.getMobile());
        userDto.setPassword(user.getPassword());
        userDto.setUniquePersonalId(user.getUniquePersonalId());
        userDto.setGender(user.getGender());
        userDto.setAddress(user.getAddress());

        return userDto;
    }
}
