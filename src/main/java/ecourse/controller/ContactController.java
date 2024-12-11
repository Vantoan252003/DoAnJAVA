package ecourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ecourse.model.Contact;
import ecourse.repository.ContactRepository;
import jakarta.servlet.http.HttpSession;




@Controller
public class ContactController {
    @Autowired
    private ContactRepository contactRepository;
    @GetMapping("/home/contact")
    public String index (){
        return "home/contact";
    }
    @PostMapping("/home/contact")
    public String sendContact(@ModelAttribute Contact contact, HttpSession session){
       contactRepository.save(contact);
        return "redirect:/home/contact";
    }
    @GetMapping("/admin/delete/{id}")
    public String delete(@PathVariable ("id") Short id) {
        contactRepository.deleteById(id);
        return "redirect:/admin";
    }
    
    
}
    

