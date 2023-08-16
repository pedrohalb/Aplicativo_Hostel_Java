package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AuthController {

    @FXML
    private AnchorPane anchorPane;

    private Stage primaryStage;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private MenuController MenuController;

    private static final String FIXED_USERNAME = "pedro";
    private static final String FIXED_PASSWORD = "henrique";

    private void openMenuScreen() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
            Parent root = fxmlLoader.load();
            MenuController = fxmlLoader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            MenuController.setPrimaryStage(stage);
            Stage currentStage = (Stage) usernameField.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void handleListGuestsButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("List.fxml"));
            Parent root = loader.load();
            ListGuestsController listGuestsController = loader.getController();
            listGuestsController.setPrimaryStage(primaryStage);
            Scene scene = new Scene(root);
            primaryStage.setTitle("Listar Hóspedes - Sistema de Reservas de Hostel");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLoginButton() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        boolean isAuthenticated = authenticate(username, password);
        if (isAuthenticated) {
            showSuccessAlert("Login bem-sucedido!");
            openMenuScreen();
        } else {
            showErrorAlert("Credenciais inválidas! Por favor tente novamente.");
        }
    }

    private boolean authenticate(String username, String password) {
        return username.equals(FIXED_USERNAME) && password.equals(FIXED_PASSWORD);
    }

    private void showSuccessAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Succeso");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showListGuestsWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("List.fxml"));
            Parent root = loader.load();
            ListGuestsController listGuestsController = loader.getController();
            listGuestsController.setPrimaryStage(primaryStage);
            Stage listGuestsStage = new Stage();
            listGuestsStage.setTitle("List Guests");
            listGuestsStage.setScene(new Scene(root));
            listGuestsStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void loginSuccessful() {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
        showListGuestsWindow();
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}

