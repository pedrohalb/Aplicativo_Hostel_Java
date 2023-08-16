package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class GuestService {
    private final ObservableList<Guest> guestsList = FXCollections.observableArrayList();

    public void addGuest(Guest guest) {
        guestsList.add(guest);
    }

    public ObservableList<Guest> getGuestsList() {
        return guestsList;
    }

    public void setGuestsList(List<Guest> guestList) {
        guestsList.setAll(guestList);
    }
}


