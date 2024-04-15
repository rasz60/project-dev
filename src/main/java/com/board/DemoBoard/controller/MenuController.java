package com.board.DemoBoard.controller;

import com.board.DemoBoard.domain.Menu;
import com.board.DemoBoard.dto.MenuForm;
import com.board.DemoBoard.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MenuController {

    private final MenuService menuService;
    @GetMapping(value = "/api/v1/getMenus")
    public List<Menu> getMenus () throws Exception {
        log.info("[GET] Menu List");

        return menuService.getMenus();
    }

    @PostMapping(value = "/api/v1/createMenus")
    public void createMenu(@RequestBody @Valid MenuForm menuForm) throws Exception {
        menuService.createMenu(menuForm);
    }
}
