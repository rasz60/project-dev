package com.board.DemoBoard.service;

import com.board.DemoBoard.domain.User;
import com.board.DemoBoard.dto.MemberDetails;
import com.board.DemoBoard.dto.UserForm;
import com.board.DemoBoard.exception.DuplicateEmailException;
import com.board.DemoBoard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserDetailsService, UserService {

    /*
    * @RequiredArgsConstructor : 초기화되지 않았거나 @NotNull인 필드의 생성자 생성
    * @Transactional : 내부 로직 수행 중 오류 발생 시 원복
    */

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public Long signUpUser(UserForm userForm) throws Exception {
        validateDuplicateEmail(userForm.getEmail());

        log.info(
                "userForm.getEmail() : " + userForm.getEmail() + "\n" +
                "userForm.getUserName() : " + userForm.getUserName() + "\n" +
                "userForm.getPassWord() : " + userForm.getPassword() + "\n" +
                "userForm.getRole() : " + userForm.getRole()
        );
        return userRepository.save(User.builder()
                .email(userForm.getEmail())
                // 패스워드 암호화
                .password(bCryptPasswordEncoder.encode(userForm.getPassword()))
                .userName(userForm.getUserName())
                .role(userForm.getRole())
                .build()).getId();
    }

    //회원가입 중복 이메일 체크
    public void validateDuplicateEmail(String email) throws Exception {
        if ( userRepository.existsByEmail(email) ) {
            throw new DuplicateEmailException();
        }
    }

    // 사용자 username로 사용자 정보를 가져오는 메소드
    @Override
    public UserDetails loadUserByUsername(String userName) {
        User user;
        Optional<User> userOptional= userRepository.findByUserName(userName) ;
        log.info("userOptional.isPresent()");
        if ( userOptional.isPresent() ) {
            user = userOptional.get();
            log.info("userInfo : " + user.getUsername());
            return new MemberDetails(user);
        }
        return null;
    }

}
