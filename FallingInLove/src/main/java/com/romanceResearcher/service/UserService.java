package com.romanceResearcher.service;

import com.romanceResearcher.domain.User;
import com.romanceResearcher.repository.UserRepository;
import com.romanceResearcher.view.UserView;

import java.util.*;

public class UserService {

    private final UserRepository userRepository;
    private static UserService instance;


    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    private UserService() {
        userRepository = UserRepository.getInstance();
    }

    // 회원 가입
    public void addUser(User user) {
        user.setUserNo(userRepository.getUserNo());
        int result = userRepository.addUser(user);

        if (result == 1) {
            System.out.println(user.getId() + "님 회원 가입이 완료되었습니다.");
            System.out.println("로그인 후 이용해주세요.");
        } else {
            System.out.println("회원 가입에 실패하였습니다. 다시 시도해주세요.");
        }
        System.out.println("처음 화면으로 이동합니다.");
    }

    // 회원 가입시 ID 중복 체크
    public boolean isDuplicatedID(String id) {
        boolean idDuplicatedFlag = userRepository.getIds().contains(id);

        if (idDuplicatedFlag) {
            System.out.println("중복된 ID가 존재합니다. ID를 다시 입력해주세요!");
        } else {
            System.out.println("사용 가능한 ID 입니다.");
        }

        return idDuplicatedFlag;
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
            UserView userView = new UserView(findedUser);
            int actionResult = userView.firstHomePage();
            if (actionResult == 1) System.out.println("로그아웃 합니다.");
        }else {
            // 로그인 실패 -> 처음 화면으로 이동
            System.out.println("로그인 실패하였습니다.");
        }

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

    // 성별이 다른 유저만 필터링해서 List로 반환
    public List<User> findMatchingPartner(User user) {
        // 유저 목록 파일(DB)을 가져온다. users
        // 매개변수의 성별과 조회된 목록들의 성별이 다른 유저들만 조회되도록 필터링한다.
        List<User> userList = userRepository.findAll();
        List<User> matchingPartners = new ArrayList<>();
        for (int i = 0; i < userList.size(); i++) {
            if (user.getGender() != userList.get(i).getGender()) {
                matchingPartners.add(userList.get(i));
            }
        }
        // 필터링된 조회를 리스트로 반환한다.
        return matchingPartners;
    }
}
