package com.romanceResearcher.service;

import com.romanceResearcher.domain.FirstMatch;
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
    private final UserRepository userRepository;
    private final MatchRepository matchRepository;
    private final SecondMatchService secondMatchService;
    private static MatchService instance;




    Scanner sc = new Scanner(System.in);


    public static MatchService getInstance() {
        if (instance == null) {
            instance = new MatchService();
        }
        return instance;
    }

    private MatchService() {
        this.userRepository = UserRepository.getInstance();
        this.matchRepository = MatchRepository.getInstance();
        this.secondMatchService = SecondMatchService.getInstance();
    }


    // 내가 좋아요 한 상대가 소개팅에 동의한다면 secondMatches 리스트에 기록 저장
    public void succeseMatching() {
        secondMatchService.addSecondMatch();
    }


    // 호감을 보낸 유저와의 firstMatch 생성 및 리스트 추가
    public void sendSignal(User randomDatingPartner) {
        matchRepository.addFirstMatch(randomDatingPartner); // firstMatch 추가
    }

    // 사용자한테 호감을 보낸 유저 목록 조회
    public void getReceiveSignalFromPartner(User user) {
        List<FirstMatch> allMatches = matchRepository.findAllMatches();

    }



    // 내가 호감보낸 상대 목록 리스트 생성 후 반환
    public List<FirstMatch> showSendSignaltoPartner(User user) {

        // FirstMatch list를 가져온다.
        List<FirstMatch> allMatches = matchRepository.findAllMatches();

        // 내가 호감보낸 상대 리스트 생성
        List<FirstMatch> myFirstMatches = new ArrayList<>();

        // 매개변수와 User from 가 같으면 myFirstMatches 리스트에 담는다.
        for (int i = 0; i < allMatches.size(); i++) {
            if (user.getId()/*사용자*/.equals(allMatches.get(i).getFrom().getId())) {
                myFirstMatches.add(allMatches.get(i));
            }
        }

        return myFirstMatches;
    }
}
