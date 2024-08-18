package com.romanceResearcher.view;

import com.romanceResearcher.domain.FirstMatch;
import com.romanceResearcher.domain.User;
import com.romanceResearcher.repository.UserRepository;
import com.romanceResearcher.service.MatchService;
import com.romanceResearcher.service.UserService;

import java.util.List;
import java.util.Scanner;

// 랜덤 매칭 화면
public class RandomMatchView {

    private final User user; // 개인 유저
    private final MatchService matchService; // 개인 유저에 대한 Match 서비스
    private final UserService userService;
    private final Scanner sc = new Scanner(System.in);

    public RandomMatchView(User user) {
        this.user = user;
        this.matchService = MatchService.getInstance();
        this.userService = UserService.getInstance();
    }


    // 소개팅 UI
    public void datingUI() {
        // 1. 랜덤 소개팅 하기
        // 2. 사용자에게 호감 보낸 상대 프로필 조회
        // 3. 사용자가 호감 보낸 상대 프로필 조회
        while (true) {
            System.out.println("===== 소개팅 화면입니다. =====");
            System.out.println("1 : 랜덤 소개팅 하기");
            System.out.println("2 : 호감 받은 상대 프로필 조회하기");
            System.out.println("3 : 호감있는 상대 프로필 조회하기");
            System.out.println("4 : 서로 호감있는 소개팅 조회하기");
            System.out.println("5 : 이전 화면으로 돌아가기");
            System.out.println("메뉴 선택 : ");

            try {
                int action = sc.nextInt(); // 입력받은 번호를 action 변수에 저장
                switch (action) {
                    case 1: randomDatingView(); break; // 랜덤 매칭 기능 호출
                    case 2: showReceiveSignalfromPartner(user); break; // 사용자에게 호감 보낸 상대 프로필 조회
                    case 3: showSendSignaltoPartner(user); break; // 사용자가 호감 보낸 상대 프로필 조회
                    case 4: showSecondMatches(); break; //
                    case 5: return; // 메소드 종료
                    default :
                        System.out.println("번호를 잘 못 입력하였습니다.");
                }
            } catch (Exception e) {
                System.out.println("메뉴 숫자를 입력해주세요.");
                sc.next(); // 버퍼 내 잘못된 입력값 버리기
            }

        }


    }

    // 랜덤 소개팅 view
    public void randomDatingView() {
        List<User> randomPartners = userService.findMatchingPartner(user);
        User randomDatingPartner = null;
        while (true) {
            int randomPartnerId = (int) (Math.random() * randomPartners.size() + 1);
            randomDatingPartner = randomPartners.get(randomPartnerId);
            System.out.println(randomDatingPartner.showUserInfo());
            System.out.println("1 : 호감 보내기");
            System.out.println("2 : 다음 상대 보기");
            System.out.println("3 : 소개팅 종료하기");

            try {
                int action = sc.nextInt(); // 입력받은 번호를 action 변수에 저장
                switch (action) {
                    case 1: matchService.sendSignal(randomDatingPartner); continue; // 호감 보내기 -> 호감 보낸 기록을 생성해서 저장해야됌
                    case 2: continue; // 다음 상대 보기
                    case 3: return; // 메소드 종료
                    default :
                        System.out.println("번호를 잘 못 입력하였습니다.");
                }
            } catch (Exception e) {
                System.out.println("숫자를 입력해주세요.");
                sc.next(); // 버퍼 내 잘못된 입력값 버리기
            }
        }
    }

    // 사용자에게 호감 보낸 상대 프로필 조회
    private void showReceiveSignalfromPartner(User user) {
        List<FirstMatch> firstMatches = matchService.getReceiveSignalFromPartner(user);
        for (FirstMatch firstMatch : firstMatches) {
            System.out.println(firstMatch);
        }
    }

    // 사용자가 호감 보낸 상대 프로필 조회
    private void showSendSignaltoPartner(User user) {
        List<FirstMatch> firstMatches = matchService.showSendSignaltoPartner(user);
        for (FirstMatch firstMatch : firstMatches) {
            System.out.println(firstMatch);
        }
    }


    private void showSecondMatches() {

    }
}
