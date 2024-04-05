package com.board.DemoBoard.domain;

import com.board.DemoBoard.dto.UserForm;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;


@Table(name = "user")
@Builder
@Entity
@Getter
public class User implements UserDetails {

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

    public User(){};

    public User(UserForm userForm) {
        this.email = userForm.getEmail();
        this.userName = userForm.getUserName();
        this.password = userForm.getPassword();
    }
    
    public static User createUser(UserForm userForm) {
        return new User(userForm);
    }

    /* security methods */
    // 권한 조회
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }


    @Override
    public String getUsername() {
        return userName;
    }

    // 계정 만료 여부 조회
    @Override
    public boolean isAccountNonExpired() {
        return true; // true : 만료되지 않음, false : 만료
    }

    // 계정 잠금 여부 조회
    @Override
    public boolean isAccountNonLocked() {
        return true; // true : 잠금되지 않음, false : 잠금
    }

    // 패스워드 만료 여부 조회
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // true : 만료되지 않음, false : 만료
    }

    // 계정 사용 가능 여부 조회
    @Override
    public boolean isEnabled() {
        return false; // true : 사용 불가, false : 사용 가능
    }
}
