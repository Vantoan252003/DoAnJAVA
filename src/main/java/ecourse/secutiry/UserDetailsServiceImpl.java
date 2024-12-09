package ecourse.secutiry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ecourse.model.UserClass;
import ecourse.repository.UserRepository;
import ecourse.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserClass user = userRepository.findByEmail(email);
        if (user != null) {
            return new CustomUserDetails(user);
        }
        throw new UsernameNotFoundException("User not found");
    }

    public String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    public String getCurrentUserImageUrl() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                // Truyền dữ liệu người dùng
                UserDetails userDetails = (UserDetails) principal;
                String username = userDetails.getUsername();
                // Giả sử bạn có phương thức lấy ảnh đại diện từ username
                return userService.getUserImageUrl(username); // Cập nhật URL ảnh đại diện từ DB
            }
        }
        return null;
    }
}
