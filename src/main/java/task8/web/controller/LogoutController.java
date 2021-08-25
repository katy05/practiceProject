package task8.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {
    @GetMapping("/logout.jhtml")
    public String getLogout(HttpSession session) {
        if(session != null) {
            session.invalidate();
        }
        return "redirect:/auth.jhtml";

    }
}
