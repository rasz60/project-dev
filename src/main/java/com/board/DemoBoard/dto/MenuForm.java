package com.board.DemoBoard.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class MenuForm {
    @NotEmpty(message = "메뉴명은 필수 입니다.")
    @Size(max = 50, message = "메뉴명은 50자리를 초과할 수 없습니다.")
    private String title;

    @Size(max = 200, message = "설명은 200자리를 초과할 수 없습니다.")
    private String descriptions;
}
