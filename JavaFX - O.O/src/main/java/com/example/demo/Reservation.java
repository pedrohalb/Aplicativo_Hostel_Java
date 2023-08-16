package com.example.demo;

import java.time.LocalDate;

public class Reservation {
    private LocalDate reservationDate;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;

    public Reservation() {
    }

    public Reservation(LocalDate checkinDate, LocalDate checkoutDate) {
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public LocalDate getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(LocalDate checkingDate) {
        this.checkinDate = checkingDate;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationDate=" + reservationDate +
                ", checkinDate=" + checkinDate +
                ", checkoutDate=" + checkoutDate +
                '}';
    }
}
