package com.board.DemoBoard.service;

import com.board.DemoBoard.domain.Menu;
import com.board.DemoBoard.dto.MenuForm;
import com.board.DemoBoard.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public List<Menu> getMenus() throws Exception {
        return menuRepository.findAll();
    }

    public void createMenu(MenuForm menuForm) throws Exception {
        menuRepository.save(Menu.createMenu(menuForm));
    }
}
