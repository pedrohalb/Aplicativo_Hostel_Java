package com.example.demo;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RoomDialogController {

    @FXML
    private TextField numberField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField floorField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField dimensionField;

    @FXML
    private Button cancelButton;

   @FXML
    private Button confirmButton;

    @FXML
    private ComboBox<RoomType> roomTypeComboBox;

    private Room room;


    @FXML
    private void initialize() {
        roomTypeComboBox.setItems(FXCollections.observableArrayList(RoomType.values()));
    }

    @FXML
    private void handleOKButton() {
        String number = numberField.getText();
        String name = nameField.getText();
        String floorText = floorField.getText();
        String description = descriptionField.getText();
        String dimensionText = dimensionField.getText();
        RoomType roomType = roomTypeComboBox.getValue();
        if (number.isEmpty() || name.isEmpty() || floorText.isEmpty() || description.isEmpty() || dimensionText.isEmpty() || roomType == null) {
            showErrorAlert("Erro de entrada", "Todos os campos são obrigatórios.", "Preencha todos os campos antes de confirmar.");
            return;
        }
        try {
            int floor = Integer.parseInt(floorText);
            double dimension = Double.parseDouble(dimensionText);
            room = new Room(number, name, floor, description, dimension, roomType);
            MakeReservationController.addRoom(room);
            closeDialog(confirmButton);
        } catch (NumberFormatException e) {
            showErrorAlert("Erro de entrada", "Valores inválidos", "O andar deve ser um número inteiro e a dimensão deve ser um número válido.");
        }
    }

    @FXML
    private void handleCancelButton() {
        room = null;
        closeDialog(cancelButton);
    }

    public Room getRoom() {
        return room;
    }

    private void closeDialog(Button button) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    private void showErrorAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
