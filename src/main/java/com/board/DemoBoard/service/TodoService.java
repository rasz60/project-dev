package com.board.DemoBoard.service;

import com.board.DemoBoard.domain.Todo;
import com.board.DemoBoard.repository.impl.TodoRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {

    private final TodoRepositoryImpl TodoRepositoryImpl;

    // Todo 작성
    @Transactional
    public Long save(Todo todo){
        TodoRepositoryImpl.save(todo);

        return todo.getId();
    }

    // Todo 전체 조회
    public List<Todo> findTodos(boolean orderState){
        return TodoRepositoryImpl.findAll(orderState);
    }

    // Todo 단건 조회
    public Todo findOne(Long todoId){
        return TodoRepositoryImpl.findOne(todoId);
    }

    // Todo 완료 상태 수정
    @Transactional
    public void updateTodoComplted(Long id, boolean completed) {
        Todo todo = TodoRepositoryImpl.findOne(id);

        todo.setCompleted(completed);
    }

    // Todo 삭제(DB 업데이트)
    @Transactional
    public void updateTodoUseYn(Long id) {
        Todo todo = TodoRepositoryImpl.findOne(id);

        todo.setUseYn("N");
    }

    // Todo 전체 삭제(DB 업데이트)
    @Transactional
    public int updateTodoAllClear() {
        return TodoRepositoryImpl.updateTodoAllClear();
    }


}
