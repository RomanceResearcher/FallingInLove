package com.romanceResearcher.service;

import com.romanceResearcher.repository.MatchRepository;
import com.romanceResearcher.repository.UserRepository;

public class MatchService {

    private final UserRepository userRepository;
    private final MatchRepository matchRepository;

    public MatchService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.matchRepository = new MatchRepository();
    }

}
