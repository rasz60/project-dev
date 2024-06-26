package com.board.DemoBoard.service;

import com.board.DemoBoard.domain.User;
import com.board.DemoBoard.dto.MemberDetails;
import com.board.DemoBoard.dto.SessionUser;
import com.board.DemoBoard.dto.UserForm;
import com.board.DemoBoard.exception.DuplicateEmailException;
import com.board.DemoBoard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Map;
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
    private final HttpSession httpSession;

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
        MemberDetails memberDetails = null;
        try {
            Optional<User> userOptional = userRepository.findByUserName(userName);
            log.info("userOptional.isPresent(" + userName + ") ? " + userOptional.isPresent());
            if (userOptional.isPresent()) {
                user = userOptional.get();
                System.out.println(user.
                        getEmail());
                memberDetails = new MemberDetails(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return memberDetails;
    }

    public User getUserByUsername(String userName) {
        Optional<User> userOptional = userRepository.findByUserName(userName);
        return userOptional.get();
    }

    public boolean usernameDuplicateChk(String username) {
        Optional<User> userOptional= userRepository.findByUserName(username);

        boolean chk = userOptional.isPresent();
        log.info("usernameDuplicateChk("+username+") : " + chk);
        return chk;
    }

    public boolean emailDuplicateChk(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean signout(String username) {
        Optional<User> userOptional = userRepository.findByUserName(username);
        boolean chk = userOptional.isPresent();
        if (chk) {
            User user = userOptional.get();
            log.info("===============signout(" + user.getUsername() + ")");
            userRepository.delete(user);
        }
        return this.chkUserExists(username);
    }

    public boolean chkUserExists(String username) {
        return userRepository.findByUserName(username).isPresent();
    }
    public boolean passwordChk(String username, String password) {
        boolean chk = false;
        Optional<User> userOptional = userRepository.findByUserName(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String pw = user.getPassword();
            System.out.println(

            );
            chk = bCryptPasswordEncoder.matches(password, pw);

            System.out.println(password + "==" + pw + "?"+chk);
        }
        return chk;
    }

    public boolean modifyUserInfo(Principal principal, Map<String, Object> info) {
        boolean chk = false;

        String newPassword = (String)info.get("newPassword");
        String newEmail = (String)info.get("newEmail");

        Optional<User> userOptional = userRepository.findByUserName(principal.getName());

        if ( userOptional.isPresent() ) {
            User user = userOptional.get();
            System.out.println("userinfo modify");
            if ( "".equals(newPassword) && "".equals(newEmail) ) {
                chk = true;
            }


            if (! "".equals(newPassword) ) {
                user.modifyPassword(bCryptPasswordEncoder.encode(newPassword));
                chk = true;
            }

            if (! "".equals(newEmail) ) {
                user.modifyEmail(newEmail);
                chk = true;
            }

            userRepository.save(user);
        }

        return chk;
    }
}
