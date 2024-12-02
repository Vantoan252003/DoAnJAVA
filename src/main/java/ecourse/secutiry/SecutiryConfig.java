package ecourse.secutiry;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecutiryConfig {
    @Bean 
    public UserDetailsService getUserDetailsServices(){
        return new UserDetailsServiceImpl();
    }
    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider getDaoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(getUserDetailsServices());
        provider.setPasswordEncoder(getPasswordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
            .requestMatchers("/admin/**").hasAuthority("admin") // Chỉ người dùng có quyền "admin" mới được truy cập các URL bắt đầu bằng "/admin/"
            .requestMatchers("/user/**").hasAuthority("user") // Chỉ người dùng có quyền "user" mới được truy cập các URL bắt đầu bằng "/user/"
            .requestMatchers("/**").permitAll() // Cho phép tất cả mọi người truy cập các URL còn lại
        )
        .formLogin((form) -> form
            .loginPage("/home/signin") // Định nghĩa trang đăng nhập tùy chỉnh tại URL "/home/signin"
            .loginProcessingUrl("/login") // URL xử lý đăng nhập
            .successHandler((request, response, authentication) -> {
                if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("user"))) {
                    response.sendRedirect("/home/index");
                } else {
                    response.sendRedirect("/admin");
                }
            }) // URL chuyển hướng sau khi đăng nhập thành công
        )
        .csrf((csrf) -> csrf.disable()); // Vô hiệu hóa CSRF (Cross-Site Request Forgery)
        return http.build();
    }
}
