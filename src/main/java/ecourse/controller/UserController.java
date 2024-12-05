package ecourse.controller;


import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ecourse.model.Role;
import ecourse.model.UserClass;
import ecourse.repository.UserRepository;
import ecourse.service.UserService;

@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @GetMapping("/admin/user")
    public String index(Model model) {
        model.addAttribute("list", userRepository.findAll());
        return "admin/user/index";
    }

    // Thêm dữ liệu
    @GetMapping("/admin/user/add")
    public String add() {
        return "admin/user/add";
    }

    @PostMapping("/admin/user/add")
    public String add(@ModelAttribute UserClass user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.updateImage(user);
        userRepository.save(user);
        return "redirect:/admin/user";
    }

    // Sửa dữ liệu
    @GetMapping("/admin/user/edit/{userId}")
    public String edit(@PathVariable("userId") short userId, Model model) {
        UserClass user = userRepository.findById(userId).orElse(null);
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "admin/user/edit";
        }

        @PostMapping("/admin/user/edit/{userId}")
        public String update(@PathVariable("userId") short userId, @ModelAttribute UserClass post) {
        post.setUserId(userId);
        if (post.getPassword() != null && !post.getPassword().isEmpty()) {
            post.setPassword(passwordEncoder.encode(post.getPassword()));
        }
        userService.updateImage(post);
        userRepository.save(post);
        return "redirect:/admin/user";
        }

    // Xóa
    @GetMapping("/admin/user/delete/{userId}")
    public String delete(@PathVariable("userId") short userId) {
        userRepository.deleteById(userId);
        return "redirect:/admin/user";
    }

    @PostMapping("/change-name")
    public String changeName(@RequestParam("fullname") String fullname) {
        // Lấy thông tin người dùng hiện tại
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // Lấy email người dùng hiện tại
        
        // Tìm người dùng trong cơ sở dữ liệu
        UserClass username = userService.findByUsername(email);
        if (username == null) {
            return "Người dùng không tồn tại!";
        }
        
        // Cập nhật tên người dùng và lưu lại
        username.setFullname(fullname);
        userService.save(username);
        
        return "Tên đã được cập nhật!";
    }
}
