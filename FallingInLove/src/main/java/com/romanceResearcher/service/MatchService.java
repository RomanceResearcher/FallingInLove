package com.romanceResearcher.service;

import com.romanceResearcher.domain.User;
import com.romanceResearcher.repository.MatchRepository;
import com.romanceResearcher.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MatchService {

    // 나, 상대 정보를 바탕으로 매치 정보 객체 생성
    // 매치 정보(객체) 생성
    private UserRepository userRepository;
    private List<User> randomPartners = new ArrayList<>();
    public User randomDatingPartner;

    Scanner sc = new Scanner(System.in);


    public MatchService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void randomDating(User user) {
        randomPartners = userRepository.findMatchingPartner(user);

        while (true) {
            int randomPartnerId = (int) (Math.random() * randomPartners.size() + 1);
            randomDatingPartner = randomPartners.get(randomPartnerId);
            System.out.println(randomDatingPartner);
            System.out.println("1 : 호감 보내기");
            System.out.println("2 : 다음 상대 보기");
            System.out.println("3 : 소개팅 종료하기");

            try {
                int action = sc.nextInt(); // 입력받은 번호를 action 변수에 저장
                switch (action) {
                    case 1: sendSignal(randomDatingPartner); continue; // 호감 보내기 -> 호감 보낸 기록을 생성해서 저장해야됌
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

    // 호감을 보낸 유저와의 firstMatch 생성 및 리스트 추가
    private void sendSignal(User randomDatingPartner) {
        MatchRepository.addFirstMatch(User randomDatingPartner); // firstMatch 추가
    }




}
