package com.board.DemoBoard.controller;

import com.board.DemoBoard.domain.User;
import com.board.DemoBoard.dto.SessionUser;
import com.board.DemoBoard.service.UserService;
import com.board.DemoBoard.utils.EmailUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
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
    public User loginInfo(Principal principal) {
        User user = null;
        try {
            String username = principal.getName();
            if (!"".equals(username)) {
                user = userService.getUserByUsername(principal.getName());
            }
        } catch (NullPointerException e ) {
          log.info("principal is null.");
        }
        return user;
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

    @PostMapping("/api/v1/passwordChk")
    public Map<String, Object> passwordChk(@RequestBody Map<String, Object> param) {
        Map<String, Object> passwordChkRst = new HashMap<>();
        boolean chk = userService.passwordChk((String)param.get("username"), (String)param.get("password"));
        passwordChkRst.put("resultCode", (chk ? 200 : 500));
        System.out.println(chk);
        return passwordChkRst;
    }

    @PostMapping("/api/v1/modifyInfo")
    public Map<String, Object> modifyInfo(@RequestBody Map<String, Object> info, Principal principal) {
        Map<String, Object> modifyInfoRst = new HashMap<>();

        boolean chk = userService.modifyUserInfo(principal, info);
        modifyInfoRst.put("resultCode", (chk ? 200 : 500));

        return modifyInfoRst;
    }
}
