package com.romanceResearcher.repository;

import com.romanceResearcher.domain.FirstMatch;
import com.romanceResearcher.domain.User;

import java.io.File;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MatchRepository {

    // 매치 기록을 저장하는 리스트 선언 및 초기화
    private final List<FirstMatch> firstMatches = new ArrayList<>();

    // CRUD
    private long firstMatchgityNo; // 매치 번호
    private User to; // 호감 보낸 사람
    private User from; // 호감 받는 사람
    private boolean acceptFlag; // From 이 To 에게 호감을 보낸 여부 (true 라면 최종결정 db에 저장)
    private LocalTime createDate; // 매치 생성 일자

    public MatchRepository(File) {}

    //f.add(매치) -> 리스트에 정보 입력

    // 입력한 리스트를 파일에 저장

    // acceptFlag -> true
    // sRepo (인자)



}
