package com.romanceResearcher.service;

import com.romanceResearcher.domain.User;
import com.romanceResearcher.repository.MatchRepository;
import com.romanceResearcher.repository.UserRepository;

public class MatchService {

    // 나, 상대 정보를 매개인자로 받아서 바탕으로 매치 정비
    // 매치 정보(객체) 생성
    private final UserRepository userRepository;
    private final MatchRepository matchRepository;

    // 생성된 매치 정보(인자)를 repo로 보낸다.
    //repo.aptjem(매치)
    public MatchService(UserRepository userRepository, MatchRepository matchRepository) {
        this.userRepository = userRepository;
        this.matchRepository = new matchRepository();

    }

    public static void randomDating() {

    }




}
