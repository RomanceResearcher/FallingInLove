package com.romanceResearcher.view;

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

    public RandomMatchView(User user, MatchService matchService) {
        this.user = user;
        this.matchService = matchService;
    }

    Scanner sc = new Scanner(System.in);
    MatchService matchservice = new MatchService(new UserRepository()); // 수정 예정
    // 소개팅 UI
    public void datingUI() {
        // 1. 랜덤 소개팅 하기
        // 2. 호감 받은 상대 프로필 조회
        // 3. 호감 보낸 상대 프로필 조회
        while (true) {
            System.out.println("===== 소개팅 화면입니다. =====");
            System.out.println("1 : 랜덤 소개팅 하기");
            System.out.println("2 : 호감 받은 상대 프로필 조회하기");
            System.out.println("3 : 호감있는 상대 프로필 조회하기");
            System.out.println("4 : 이전 화면으로 돌아가기");
            System.out.println("메뉴 선택 : ");

            try {
                int action = sc.nextInt(); // 입력받은 번호를 action 변수에 저장
                switch (action) {
                    case 1: matchService.randomDating(user); break; // 랜덤 매칭 기능 호출
                    case 2: matchService.showPartnersOfReceiveSignal(); break; // 호감 받은 상대 조회
                    case 3: matchService.showPartnersOfSendSignal(); break; // 호감 보낸 상대 조회
                    case 4: return; // 메소드 종료
                    default :
                        System.out.println("번호를 잘 못 입력하였습니다.");
                }
            } catch (Exception e) {
                System.out.println("메뉴 숫자를 입력해주세요.");
                sc.next(); // 버퍼 내 잘못된 입력값 버리기
            }

        }


    }



}
