package com.romanceResearcher.service;

import com.romanceResearcher.domain.User;
import com.romanceResearcher.repository.UserRepository;
import com.romanceResearcher.view.UserView;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService() {
        userRepository = new UserRepository();
    }

    // 회원 가입
    public void addUser(User user) {
        user.setUserNo(userRepository.getUserNo());
        int result = userRepository.addUser(user);

        if (result == 1) {
            System.out.println(user.getId() + "님 회원 가입이 완료되었습니다.");
        } else {
            System.out.println("회원 가입에 실패하였습니다. 다시 시도해주세요.");
        }
    }

    // 회원 수정
    public void updateUser(User user) {
        int result = userRepository.updateUser(user);
        if (result == 1) {
            System.out.println(user.getId() + "님 회원 정보가 수정되었습니다.");
        } else {
            System.out.println(user.getId() + "님 회원 정보 수정에 실패하였습니다. 다시 시도해주세요.");
        }
    }

    // 회원 조회
    public User getUser(int no) {

        return userRepository.findByNo(no).get();
    }

    // 로그인
    public void loginUser(String id, String password) {
        List<User> userList = userRepository.findAll();
        Map<String, String> loginInfo = userRepository.findLoginInfo();
        if (userValidation(id, password, loginInfo)) {
            // 로그인 이후 화면으로 이동
            User findedUser = userRepository.findById(id).get();
            UserView userView = new UserView(findedUser, this);
            userView.firstHomePage();
        }

        // 로그인 실패 -> 처음 화면으로 이동
        System.out.println("로그인 실패하였습니다.");
    }

    // user 검증
    private static boolean userValidation(String id, String password, Map<String, String> loginInfo) {
        if (loginInfo.containsKey(id)) {
            if (loginInfo.get(id).equals(password)) {
                 return true;
            }
        }

        return false;
    }

    // 회원 탈퇴
    public void deleteUser(User user) {
        int result = userRepository.deleteUser(user);

        if (result == 1) {
            System.out.println("회원 탈퇴가 완료되었습니다.");
        } else {
            System.out.println("회원 탈퇴가 실패하였습니다.");
        }
    }

}
