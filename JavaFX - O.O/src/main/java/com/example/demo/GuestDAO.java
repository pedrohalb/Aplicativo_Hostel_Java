package com.example.demo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GuestDAO {

    public static void salvarGuest(Guest guest) {
        try (Connection conn = ConexaoSQLite.conectar()) {
            assert conn != null;
            try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO Guests (title, firstName, lastName, email, birthDate) VALUES (?, ?, ?, ?, ?)")) {
                stmt.setString(1, guest.getTitle().name());
                stmt.setString(2, guest.getFirstName());
                stmt.setString(3, guest.getLastName());
                stmt.setString(4, guest.getEmail());
                stmt.setString(5, guest.getBirthDate().toString());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Guest> carregarGuests() {
        List<Guest> guests = new ArrayList<>();
        try (Connection conn = ConexaoSQLite.conectar()) {
            assert conn != null;
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM Guests")) {
                while (rs.next()) {
                    String titleStr = rs.getString("title");
                    Title title = Title.valueOf(titleStr);
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    String email = rs.getString("email");
                    LocalDate birthDate = LocalDate.parse(rs.getString("birthDate"));
                    Guest guest = new Guest(title, firstName, lastName, email, birthDate);
                    guests.add(guest);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guests;
    }
}

