package com.example.thi_module3.Service;

import com.example.thi_module3.Emty.RentalRoom;
import com.example.thi_module3.Model.AdminModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminService {
    private final AdminModel adminModel;

    public AdminService() {
        this.adminModel = new AdminModel();

    }
   public void showPageUserList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        // Show list of users
     ResultSet data = adminModel.getAllUser();
      List<RentalRoom> rentalrooms= new ArrayList<>();
        while (data.next()) {
            int roomID=data.getInt("roomID");
            String tenantName=data.getString("tenantName");
            String phoneNumber=data.getString("phoneNumber");
            Date rentalStartDate=data.getDate("rentalStartDate");
            String   paymentMethodName=data.getString("paymentMethodName");
          String notes=data.getString("notes");
          RentalRoom rentalRoom= new RentalRoom(roomID,tenantName, phoneNumber, rentalStartDate, paymentMethodName, notes);
          rentalrooms.add(rentalRoom);

        }
       request.setAttribute("data",rentalrooms);//// Lưu danh sách người dùng vào request
        // Forward to list.jsp
       RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/list.jsp");
        requestDispatcher.forward(request, response);
  }
    public void deleteUser(HttpServletRequest request, HttpServletResponse respons) throws SQLException, IOException {
        int roomID = Integer.parseInt(request.getParameter("roomID"));
        adminModel.deleteUser(roomID);
        respons.sendRedirect(request.getContextPath() + "/tt");
    }
}
