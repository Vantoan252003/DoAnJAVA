package ecourse.secutiry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ecourse.model.UserClass;
import ecourse.model.UserRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
   
    @Autowired 
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserClass user = userRepository.findByEmail(email);
        if(user != null) {
            return new CustomUserDetails(user);
        }
        throw new UsernameNotFoundException("User not found");
    }

}
