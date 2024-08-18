package com.romanceResearcher.service;

import com.romanceResearcher.repository.SecondMatchRepository;

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
    public void addSecondMatch() {

    }
}
