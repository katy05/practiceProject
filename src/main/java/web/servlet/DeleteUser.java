package web.servlet;


import service.AdminService;
import service.AdminServiceImpl;
import service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/deleteUser.jhtml")
public class DeleteUser extends HttpServlet {
   private AdminService adminService = ServiceFactory.getInstance().getService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Integer userId = Integer.valueOf(req.getParameter("userId"));
        adminService.delete(userId);
        if (userId != session.getAttribute("idLoggedInUser")) {
            resp.sendRedirect(req.getContextPath() + "/userTable.jhtml");
        } else {
            resp.sendRedirect(req.getContextPath() + "/logout.jhtml");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/userTable.jhtml");
    }


}
