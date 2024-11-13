package com.example.thi_module3.Model;

import com.example.thi_module3.Emty.PaymentMethod;
import com.example.thi_module3.Emty.RentalRoom;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public void deleteUser(int roomID) throws SQLException {
        String sql = "DELETE FROM RentalRoom WHERE roomID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        // Thay thế câu lệnh để sử dụng setInt cho kiểu dữ liệu int
        statement.setInt(1, roomID);

        statement.execute();
    }
    public void addProduct(RentalRoom rentalRoom) throws SQLException {
        String sql = "INSERT INTO product (id,name,image, price,category_id) VALUES (?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        //thay thế câu lệnh
        statement.setInt(1, rentalRoom.getId());
        statement.setString(2, rentalRoom.getName());
        statement.setString(3, rentalRoom.getImage());
        statement.setBigDecimal(4, new BigDecimal(rentalRoom.getPrice()));
        statement.setInt(5, rentalRoom.getCategoryId());
        statement.execute();
    }
}
