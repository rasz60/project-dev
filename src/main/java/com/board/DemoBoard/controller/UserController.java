package com.board.DemoBoard.controller;

import com.board.DemoBoard.dto.UserForm;
import com.board.DemoBoard.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserServiceImpl userServiceImpl;




    @PostMapping("/signup/join")
    public String signUpUser(@RequestParam String userName, @RequestParam String password, @RequestParam String email) throws Exception {
        @Valid
        UserForm userForm = new UserForm(userName, password, email);
        userServiceImpl.signUpUser(userForm);
        return "redirect:/formLogin";
    }
}
