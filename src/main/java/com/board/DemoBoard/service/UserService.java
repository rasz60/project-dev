package com.board.DemoBoard.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    public boolean usernameDuplicateChk(String username);
    public boolean emailDuplicateChk(String email);
}
