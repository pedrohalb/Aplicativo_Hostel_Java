package com.example.demo;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import com.example.demo.Reservation; // Importe a classe Reservation
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

import javafx.util.Callback;
import javafx.util.converter.LocalDateStringConverter;

public class MakeReservationController {

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private TableView<Reservation> reservationTableView;

	@FXML
	private TableColumn<Reservation, LocalDate> checkinDateColumn;

	@FXML
	private TableColumn<Reservation, LocalDate> checkoutDateColumn;

	private Stage primaryStage;

	@FXML
	private TableView<Room> roomTableView;

	@FXML
	private TableColumn<Room, String> numberColumn;

	@FXML
	private TableColumn<Room, String> nameColumn;

	@FXML
	private TableColumn<Room, Integer> floorColumn;

	@FXML
	private TableColumn<Room, String> descriptionColumn;

	@FXML
	private TableColumn<Room, Double> dimensionColumn;

	@FXML
	private TableColumn<Room, RoomType> roomTypeColumn;

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	private static List<Reservation> reservationList = new ArrayList<>();

	public static void addReservation(Reservation reservation) {
		reservationList.add(reservation);
	}

	public List<Reservation> getReservationList() {
		return reservationList;
	}

	private static List<Room> roomList = new ArrayList<>();

	public static void addRoom(Room room) {
		roomList.add(room);
	}

	@FXML
	private void handleAddRoomButton() {
		Dialog<Room> dialog = new Dialog<>();
		dialog.setTitle("Adicionar Quarto");
		dialog.setHeaderText("Adicione os detalhes do quarto");
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("room_dialog.fxml"));
		try {
			AnchorPane dialogContent = fxmlLoader.load();
			dialog.getDialogPane().setContent(dialogContent);
			RoomDialogController roomDialogController = fxmlLoader.getController();
			dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
			dialog.setResultConverter(new Callback<ButtonType, Room>() {
				@Override
				public Room call(ButtonType buttonType) {
					if (buttonType == ButtonType.OK) {
						return roomDialogController.getRoom();
					}
					return null;
				}
			});
			Optional<Room> result = dialog.showAndWait();
			roomTableView.getItems().addAll(roomList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void handleAddReservationButton() {
		Dialog<Reservation> dialog = new Dialog<>();
		dialog.setTitle("Adicionar Reserva");
		dialog.setHeaderText("Adicione os detalhes da reserva");
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("reservation_dialog.fxml"));
		try {
			AnchorPane dialogContent = fxmlLoader.load();
			dialog.getDialogPane().setContent(dialogContent);
			ReservationDialogController reservationDialogController = fxmlLoader.getController();
			dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
			dialog.setResultConverter(new Callback<ButtonType, Reservation>() {
				@Override
				public Reservation call(ButtonType buttonType) {
					if (buttonType == ButtonType.OK) {
						return reservationDialogController.getReservation();
					}
					return null;
				}
			});
			Optional<Reservation> result = dialog.showAndWait();
			reservationTableView.getItems().addAll(reservationList);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void initialize() {
		numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		floorColumn.setCellValueFactory(new PropertyValueFactory<>("floor"));
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
		dimensionColumn.setCellValueFactory(new PropertyValueFactory<>("dimension"));
		roomTypeColumn.setCellValueFactory(new PropertyValueFactory<>("roomType"));
		roomTableView.getItems().addAll(roomList);
		checkinDateColumn.setCellValueFactory(new PropertyValueFactory<>("checkinDate"));
		checkoutDateColumn.setCellValueFactory(new PropertyValueFactory<>("checkoutDate"));
		checkinDateColumn.setCellFactory(column -> new LocalDateCellFactory());
		checkoutDateColumn.setCellFactory(column -> new LocalDateCellFactory());
		reservationTableView.getItems().addAll(reservationList);
	}

	private void openMainMenu() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
			Parent root = loader.load();
			MenuController menuController = loader.getController();
			menuController.setPrimaryStage(primaryStage);
			List<Room> updatedRoomList = roomTableView.getItems();
			roomList.clear();
			roomList.addAll(updatedRoomList);
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
		Stage stage = (Stage) reservationTableView.getScene().getWindow();
		stage.close();
		openMainMenu();
		List<Reservation> updatedReservationList = getReservationList();
	}
}