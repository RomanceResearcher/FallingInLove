package com.romanceResearcher.view;

import com.romanceResearcher.domain.User;
import com.romanceResearcher.service.PictureService;
import com.romanceResearcher.service.UserService;

import java.awt.*;
import java.util.List;
import java.util.Scanner;

/* 로그인 후 나오는 메뉴 선택 화면 */
public class UserView {

    private final User user; // 개인 유저
    private final UserService userService; // 개인 유저에 대한 서비스

    public UserView(User user, UserService userService) {
        this.user = user;
        this.userService = userService;
    }


    public void firstHomePage() { // 로그인 후 초기화면

        Scanner sc = new Scanner(System.in);
        UserService userService = UserService.getInstance();

        while (true) {
            System.out.print("원하는 메뉴를 선택하세요 (1 : 로그아웃, 2: 소개팅하러 가기, 3: 마이페이지, 4: 회원 정보 수정, 5: 회원 탈퇴");
            int action = sc.nextInt();
            switch (action) {
                case 1 : return;
                case 2 :
            }
        }
    }

    // 마이페이지 UI
    public void userInfoUi() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("마이페이지입니다. (1 : 프로필 정보 조회, 2 : 프로필 정보 수정(사진 제외), 3 : 프로필 사진 수정 ");
            int action = sc.nextInt();
            if (action == 1) { // 정보 조회
                System.out.println(user.toString());
            } else if (action == 2) { // 정보 수정
                updateUserView(sc);

            } else if (action == 3) { // 사진 정보 수정
                pictureRevisionView(sc);
            }
        }
    }

    // 회원 정보 수정 view
    private void updateUserView(Scanner sc) {
        System.out.print("변경할 이름 : ");
        user.setName(sc.nextLine());
        System.out.print("변경할 비밀번호 : ");
        user.setPwd(sc.nextLine());
        System.out.print("변경할 이메일 : ");
        user.setEmail(sc.nextLine());
        System.out.print("변경할 전화번호 (형식 : 000-0000-0000) : ");
        user.setHp(sc.nextLine());

        userService.updateUser(user);
    }

    // 사진 정보 수정 view
    private void pictureRevisionView(Scanner sc) {
        PictureService pictureService = new PictureService(user.getPictures());
        while (true) {
            System.out.print("1 : 사진 추가, 2 : 기존 사진 수정, 3 : 사진 삭제, 4 : 뒤로 가기");
            int pictureMenu = sc.nextInt();

            if (pictureMenu == 1) { // 사진 추가

            } else if (pictureMenu == 2) { // 기존 사진 수정
                if (pictureService.hasPictures()) { // 기존에 아무 사진도 없다면
                    System.out.print("저장된 사진이 없습니다. 메뉴를 다시 선택해주세요.");
                } else { // 기존에 사진이 있다면
                    System.out.print("수정할 사진을 선택하세요 (인덱스 입력) : ");
                    int idx = sc.nextInt();
                    System.out.print("대체할 사진을 입력해주세요 : ");
                    String newPicture = sc.nextLine();
                    pictureService.updatePicture(idx, newPicture);
                }
            } else if (pictureMenu == 3) {
                if (pictureService.getPictures().isEmpty()) {
                } else {
                    System.out.print("변경할 사진의 인덱스 번호를 누르세요 (0번 인덱스부터 시작) : ");
                    int picIndex = sc.nextInt();
                    if (picIndex < 0 || picIndex >= user.getPictures().size()) {
                        System.out.println("해당 인덱스의 사진은 존재하지 않습니다. 다시 입력해주세요.");
                    } else {
                        pictureService.deletePicture(picIndex); // 사진 삭제

                    }
                }
            } else if (pictureMenu == 4) {
                userService.updateUser(user); // 회원 정보 업데이트
                break;
            } else {
                System.out.println("다시 입력해주세요.");
            }
        }
    }

}
