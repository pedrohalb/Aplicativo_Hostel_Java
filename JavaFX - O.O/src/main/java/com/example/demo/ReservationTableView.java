package com.example.demo;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class ReservationTableView extends TableView<Reservation> {

    public ReservationTableView() {
        TableColumn<Reservation, LocalDate> checkinColumn = new TableColumn<>("Check-In");
        TableColumn<Reservation, LocalDate> checkoutColumn = new TableColumn<>("Check-Out");
        checkinColumn.setCellValueFactory(new PropertyValueFactory<>("checkinDate"));
        checkoutColumn.setCellValueFactory(new PropertyValueFactory<>("checkoutDate"));
        getColumns().addAll(checkinColumn, checkoutColumn);
    }
}

