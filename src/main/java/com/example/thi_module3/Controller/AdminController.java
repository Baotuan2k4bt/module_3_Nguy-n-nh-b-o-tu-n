package com.example.thi_module3.Controller;

import com.example.thi_module3.Service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AdminController",urlPatterns = "/tt/*")
public class AdminController extends BaseController {

    private AdminService adminService;

    @Override
    public void init() throws ServletException {
        adminService = new AdminService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String url = req.getPathInfo();//: Lấy thông tin phần đường dẫn sau tên servlet trong URL
        if ((url == null)) {
            url = "/";
        }
        try {
            switch (url) {
                case "/":
                    adminService.showPageUserList(req, resp);
                    break;
                case "/create":
                    adminService.showPageAddUser(req, resp);
                    break;
                case "/find":
                    adminService.Search(req, resp);
                    break;

                case "/delete":
                    adminService.deleteUser(req, resp);
                    break;
                case "/update":
                    adminService.showPageUpdateUser(req, resp);
                    break;
                default:
                    pageNotFound(req, resp);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException(e);

        }


}
    @Override
    // Servlet này chịu trách nhiệm xử lý các yêu cầu HTTP POST từ phía client.
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Handle POST requests here
        req.setCharacterEncoding("UTF-8");
        String url = req.getPathInfo();
        if ((url == null)) {
            url = "/";
        }
        try {
            switch (url){
                case "/create":
               adminService.createUser(req, resp);
                    break;
             case "/update":
                   adminService.updateUser(req, resp);
           break;
                default:
                    pageNotFound(req, resp);
                    break;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}
