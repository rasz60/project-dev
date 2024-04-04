package com.board.DemoBoard.repository;

import com.board.DemoBoard.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {

}
