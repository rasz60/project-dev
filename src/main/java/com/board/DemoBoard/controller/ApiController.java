package com.board.DemoBoard.controller;

import com.board.DemoBoard.dto.SessionUser;
import com.board.DemoBoard.service.UserService;
import com.board.DemoBoard.utils.EmailUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiController {

    private final HttpSession httpSession;
    private final UserService userService;
    private final EmailUtils emailUtils;
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

    @GetMapping("/api/v1/emailDupChk/{email}")
    public boolean emailDupChk(@PathVariable("email") String email) {
        boolean chk = userService.emailDuplicateChk(email);
        log.info("emailDupChk("+email+") ? " + chk);
        return chk;
    }

    @GetMapping("/api/v1/emailValid/{emailAddress}")
    public Map<String, Object> emailValid(@PathVariable("emailAddress") String emailAddress) {

        System.out.println(emailAddress);
        Map<String, Object> emailResult = emailUtils.sendEmail(emailAddress);

        return emailResult;
    }

    @DeleteMapping("/api/v1/signout/{username}")
    public Map<String, Object> signout(@PathVariable("username") String username) {
        Map<String, Object> signoutRst = new HashMap<>();
        boolean chk = userService.signout(username);
        signoutRst.put("resultCode", (chk ? 500 : 200));
        return signoutRst;
    }

    @GetMapping("/api/v1/passwordChk/{username}/{password}")
    public Map<String, Object> passwordChk(@PathVariable("username") String username,@PathVariable("password") String password) {
        Map<String, Object> passwordChkRst = new HashMap<>();
        boolean chk = userService.passwordChk(username, password);
        passwordChkRst.put("resultCode", (chk ? 200 : 500));
        System.out.println(chk);
        return passwordChkRst;
    }
}
