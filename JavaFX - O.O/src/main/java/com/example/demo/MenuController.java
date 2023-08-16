package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class MenuController {

    private Stage primaryStage;

    @FXML
    private Button listGuestsButton;

    @FXML
    private Button createReservationButton;

    @FXML
    private void handleMakeReservationButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("make_reservation.fxml"));
            Parent root = loader.load();
            MakeReservationController makeReservationController = loader.getController();
            makeReservationController.setPrimaryStage(primaryStage);
            Scene scene = new Scene(root);
            primaryStage.setTitle("Fazer Reserva - Sistema de Reservas de Hostel");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleListGuestsButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("List.fxml"));
            Parent root = loader.load();
            ListGuestsController listGuestController = loader.getController();
            listGuestController.setPrimaryStage(primaryStage);
            Scene scene = new Scene(root);
            primaryStage.setTitle("Lista de Hóspedes - Sistema de Reservas de Hostel");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
