package com.example.demo;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ListGuestsController {

    private Stage menuStage;

    private Stage primaryStage;

    @FXML
    private TableView<com.example.demo.Guest> guestTableView;

    @FXML
    private TableColumn<Guest, Title> titleColumn;

    @FXML
    private TableColumn<Guest, String> firstNameColumn;

    @FXML
    private TableColumn<Guest, String> lastNameColumn;

    @FXML
    private TableColumn<Guest, String> emailColumn;

    @FXML
    private TableColumn<Guest, Date> birthDateColumn;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    @FXML
    private DatePicker birthDateField;

    @FXML
    private ComboBox<Title> titleComboBox;

    private MenuController menuController;

    private static final ObservableList<Guest> guestsList = FXCollections.observableArrayList();

    public void addGuest(Guest guest) {
    	guestsList.add(guest);
    }

    public List<Guest> getGuestList() {
        return guestsList;
    }

    @FXML
    private void initialize() {
            titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
            firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
            titleComboBox.getItems().addAll(Title.values());
            guestTableView.setItems(guestsList);
    }

    @FXML
    private void handleAddGuestButton() {
        Title title = titleComboBox.getValue();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        LocalDate birthDate = birthDateField.getValue();
        Guest newGuest = new Guest(title, firstName, lastName, email, birthDate);
        guestsList.add(newGuest);
        clearFields();
        showSuccessAlert();
    }


    private void showSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successo");
        alert.setHeaderText(null);
        alert.setContentText("Hospede adicionado com sucesso!");
        alert.showAndWait();
    }
    private void clearFields() {
        titleComboBox.getSelectionModel().clearSelection();
        firstNameField.clear();
        lastNameField.clear();
        emailField.clear();
        birthDateField.setValue(null);
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    private void openMainMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
            Parent root = loader.load();
            menuController = loader.getController();
            menuController.setPrimaryStage(primaryStage);
            Scene scene = new Scene(root);
            primaryStage.setTitle("Menu Principal");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBackToMenuButton() {
        Stage stage = (Stage) guestTableView.getScene().getWindow();
        stage.hide();
        openMainMenu();
    }
}