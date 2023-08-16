package com.example.demo;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Auth.fxml"));
            Parent root = loader.load();
            AuthController authController = loader.getController();
            authController.setPrimaryStage(primaryStage);

            Scene scene = new Scene(root);
            primaryStage.setTitle("Sistema de Reservas de Hostel");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

