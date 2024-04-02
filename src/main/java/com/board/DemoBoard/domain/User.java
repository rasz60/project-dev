package com.board.DemoBoard.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class User {
    /*
     *
     * @Entity : JPA의 entitiy로 등록이 됩니다.
     * @Id : 해당 칼럼 식별키(PK, Primary key) ★ 모든 엔티티에 반드시 @Id 지정해 주어야 합니다.
     * @GeneratedValue : 식별키를 어떤 전략으로 생성하는지 명시 ★ AUTO: 특정 데이터베이스에 맞게 자동으로 생성되는 방식입니다.
     * @Column : DB 컬럼으로 등록
     *
     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 50, nullable = false)
    private String userName;

    @Column(length = 50, nullable = false)
    private String password;

}
