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
        user.setPassword("");
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

    // @PostMapping("/home/profile")
    // public String changeName(@RequestParam("fullname") String fullname) {
    // // Lấy thông tin người dùng hiện tại
    // Authentication authentication =
    // SecurityContextHolder.getContext().getAuthentication();
    // String email = authentication.getName(); // Lấy email người dùng hiện tại

    // // Tìm người dùng trong cơ sở dữ liệu
    // UserClass username = userService.findByUsername(email);
    // if (username == null) {
    // return "Người dùng không tồn tại!";
    // }

    // // Cập nhật tên người dùng và lưu lại
    // username.setFullname(fullname);
    // userService.save(username);
    // return "Tên đã được cập nhật!";
    // }

    // public String updateFullname(@RequestParam String fullname) {
    // String username = getCurrentUsername();
    // userService.updateFullname(username, fullname);
    // return "Fullname updated successfully!";
    // }

    // private String getCurrentUsername() {
    // Object principal =
    // SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    // if (principal instanceof UserDetails) {
    // return ((UserDetails) principal).getUsername();
    // } else {
    // return principal.toString();
    // }
    // }
    @Controller
    public class ProfileController {

        @Autowired
        private UserService userService;

        @PostMapping("/home/profile")
        public String changeName(@RequestParam("fullname") String fullname, Model model) {
            // Kiểm tra người dùng đã đăng nhập chưa
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {
                String email = authentication.getName(); // Lấy email của người dùng hiện tại

                // Cập nhật thông tin fullname trong cơ sở dữ liệu
                userService.updateFullname(email, fullname); // Cập nhật tên mới

                model.addAttribute("message", "Tên đã được thay đổi thành công!");
            } else {
                model.addAttribute("message", "Bạn cần đăng nhập để thay đổi tên!");
            }

            return "/home/profile"; // Chuyển hướng về trang profile sau khi cập nhật
        }
    }
}
