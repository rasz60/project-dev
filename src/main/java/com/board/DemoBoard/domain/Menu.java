package com.board.DemoBoard.domain;

import com.board.DemoBoard.dto.MenuForm;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "menu_id")
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 200)
    private String descriptions;

    @Column(length = 1000)
    private String url;

    @Column(columnDefinition = "varchar(1) default 'Y'")
    private String useYn;

    @Column(nullable = true)
    private int sortOrder;

    public Menu(){};

    public Menu(MenuForm menuForm) {
        this.title = menuForm.getTitle();
        this.descriptions = menuForm.getDescriptions();
    }

    public static Menu createMenu(MenuForm menuForm) {
        return new Menu(menuForm);
    }
}
