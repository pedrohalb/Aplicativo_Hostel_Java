package com.example.demo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {

    public static void salvarReservation(Reservation reservation) {
        try (Connection conn = ConexaoSQLite.conectar()) {
            assert conn != null;
            try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO Reservations (checkinDate, checkoutDate) VALUES (?, ?)")) {

                stmt.setString(1, reservation.getCheckinDate().toString());
                stmt.setString(2, reservation.getCheckoutDate().toString());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Reservation> carregarReservations() {
        List<Reservation> reservations = new ArrayList<>();
        try (Connection conn = ConexaoSQLite.conectar()) {
            assert conn != null;
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM Reservations")) {
                while (rs.next()) {
                    LocalDate checkinDate = LocalDate.parse(rs.getString("checkinDate"));
                    LocalDate checkoutDate = LocalDate.parse(rs.getString("checkoutDate"));
                    Reservation reservation = new Reservation(checkinDate, checkoutDate);
                    reservations.add(reservation);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    public static void saveAllReservations(List<Reservation> reservations) {
        try (Connection conn = ConexaoSQLite.conectar()) {
            assert conn != null;
            try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO Reservations (checkinDate, checkoutDate) VALUES (?, ?)")) {
                conn.setAutoCommit(false);
                for (Reservation reservation : reservations) {
                    stmt.setString(1, reservation.getCheckinDate().toString());
                    stmt.setString(2, reservation.getCheckoutDate().toString());
                    stmt.addBatch();
                }
                stmt.executeBatch();
                conn.commit();
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

