package com.board.DemoBoard.controller;

import com.board.DemoBoard.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
public class WebController {



    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final HttpSession httpSession;
    @GetMapping("/")
    public String index(Model model) { return "vue/index"; }

    @GetMapping("/formLogin")
    public String login(HttpServletRequest request, Model model) {
        String error = request.getParameter("error");

        if (error != null) {
            model.addAttribute("loginError", "loginError");
        }
        return "vue/index";
    }

    @GetMapping("/signup")
    public String signin() { return "vue/index"; }

    @PostMapping("/joinProc")
    public String joinProc(User user) {
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        return null;
    }
}
