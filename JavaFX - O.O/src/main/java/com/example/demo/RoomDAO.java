package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

    public static void salvarRoom(Room room) {
        try (Connection conn = ConexaoSQLite.conectar()) {
            assert conn != null;
            try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO Rooms (number, name, floor, description, dimension, roomType) VALUES (?, ?, ?, ?, ?, ?)")) {
                stmt.setString(1, room.getNumber());
                stmt.setString(2, room.getName());
                stmt.setInt(3, room.getFloor());
                stmt.setString(4, room.getDescription());
                stmt.setDouble(5, room.getDimension());
                stmt.setString(6, room.getRoomType().name());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Room> carregarRooms() {
        List<Room> rooms = new ArrayList<>();
        try (Connection conn = ConexaoSQLite.conectar()) {
            assert conn != null;
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM Rooms")) {
                while (rs.next()) {
                    String number = rs.getString("number");
                    String name = rs.getString("name");
                    int floor = rs.getInt("floor");
                    String description = rs.getString("description");
                    double dimension = rs.getDouble("dimension");
                    String roomTypeStr = rs.getString("roomType");
                    RoomType roomType = RoomType.valueOf(roomTypeStr);

                    Room room = new Room(number, name, floor, description, dimension, roomType);
                    rooms.add(room);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public static void saveAllRooms(List<Room> rooms) {
        try (Connection conn = ConexaoSQLite.conectar()) {
            assert conn != null;
            try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO Rooms (number, name, floor, description, dimension, roomType) VALUES (?, ?, ?, ?, ?, ?)")) {
                conn.setAutoCommit(false);
                for (Room room : rooms) {
                    stmt.setString(1, room.getNumber());
                    stmt.setString(2, room.getName());
                    stmt.setInt(3, room.getFloor());
                    stmt.setString(4, room.getDescription());
                    stmt.setDouble(5, room.getDimension());
                    stmt.setString(6, room.getRoomType().name());
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

