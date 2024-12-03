package ecourse.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import ecourse.model.UserClass;
import ecourse.repository.UserRepository;

@Controller
public class AdminController {
@Autowired UserRepository userRepository;
    @ModelAttribute
    private void userDetails(Model m, Principal p){
        String email = p.getName();
        UserClass user = userRepository.findByEmail(email);
        m.addAttribute("user", user);
    }
@GetMapping("/admin")
public String trueAdmin() {
return "admin/index";
}
}