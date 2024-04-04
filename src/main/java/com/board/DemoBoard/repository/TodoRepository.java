package com.board.DemoBoard.repository;

import com.board.DemoBoard.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long>  {
}
