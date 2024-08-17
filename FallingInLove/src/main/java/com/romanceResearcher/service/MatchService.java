package com.romanceResearcher.service;

import com.romanceResearcher.domain.User;
import com.romanceResearcher.repository.MatchRepository;
import com.romanceResearcher.repository.UserRepository;

import java.util.List;
import java.util.Random;

public class MatchService {

    // 나, 상대 정보를 바탕으로 매치 정보 객체 생성
    // 매치 정보(객체) 생성
    private UserRepository userRepository;

    public MatchService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void randomDating(User user) {
        List<User> randomPartners = userRepository.findMatchingPartner(user);
        Random random = new Random();
        int randomPartnerId = random.nextInt(randomPartners.size());
        User randomDatingPartner = randomPartners.get(randomPartnerId);
    }

}
