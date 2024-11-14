package com.example.thi_module3.Model;

import com.example.thi_module3.Emty.PaymentMethod;
import com.example.thi_module3.Emty.RentalRoom;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdminModel {
    private final List<RentalRoom> list;
    private static Connection connection;

    public AdminModel() {
        list = new ArrayList<>();
        connection = DataBase.getConnection();
    }
    public ResultSet getAllUser() throws SQLException {
        // viet cau lenh truy van
        String sql = "SELECT " +
                "    r.roomID, " +
                "    r.tenantName, " +
                "    r.phoneNumber, " +
                "    r.rentalStartDate, " +
                "    p.paymentMethodName, " +
                "    r.notes " +
                "FROM " +
                "    RentalRoom r " +
                "JOIN " +
                "    PaymentMethod p " +
                "ON " +
                "    r.paymentMethodID = p.paymentMethodID";
        //dua cau lenh truy van
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }
    public ResultSet getAllCategory() throws SQLException {
        String sql = "SELECT * FROM paymentmethod";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }
    public void deleteUser(int roomID) throws SQLException {
        String sql = "DELETE FROM rentalroom WHERE roomID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        // Thay thế câu lệnh để sử dụng setInt cho kiểu dữ liệu int
        statement.setInt(1, roomID);

        statement.execute();
    }
    public static void addUser(RentalRoom room) throws SQLException {
        //PreparedStatement: Được sử dụng để thực thi các câu lệnh SQL có tham số.
        String sql = "INSERT INTO rentalroom (tenantName, phoneNumber, rentalStartDate,  paymentMethodID, notes) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        //thay thế câu lệnh
        statement.setString(1, room.getTenantName());
        statement.setString(2, room.getPhoneNumber());
        statement.setDate(3, Date.valueOf(room.getRentalStartDate()));
        statement.setInt(4, room.getPaymentMethodID());
        statement.setString(5, room.getNotes());

        //thực thi câu lệnh
        statement.execute();
        System.out.println("oke");
    }
    public static ResultSet findUserByID(int roomID ) throws SQLException {
        String sql = "SELECT * FROM rentalroom WHERE roomID= ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,roomID );
        // Thực thi truy vấn và trả về kết quả
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }

    public void updateUser(RentalRoom room) throws SQLException {
        String sql = "UPDATE rentalroom SET tenantName=?, phoneNumber=?, rentalStartDate=?, paymentMethodID=?, notes=? WHERE roomID=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        //thay thế câu lệnh
        statement.setString(1, room.getTenantName());
        statement.setString(2, room.getPhoneNumber());
        statement.setDate(3, Date.valueOf(room.getRentalStartDate()));
        statement.setInt(4, room.getPaymentMethodID());
        statement.setString(5, room.getNotes());
        statement.setInt(6, room.getRoomID());
        //thực thi câu lệnh
        statement.execute();
        System.out.println("oke");
    }
    public List<RentalRoom> searchProducts(String keyword) throws SQLException {
        String sql = "SELECT * FROM rentalroom WHERE roomID LIKE ? OR tenantName LIKE ? OR phoneNumber LIKE ?";
        List<RentalRoom> rentalRooms = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // Set the search keyword for each of the three fields in the query
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            statement.setString(3, "%" + keyword + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    // Extract data from the result set
                    int roomID = resultSet.getInt("roomID"); // Use getInt for numeric fields like roomID
                    String tenantName = resultSet.getString("tenantName");
                    String phoneNumber = resultSet.getString("phoneNumber");
                    LocalDate rentalStartDate = resultSet.getDate("rentalStartDate").toLocalDate();
                    int paymentMethodID = resultSet.getInt("paymentMethodID");
                    String notes = resultSet.getString("notes");

                    // Create a RentalRoom object and add it to the list
                    RentalRoom room = new RentalRoom(roomID, tenantName, phoneNumber, rentalStartDate, paymentMethodID, notes);
                    rentalRooms.add(room);
                }
            }
        } catch (SQLException e) {
            // Log or rethrow the exception with more context
            System.err.println("Error executing search: " + e.getMessage());
            throw e; // Re-throw the exception after logging it
        }

        return rentalRooms;
    }

}
