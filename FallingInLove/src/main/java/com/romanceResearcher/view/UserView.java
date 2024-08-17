package com.romanceResearcher.view;

import com.romanceResearcher.domain.User;
import com.romanceResearcher.domain.UserDto;
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


    public void firstHomePage() {

        Scanner sc = new Scanner(System.in);
        UserService userService = new UserService();

        while (true) {
            System.out.print("원하는 메뉴를 선택하세요 (1 : 로그아웃, 2: 랜덤 프로필 보기, 3: 마이페이지, 4: 회원 정보 수정, 5: 회원 탈퇴");
        }
    }

    // 마이페이지 UI
    public void userInfoUi() {
        Scanner sc = new Scanner(System.in);

                /*private long userNo; // 회원 번호
            private String name; // 회원 이름
            private Date birth; // 생년월일
            private String id; // 회원 아이디
            private String pwd; // 회원 비밀번호
            private String email; // 회원 이메일
            private String hp; // 회원 전화번호
            private char gender; // 회원 성별
            private List<String> pictures; // 회원 사진 리스트
            private long point; // 회원 포인트*/
        while (true) {
            System.out.print("마이페이지입니다. (1 : 프로필 정보 조회, 2 : 프로필 정보 수정(사진 제외), 3 : 프로필 사진 수정 ");
            int action = sc.nextInt();
            if (action == 1) { // 정보 조회
                System.out.println(user.toString());
            } else if (action == 2) { // 정보 수정
                System.out.print("변경할 이름 : ");
                user.setName(sc.nextLine());
                System.out.print("변경할 비밀번호 : ");
                user.setPwd(sc.nextLine());
                System.out.print("변경할 이메일 : ");
                user.setEmail(sc.nextLine());
                System.out.print("변경할 전화번호 : ");
                user.setHp(sc.nextLine());

            } else if (action == 3) { // 사진 정보 수정
                PictureService pictureService = new PictureService(user.getPictures());
                while (true) {
                    System.out.print("1 : 사진 추가, 2 : 기존 사진 수정, 3 : 사진 삭제");
                    int pictureMenu = sc.nextInt();

                    if (pictureMenu == 1) {

                    } else if (pictureMenu == 2) {
                        if (pictureService.hasPictures()) {
                            System.out.print("저장된 사진이 없습니다. 메뉴를 다시 선택해주세요.");
                        } else {
                            System.out.print("수정할 사진을 선택하세요 (인덱스 입력) : ");
                            int idx = sc.nextInt();
                            System.out.print("대체할 사진을 입력해주세요 : ");
                            String newPicture = sc.nextLine();
                            pictureService.updatePicture(idx, newPicture);
                        }
                    }
                    if (pictureService.getPictures().isEmpty()) {
                    } else {
                        System.out.print("변경할 사진의 인덱스 번호를 누르세요 (0번 인덱스부터 시작) : ");
                        int picIndex = sc.nextInt();
                        if (picIndex < 0 || picIndex >= user.getPictures().size()) {
                            System.out.println("해당 인덱스의 사진은 존재하지 않습니다. 다시 입력해주세요.");
                        } else {
                            userPictures.set(picIndex); // 사진 삭제

                        }
                    }

                }
            }
        }
    }

}
