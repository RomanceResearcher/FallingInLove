package com.romanceResearcher.view;

import com.romanceResearcher.domain.User;
import com.romanceResearcher.service.MatchService;
import com.romanceResearcher.service.UserService;

import java.util.List;
import java.util.Scanner;

// 랜덤 매칭 화면
public class RandomMatchView {

    // 상대 목록을 보여준다.(상대 객체1)
    // 유저 repo에 리스트파일 -> 랜덤으로 유저 정보를 전송받음 -> 뷰에 존재하는 리스트에 담고 -> 거기서 랜덤으로 한개씩 화면에 도출
    // 필터링 된 회원 리스트 -> 2차적으로 필터링 하는 것


    // 소개팅 UI
    Scanner sc = new Scanner(System.in);

    // 1. 랜덤 소개팅 하기
    // 2. 호감 받은 상대 프로필 조회
    // 3. 호감 보낸 상대 프로필 조회

    public void datingUI() {

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
                    case 1: MatchService.randomDating(); break; // 랜덤 매칭 기능 호출
                    case 2: MatchService.showPartnersOfReceiveSignal(); break; // 호감 받은 상대 조회
                    case 3: MatchService.showPartnersOfSendSignal(); break; // 호감 보낸 상대 조회
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
