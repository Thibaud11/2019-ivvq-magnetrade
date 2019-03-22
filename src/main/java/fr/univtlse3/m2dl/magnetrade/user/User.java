package fr.univtlse3.m2dl.magnetrade.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class User {

    @Id
    @GeneratedValue
    Long id;

    private String firstName;
    private String lastName;
    private String emailName;
    private Date birthDate;
    private String nickName;
    private String password;
    private String phoneNumber;
    private String picture;

    public User(){

    }

    public User(String firstName, String lastName, String emailName, Date birthDate, String nickName, String password, String phoneNumber, String picture) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailName = emailName;
        this.birthDate = birthDate;
        this.nickName = nickName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.picture = picture;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailName() {
        return emailName;
    }

    public void setEmailName(String emailName) {
        this.emailName = emailName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}