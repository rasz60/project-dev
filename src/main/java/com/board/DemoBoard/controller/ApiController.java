package com.board.DemoBoard.controller;

import com.board.DemoBoard.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiController {

    private final HttpSession httpSession;

    @GetMapping("/api/v1/loginInfo")
    public SessionUser loginInfo() {
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");

        log.info("loginInfo ==========================:" + (sessionUser == null));
        return sessionUser;
    }
}
