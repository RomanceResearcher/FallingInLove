package com.romanceResearcher.view;

import com.romanceResearcher.domain.User;
import com.romanceResearcher.repository.UserRepository;
import com.romanceResearcher.service.MatchService;
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


    // 반환값
    // 1 : 로그아웃, 2 : 회원 탈퇴
    public int firstHomePage() { // 로그인 후 초기화면

        Scanner sc = new Scanner(System.in);
        RandomMatchView randomMatchView = new RandomMatchView(user);

        while (true) {
            System.out.print("원하는 메뉴를 선택하세요 (1 : 로그아웃, 2: 소개팅하러 가기, 3: 마이페이지");
            int action = sc.nextInt();
            switch (action) {
                case 1 : return 1;
                case 2 : randomMatchView.datingUI(); break;
                case 3 :
                    boolean deletedFlag = userInfoUi();
                    if (deletedFlag) return 2;
                default :
                    System.out.println("잘못 입력하셨습니다. 메뉴에 있는 번호를 입력해주세요.");
                    break;
            }
        }
    }

    // 마이페이지 UI
    // 반환값
    // true : 회원 탈퇴 했음, false : 회원 탈퇴 안함
    public boolean userInfoUi() {
        Scanner sc = new Scanner(System.in);

        label:
        while (true) {
            System.out.print("마이페이지입니다. (1 : 프로필 정보 조회, 2 : 프로필 정보 수정(사진 제외), 3 : 프로필 사진 수정, 4 : 회원 탈퇴, 5 : 뒤로 가기");
            int action = sc.nextInt();

            switch (action) {
                case 1 : System.out.println(user.toString()); break; // 정보 조회
                case 2 : updateUserView(sc); break; // 정보 수정
                case 3 : pictureRevisionView(sc); break; // 사진 정보 수정
                case 4 : if (deleteUserView(sc)) return true; // 회원 탈퇴 기능 true : 회원 탈퇴가 된 경우
                case 5 : System.out.println("이전 페이지로 이동합니다."); break label; // 뒤로 가기
                default : System.out.println("잘못 입력하셨습니다. 메뉴에 있는 번호를 입력해주세요."); break;
            }
        }
        // false : 회원 탈퇴를 안한 경우
        return false;
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
            } else if (pictureMenu == 3) { // 사진 삭제
                if (pictureService.getPictures().isEmpty()) { // 기존에 아무 사진도 없다면
                    System.out.print("저장된 사진이 없습니다. 메뉴를 다시 선택해주세요.");
                } else { // 기존에 사진이 있다면
                    System.out.print("변경할 사진의 인덱스 번호를 누르세요 (0번 인덱스부터 시작) : ");
                    int picIndex = sc.nextInt();
                    if (picIndex < 0 || picIndex >= user.getPictures().size()) { // 해당 인덱스의 사진이 없는 경우
                        System.out.println("해당 인덱스의 사진은 존재하지 않습니다. 다시 입력해주세요.");
                    } else { // 해당 인덱스의 사진이 존재하는 경우 -> 사진 삭제 진행
                        pictureService.deletePicture(picIndex); // 사진 삭제
                        System.out.println("해당 사진이 삭제되었습니다.");
                    }
                }
            } else if (pictureMenu == 4) { // 뒤로 가기
                userService.updateUser(user); // 회원 정보 업데이트
                break;
            } else { // 잘못된 메뉴 번호를 눌렀을 경우
                System.out.println("잘못 입력하셨습니다. 메뉴에 있는 번호를 입력해주세요.");
            }
        }
    }

    // 회원 탈퇴 view
    private boolean deleteUserView(Scanner sc) {
        System.out.print("정말 탈퇴하시겠습니까? (예 : YES, 아니오 : NO");
        String deleteResponse = sc.nextLine();
        if (deleteResponse.equals("YES")) {
            System.out.println(user.getId() + "님 회원 탈퇴가 되었습니다.");
            System.out.println(user.getId() + "님 지금까지 저희 만년설을 이용해주셔서 감사합니다.");
            userService.deleteUser(user);
            return true;
        } else if (deleteResponse.equals("NO")) {
            System.out.println("탈퇴를 취소하셨습니다.");
        } else {
            System.out.println("잘못 입력하셨습니다. 마이페이지 메뉴로 돌아갑니다.");
        }
        return false;
    }

}
