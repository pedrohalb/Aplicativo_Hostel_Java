package com.example.demo;

import java.time.LocalDate;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ReservationDialogController {

    @FXML
    private DatePicker checkinDatePicker;

    @FXML
    private DatePicker checkoutDatePicker;

    @FXML
    private Button cancelButton;

    @FXML
    private Button confirmButton;

    private Reservation reservation;

    public void initialize() {
    }

    @FXML
    private void handleOKButton() {
        LocalDate checkinDate = checkinDatePicker.getValue();
        LocalDate checkoutDate = checkoutDatePicker.getValue();
        if (checkinDate != null && checkoutDate != null) {
            reservation = new Reservation(checkinDate, checkoutDate);
            MakeReservationController.addReservation(reservation);
            closeDialog(confirmButton);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Datas não selecionadas");
            alert.setContentText("Por favor, selecione as datas de check-in e check-out.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleCancelButton() {
        reservation = null;
        closeDialog(cancelButton);
    }

    public Reservation getReservation() {
        return reservation;
    }

    private void closeDialog(Button button) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }}
