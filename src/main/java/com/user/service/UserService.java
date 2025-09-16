package com.user.service;

import com.user.model.User;

public interface UserService {
    
    boolean signup(User user);
        
    User login(String email, String password);
}
