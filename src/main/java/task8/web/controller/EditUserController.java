package task8.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import task8.domain.Role;
import task8.domain.User;
import task8.service.AdminService;
import task8.service.validators.EditUserValidator;
import task8.web.servlet.Validator;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/editUser.jhtml")

@Controller
public class EditUserController {
    @Autowired
    private AdminService adminService;
    private Validator validator;
    @Autowired
    @Qualifier("editUserValidator")
    private EditUserValidator editUserValidator;

    @GetMapping
    public String getEditUserPage(Model model, @RequestParam(value = "userId", required = false) String userId) {

        String regex = "^[0-9]*$";
        User user = new User();
        if (userId == null || userId.matches(regex)) {
            try {
                if (userId == null || userId.trim().isEmpty()) {
                    userId = String.valueOf(adminService.readMaxId() + 1);
                }
                model.addAttribute("idEditedUser", userId);
                model.addAttribute("userId", userId);
                if (adminService.read(Integer.parseInt(userId)) == null) {
                    model.addAttribute("user", user);
                    model.addAttribute("thisIsPageAdd", true);
                } else {
                    user = adminService.read(Integer.parseInt(userId));
                    model.addAttribute("user", user);
                    model.addAttribute("thisIsPageEdit", true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return "/editUser";
        } else {
            return "redirect:/userTable.jhtml";

        }
    }

    @PostMapping
    public String postEditUserPage(@ModelAttribute User user,
                                   Model model, HttpSession session,
                                   @RequestParam(required = false) Integer idEditedUser,
                                   BindingResult bindingResult) throws SQLException {

        Integer userId = user.getId();
        if (userId == null) {
            userId = adminService.readMaxId() + 1;
        }

        editUserValidator.validate(user, bindingResult);


        if (bindingResult.hasErrors()) {
            model.addAttribute("idEditedUser", idEditedUser);
            model.addAttribute("user", user);
            model.addAttribute("editFailed", true);
            if (!validator.validEmail(user.getEmail())) {
                bindingResult.rejectValue("email", "email.wrong");
            }
            if (adminService.read(userId) == null) {
                model.addAttribute("thisIsPageAdd", true);
            } else {
                model.addAttribute("thisIsPageEdit", true);
            }


            return "/editUser";
        }
        if (!validator.validEmail(user.getEmail())) {
            bindingResult.rejectValue("email", "email.wrong");
            if (adminService.read(userId) == null) {
                model.addAttribute("thisIsPageAdd", true);
            } else {
                model.addAttribute("thisIsPageEdit", true);
            }
            return "/editUser";
        }


        List<Role> userRoles = user.getRoles();

        String newPassword = user.getPassword().trim();
        String newLogin = user.getLogin();
        String newDob = user.getDob();
        String newEmail = user.getEmail();

        User newUser = new User(userId, newLogin, newPassword, userRoles, newDob, newEmail);
        model.addAttribute("user", newUser);


        if (userId == session.getAttribute("idLoggedInUser")) {
            session.setAttribute("login", newLogin);
        }
        if (userId != adminService.readMaxId() + 1) {
            try {
                adminService.update(newUser);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            user.setLogin(newLogin);
            user.setPassword(newPassword);
            user.setRoles(userRoles);
            user.setEmail(newEmail);
        } else {
            try {
                adminService.create(newUser);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/userTable.jhtml";
    }


    @ModelAttribute("allRoles")
    public List<Role> getAllRoles() {
        List<Role> allRoles = new ArrayList<>();
        allRoles.add(new Role(1, "root"));
        allRoles.add(new Role(2, "user"));
        allRoles.add(new Role(3, "manager"));
        allRoles.add(new Role(4, "developer"));
        allRoles.add(new Role(5, "seo"));
        return allRoles;
    }
}