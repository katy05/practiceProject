package task8.web.servlet;


import org.springframework.beans.factory.annotation.Autowired;
import task8.domain.User;
import task8.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/auth.jhtml")
public class Login extends HttpServlet {

    @Autowired
    private AdminService adminService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        boolean userIsLogged = session != null && session.getAttribute("login") != null;
        if (!userIsLogged) {
            req.getRequestDispatcher("WEB-INF/jsp/login.jsp").
                    forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/welcome.jhtml");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = null;
        try {
            user = adminService.checkLoginAndPassword(login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (user != null) {
            session.setAttribute("login", login);
            session.setAttribute("roles", user.getRoles());
            //session.setAttribute("isRoot", user.isRoot());
            session.setAttribute("idLoggedInUser", user.getId());
            resp.sendRedirect(req.getContextPath() + "/welcome.jhtml");
        } else {
            session.setAttribute("failedLogin", req.getParameter("login"));
            resp.sendRedirect(req.getContextPath() + "/auth.jhtml?authFailed=true");
        }
    }

}
