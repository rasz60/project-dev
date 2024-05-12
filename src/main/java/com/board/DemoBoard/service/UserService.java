package com.board.DemoBoard.service;

import com.board.DemoBoard.domain.User;
import com.board.DemoBoard.dto.UserForm;

import java.security.Principal;
import java.util.Map;

public interface UserService {
    public User getUserByUsername(String userName);
    public Long signUpUser(UserForm userForm) throws Exception;
    public boolean usernameDuplicateChk(String username);
    public boolean emailDuplicateChk(String email);
    public boolean signout(String username);
    public boolean passwordChk(String username, String password);
    public boolean modifyUserInfo(Principal principal, Map<String, Object> info);
}
