package com.romanceResearcher.service;

import com.romanceResearcher.domain.FirstMatch;
import com.romanceResearcher.domain.SecondMatch;
import com.romanceResearcher.domain.User;
import com.romanceResearcher.repository.SecondMatchRepository;

import java.util.List;

public class SecondMatchService {


    private static SecondMatchService instance;
    private final SecondMatchRepository secondMatchRepository;


    public static SecondMatchService getInstance() {
        if (instance == null) {
            instance = new SecondMatchService();
        }
        return instance;
    }


    private SecondMatchService() {
        secondMatchRepository = SecondMatchRepository.getInstance();
    }

    // SecondMatch 기록 생성하기
    // 내가 좋아요 한 상대가 소개팅에 동의한다면 secondMatches 리스트에 기록 저장
    public void addSecondMatch(FirstMatch firstMatch) {
        secondMatchRepository.addSecondMatch(firstMatch.changeToSecondMatch());
    }

    // 나의 SecondMatch 기록 보기
    public List<SecondMatch> showMySecondMatches(User user) {
        return secondMatchRepository.findSecondMatchByUserId(user.getId());
    }

    // 매칭 수락
    public void acceptedSecondMatch(SecondMatch secondMatch) {
        secondMatch.setFinalAccept(true);
        secondMatchRepository.updateSecondMatch(secondMatch);
    }

    // 해당 SecondMatch 삭제
    public void deleteSecondMatch(SecondMatch secondMatch) {
        secondMatchRepository.deleteSecondMatch(secondMatch);
    }


    public void deleteMyAllSecondMatch(String id) {
        List<SecondMatch> allSecondMatches = secondMatchRepository.findAllSecondMatches();

        for (SecondMatch secondMatch : allSecondMatches) {
            if (secondMatch.getCoupleId().contains(id))
                allSecondMatches.remove(secondMatch);
        }

        secondMatchRepository.saveSecondMatchesList(allSecondMatches);
    }
}
