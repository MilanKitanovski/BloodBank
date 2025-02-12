package org.example.model;

import com.sun.istack.NotNull;
import org.example.model.enums.Gender;
import org.example.model.enums.UserType;
import org.example.model.Centre;
import javax.persistence.ManyToOne;  //kada vise entiteta pripada jednom entitetu
import javax.persistence.Table;

//Na primer: više korisnika može pripadati jednoj kompaniji (više korisnika → jedna kompanija)
@javax.persistence.Entity //Bez @Entity, Hibernate neće prepoznati klasu kao tabelu!
@Table(name = "users")  // Ovo može pomoći ako `user` nije prepoznat zbog rezervisane reči
public class User extends Entity {
    @NotNull
    private  String name;

    @NotNull
    private String surname;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String address;

    @NotNull
    private String city;

    @NotNull
    private String country;

    @NotNull
    private String mobile;

    @NotNull
    private String uniquePersonalId;

    @NotNull
    private String profession;
    private String information;
    public String getProfession() { return profession; }

    private Gender gender;
    @ManyToOne
    private Centre centre;
    @NotNull
    private UserType userType;

    public User(String name, String surname, String email, String password, String address, String city,
                String country, String mobile, String uniquePersonalId, String profession,Gender gender,  UserType userType, String information) {
        this.name = name;
        this.surname = surname;
//      this.email = email;
//      this.password = password;
        this.address = address;
        this.city = city;
        this.country = country;
        this.mobile = mobile;
        this.uniquePersonalId = uniquePersonalId;
        this.profession = profession;
        this.gender = gender;
        this.userType = userType;
        this.information = information;
    }
    public User(){
        super(); //super(); poziva konstruktor roditeljske (super) klase. Koristi se da bi se inicijalizovale osobine nasledstva u Javi.
    }              // super(); je zapravo suvišan ovde, jer Java automatski poziva podrazumevani konstruktor roditeljske klase ako ga ne napišeš.
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getUniquePersonalId() {
        return uniquePersonalId;
    }
    public void setUniquePersonalId(String uniquePersonalId) {
        this.uniquePersonalId = uniquePersonalId;
    }
    public void setProfession(String profession) {
        this.profession = profession;
    }
    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public UserType getUserType() {
        return userType;
    }
    public void setUserType(UserType userType) {
        this.userType = userType;
    }
    public String getInformation() {
        return information;
    }
    public void setInformation(String information) {
        this.information = information;
    }
    public String getRole(){
        return userType.toString();
    }
    public Centre getCentre() { return centre; }public void setCentre(Centre centre) { this.centre = centre; }

}
