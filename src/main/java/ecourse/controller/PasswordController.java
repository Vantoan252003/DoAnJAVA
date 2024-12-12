package ecourse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PasswordController {
    @GetMapping("/home/changePassword")
    public String changePass() {
        return "/home/changePassword";
    }
}
