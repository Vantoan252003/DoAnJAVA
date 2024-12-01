package ecourse.service;

import ecourse.model.UserClass;

public interface UserInterface {
    public UserClass createUser(UserClass user);
    public boolean checkEmail(String email);
    
}
