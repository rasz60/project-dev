package com.board.DemoBoard.service;

import com.board.DemoBoard.domain.User;
import com.board.DemoBoard.dto.UserForm;
import com.board.DemoBoard.exception.DuplicateEmailException;
import com.board.DemoBoard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    /*
    * @RequiredArgsConstructor : 초기화되지 않았거나 @NotNull인 필드의 생성자 생성
    * @Transactional : 내부 로직 수행 중 오류 발생 시 원복
    */

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public Long signUpUser(UserForm userForm) throws Exception {
        validateDuplicateEmail(userForm.getEmail());
        return userRepository.save(User.builder()
                .email(userForm.getEmail())
                // 패스워드 암호화
                .password(bCryptPasswordEncoder.encode(userForm.getPassword()))
                .build()).getId();
    }

    //회원가입 중복 이메일 체크
    public void validateDuplicateEmail(String email) throws Exception {
        if ( userRepository.existsByEmail(email) ) {
            throw new DuplicateEmailException();
        }
    }

    // 사용자 email로 사용자 정보를 가져오는 메소드
    @Override
    public User loadUserByUsername(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException((email)));
    }

}
