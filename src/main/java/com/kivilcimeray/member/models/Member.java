package com.kivilcimeray.member.models;

import org.hibernate.validator.constraints.Email;
import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created by anıl on 02.11.2016.
 */

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Integer version;

    @Email(message = "Wrong email format")
    private String email;
    @Size(min = 3, max = 20, message = "First name should contain more than 2 and less then 20 letters.")
    private String name;
    @Size(min = 2, max = 20, message = "Last name should contain more than 1 and less then 20 letters.")
    private String surname;
    @Size(min = 10, max = 20, message = "Your phone should contain more than 10 and less then 20 numbers.")
    private String phoneNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
