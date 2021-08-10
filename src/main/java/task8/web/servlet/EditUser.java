package task8.web.servlet;


import task8.domain.User;
import task8.service.AdminService;
import task8.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/editUser.jhtml")
public class EditUser extends HttpServlet {
    private AdminService adminService = ServiceFactory.getInstance().getService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");

        String regex = "^[0-9]*$";

        if (userId == null || userId.matches(regex)) {
            try {
                if (userId == null || adminService.read(Integer.parseInt(userId)) == null) {
                    userId = String.valueOf(adminService.readMaxId() + 1);
                    req.setAttribute("thisIsPageAdd", true);

                } else {
                    Object user = adminService.read(Integer.parseInt(userId));
                    req.setAttribute("user", user);
                    req.setAttribute("thisIsPageEdit", true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            req.setAttribute("userId", userId);
            req.getRequestDispatcher("/WEB-INF/jsp/editUser.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/userTable.jhtml");

        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("failedLogin", false);
        req.setAttribute("failedPassword", false);
        req.setAttribute("failedRole", false);
        req.setAttribute("failedEmail", false);
        req.setAttribute("failedDob", false);

        HttpSession session = req.getSession();
        Integer userId = Integer.parseInt(req.getParameter("idEditedUser"));

        req.setAttribute("userId", userId);
        try {
            if (userId == adminService.readMaxId() + 1) {
                req.setAttribute("thisIsPageAdd", true);

            } else {
                req.setAttribute("thisIsPageEdit", true);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        User user = null;
        try {
            user = adminService.read(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String login = req.getParameter("newLogin");
        String password = req.getParameter("newPassword");
        List<String> role = new ArrayList<>();
        if (req.getParameterValues("userRole") != null) {
            role = Arrays.asList(req.getParameterValues("userRole"));
        }
        String email = req.getParameter("newEmail");
        String dob = req.getParameter("newDob");

        password = password.trim();

        User newUser = new User(userId, login, password, role, dob, email);
        req.setAttribute("user", newUser);

        if (Validator.validEmail(email) && Validator.validLogin(login)
                && Validator.isFieldNotEmpty(password) && Validator.isFieldNotEmpty(dob)) {

            if (userId == session.getAttribute("idLoggedInUser")) {
                session.setAttribute("login", login);
                //session.setAttribute("role", role);
            }
            if (user != null) {
                User editUser = new User(userId, login, password, role, dob, email);
                try {
                    adminService.update(editUser);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                user.setLogin(login);
                user.setPassword(password);
                user.setRole(role);
                user.setEmail(email);
            } else {
                User addUser = new User(userId, login, password, role, dob, email);
                try {
                    adminService.create(addUser);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            resp.sendRedirect(req.getContextPath() + "/userTable.jhtml");
        } else {
            if (!Validator.validLogin(login)) {
                req.setAttribute("failedLogin", true);
            }
            if (!Validator.isFieldNotEmpty(password)) {
                req.setAttribute("failedPassword", true);
            }
            if (!Validator.validEmail(email)) {
                req.setAttribute("failedEmail", true);
            }
            if (!Validator.isFieldNotEmpty(dob)) {
                req.setAttribute("failedDob", true);
            }

            req.getRequestDispatcher("/WEB-INF/jsp/editUser.jsp?editFailed=true").
                    forward(req, resp);

        }

    }
}
