package com.board.DemoBoard.repository;

import com.board.DemoBoard.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    /*
    * JpaRepository를 상속받아 CRUD 메서드 사용
    * save(), find(), update(), delete()
    */

    // 자동으로 exist 쿼리를 한다..?!
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email); // email로 사용자 찾기

}
