package task8.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import task8.service.AdminService;

import java.sql.SQLException;

@Controller
public class UserTableController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/userTable.jhtml")
    public String getUserTablePage(Model model) {
        try {
            model.addAttribute("users", adminService.findAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "/userTable";
    }

}
