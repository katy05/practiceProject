package task8.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import task8.service.AdminService;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;


@Controller
public class DeleteController {

    @Autowired
    private AdminService adminService;
    private UserTableController userTableController;

    @GetMapping("/deleteUser.jhtml")
    public String getDeleteUser(HttpSession session, @ModelAttribute("userId") Integer userId) {
        try {
            adminService.delete(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (session.getAttribute("idLoggedInUser") != userId) {
            return "redirect:/userTable.jhtml";
        } else {
            return "redirect:/logout.jhtml";
        }

    }
}
