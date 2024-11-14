package com.example.thi_module3.Service;

import com.example.thi_module3.Emty.PaymentMethod;
import com.example.thi_module3.Emty.RentalRoom;
import com.example.thi_module3.Model.AdminModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        //Show list of users
     ResultSet data = adminModel.getAllUser();
       ResultSet list = adminModel.getAllCategory();
    List<RentalRoom> rentalrooms= new ArrayList<>();
      List<PaymentMethod>  paymentmethods=new ArrayList<>();
        while (data.next()) {
            int roomID=data.getInt("roomID");
            String tenantName=data.getString("tenantName");
            String phoneNumber=data.getString("phoneNumber");
            LocalDate  rentalStartDate= data.getDate("rentalStartDate").toLocalDate();
            String paymentMethodName=data.getString("paymentMethodName");
          String notes=data.getString("notes");
          RentalRoom rentalRoom= new RentalRoom(roomID,tenantName, phoneNumber, rentalStartDate, paymentMethodName, notes);
          rentalrooms.add(rentalRoom);

        }
     while (list.next()) {
          int paymentMethodID=list.getInt("paymentMethodID");
          String paymentMethodName=list.getString("paymentMethodName");
          PaymentMethod paymentmethod= new PaymentMethod(paymentMethodID, paymentMethodName);
         paymentmethods.add(paymentmethod);

     }
       request.setAttribute("data",rentalrooms);
       request.setAttribute("list", paymentmethods);
       //// Lưu danh sách người dùng vào request
        // Forward to list.jsp
       RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/list.jsp");
       requestDispatcher.forward(request, response);
  }

    public void deleteUser(HttpServletRequest request, HttpServletResponse respons) throws SQLException, IOException {
        int roomID = Integer.parseInt(request.getParameter("roomID"));
        adminModel.deleteUser(roomID);
        respons.sendRedirect(request.getContextPath() + "/tt");
    }
    public void showPageAddUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/create.jsp");
        requestDispatcher.forward(request, response);
    }

    public void createUser(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        // Get user Data from request
        String tenantName = request.getParameter("tenantName");
        String phoneNumber = request.getParameter("phoneNumber");
        LocalDate rentalStartDate =LocalDate.parse(request.getParameter("rentalStartDate"));
        int paymentMethodID = Integer.parseInt(request.getParameter("paymentMethodID"));
        String notes = request.getParameter("notes");
        // Kiểm tra nếu chuỗi không rỗng

        RentalRoom rentalroom = new RentalRoom(tenantName,phoneNumber,rentalStartDate,paymentMethodID,notes);
        // Lưu User vào cơ sở dữ liệu thông qua model
        adminModel.addUser(rentalroom);
        // Chuyển hướng về trang danh sách người dùng
        response.sendRedirect(request.getContextPath() +"/tt");
    }

    public void showPageUpdateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        //get user update from database
        int roomID= Integer.parseInt(request.getParameter("roomID"));
        RentalRoom userUpdate =this.getUserById(roomID);
        request.setAttribute("room",userUpdate);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/update.jsp");
        requestDispatcher.forward(request, response);
    }
    public RentalRoom getUserById(int roomID) throws SQLException {
        ResultSet resultSet = AdminModel.findUserByID(roomID);
        RentalRoom room = null;
        while (resultSet.next()) {
            String tenantName=resultSet.getString("tenantName");
            String phoneNumber=resultSet.getString("phoneNumber");
            LocalDate  rentalStartDate= resultSet.getDate("rentalStartDate").toLocalDate();
            int paymentMethodID=resultSet.getInt("paymentMethodID");
            String notes=resultSet.getString("notes");
            room = new RentalRoom( roomID,tenantName, phoneNumber, rentalStartDate, paymentMethodID, notes   );
        }

        return room;
    }
    public void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        // Get user Data from request
        //Các tham số từ biểu mẫu (Form Parameters):
        int roomID = Integer.parseInt(request.getParameter("roomID"));
        String tenantName = request.getParameter("tenantName");
        String phoneNumber = request.getParameter("phoneNumber");
        LocalDate rentalStartDate = LocalDate.parse(request.getParameter("rentalStartDate"));
        int paymentMethodID = Integer.parseInt(request.getParameter("paymentMethodID"));
        String notes = request.getParameter("notes");

        // Tạo đối tượng Congviec
        RentalRoom room = new RentalRoom(roomID, tenantName, phoneNumber, rentalStartDate,paymentMethodID,notes);
        // Thêm người dùng vào cơ sở dữ liệu
        adminModel.updateUser(room);
        // Chuyển hướng về trang danh sách người dùng
        response.sendRedirect(request.getContextPath() + "/tt");

        System.out.println(room.toString());
    }
    public void Search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        // Lấy từ khóa tìm kiếm từ request
        String keyword = request.getParameter("keyword");

        // Lấy danh sách sản phẩm dựa trên từ khóa tìm kiếm
        List<RentalRoom> rentalRooms = adminModel.searchProducts(keyword);
        System.out.println(rentalRooms.size());
        // Đặt danh sách sản phẩm vào thuộc tính request
        request.setAttribute("data", rentalRooms);

        // Forward đến trang showList.jsp
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/list.jsp");
        requestDispatcher.forward(request, response);
    }

}
