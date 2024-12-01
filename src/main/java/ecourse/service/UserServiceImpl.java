package ecourse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecourse.model.UserClass;
import ecourse.model.UserRepository;
@Service
public class UserServiceImpl implements UserInterface {
    @Autowired
    private UserRepository userRepository;
	@Override
	public UserClass createUser(UserClass user) {
        return userRepository.save(user);
		// Implementation here
	}
    @Override
    public boolean checkEmail(String email) {
        // TODO Auto-generated method stub
        return userRepository.existsByEmail(email);
    }
    
}
