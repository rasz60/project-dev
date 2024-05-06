package com.board.DemoBoard.service;

import com.board.DemoBoard.dto.UserForm;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    public Long signUpUser(UserForm userForm) throws Exception;
    public boolean usernameDuplicateChk(String username);
    public boolean emailDuplicateChk(String email);
    public boolean signout(String username);
}
