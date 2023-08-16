package com.example.demo;

import java.time.LocalDate;
import java.util.*;

public class Guest {
    private String firstName;
    private String lastName;

    private com.example.demo.Address address;

    private String email;

    private Date birthDate;

    private List<Reservation> reservations = new ArrayList<>();

    private Title title;

    public Guest(Title title, String firstName, String lastName, String email, LocalDate birthDate) {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = Date.convertToLocalDate(birthDate);
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public Guest() {
       this("");
    }
    public Guest(String firstName){
       this(firstName, "");
   }

    public Guest(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

     public String getFirstName() {
        return firstName;
    }

     public void setFirstName(String name) {
        if (name.length() > 2) {
            this.firstName = name;
        }
     }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }
}
