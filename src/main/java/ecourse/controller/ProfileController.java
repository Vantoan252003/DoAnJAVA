package ecourse.controller;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ecourse.model.UserClass;
import ecourse.service.UserService;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService; // Service để quản lý người dùng

    @PostMapping("/home/avatar")
    public String updateAvatar(@RequestParam("imageFile") MultipartFile imageFile, Principal principal) {
    try {
        String username = principal.getName();
        UserClass user = userService.findByUsername(username);
        user.setImageFile(imageFile);
        userService.updateImage(user);
        return "redirect:/home/profile";
    } catch (Exception e) {
        e.printStackTrace();
        return "redirect:/home/profile";
    }
}
   
}
