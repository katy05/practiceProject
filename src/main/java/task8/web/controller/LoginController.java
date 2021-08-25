package task8.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import task8.domain.User;
import task8.service.AdminService;
import task8.service.validators.LoginValidator;
import task8.web.forms.LoginForm;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@RequestMapping("/auth.jhtml")


@Controller

public class LoginController {
    @Autowired
    private AdminService adminService;
    @Autowired
    @Qualifier("loginValidator")
    private LoginValidator loginValidator;

    @GetMapping
    public String getLogin(HttpSession session, @RequestParam(value = "lang", required = false) String lang) {
        session.setAttribute("lang", lang);
        boolean userIsLogged = session != null && session.getAttribute("login") != null;
        if (!userIsLogged) {
            return "/login";
        } else {
            return "/welcome";
        }

    }


    @PostMapping
    public String postLogin(HttpSession session, Model model,
                            @ModelAttribute("incomingUser") LoginForm incomingUser, BindingResult bindingResult) {

        loginValidator.validate(incomingUser, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("login", incomingUser.getLogin());
            model.addAttribute("password", incomingUser.getPassword());
            return "login";
        }
        User user = null;
        try {
            user = adminService.checkLoginAndPassword(incomingUser.getLogin(), incomingUser.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (user != null) {
            session.setAttribute("login", incomingUser.getLogin());
            session.setAttribute("roles", user.getRoles());
            session.setAttribute("idLoggedInUser", user.getId());
            return "/welcome";
        } else {
//            model.addAttribute("authFailed", true);
            bindingResult.rejectValue("login", "login.wrong");
            bindingResult.rejectValue("password", "loginOrPassword.wrong");
            model.addAttribute("failedLogin", incomingUser.getLogin());


            return "/login";
        }
    }

    @ModelAttribute("incomingUser")
    public LoginForm getLoginForm() {
        return new LoginForm();
    }

    @ModelAttribute("allLang")
    public List<String> getAllLang(){
        List<String> allLang = new ArrayList<>();
        allLang.add("ru");
        allLang.add("en");
        return allLang;
    }
}
