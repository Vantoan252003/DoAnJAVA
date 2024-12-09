package ecourse.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import ecourse.model.UserClass;
import ecourse.repository.ContactRepository;
import ecourse.repository.UserRepository;




@Controller
public class AdminController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ContactRepository contactRepository;
    @ModelAttribute
    private void userDetails(Model m, Principal p) {
        String email = p.getName();
        UserClass user = userRepository.findByEmail(email);
        m.addAttribute("user", user);
    }

    @GetMapping("/admin")
    public String trueAdmin() {
        return "admin/index";
    }
    @GetMapping("/admin/messages")
    public String messages(Model model) {
        model.addAttribute("list", contactRepository.findAll(Sort.by(Sort.Direction.DESC, "submittedAt")));
        return "admin/messages";
    }
    @GetMapping("/admin/messages/delete/{id}")
    public String delete(@PathVariable ("id") Short id) {
        contactRepository.deleteById(id);
        return "redirect:/admin/messages";
    }
    
}