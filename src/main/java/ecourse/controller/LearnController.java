package ecourse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ecourse.model.Enrollments;
import ecourse.model.Order;
import ecourse.model.UserClass;
import ecourse.repository.EnrollmentsRepository;
import ecourse.repository.OrderRepository;
import ecourse.repository.UserRepository;
import ecourse.secutiry.CustomUserDetails;

@Controller
public class LearnController {

    @Autowired
    private UserRepository userRepository;
    @Autowired 
    private EnrollmentsRepository  enrollmentsRepository;
    @GetMapping("/home/learn")
    public String learn(Model model) {
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Short currentPrincipalName = userDetails.getUserId();
        UserClass user = userRepository.findByUserId(currentPrincipalName);
        List<Enrollments> enrolls = enrollmentsRepository.findEnrollmentsByClazz_UserId(user.getUserId());
        model.addAttribute("enrolls",enrolls);
        return "/home/learn";
    }
}
