package com.board.DemoBoard.controller;

import com.board.DemoBoard.dto.SessionUser;
import com.board.DemoBoard.service.UserService;
import com.board.DemoBoard.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiController {

    private final HttpSession httpSession;
    private final UserService userService;
    @GetMapping("/api/v1/loginInfo")
    public SessionUser loginInfo() {
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");

        log.info("loginInfo is null ? " + (sessionUser == null));
        return sessionUser;
    }

    @GetMapping("/api/v1/usernameDupChk/{username}")
    public boolean usernameDupChk(@PathVariable("username") String username) {
        boolean chk = userService.usernameDuplicateChk(username);
        log.info("usernameDupChk("+username+") ? " + chk);
        return chk;
    }
}
