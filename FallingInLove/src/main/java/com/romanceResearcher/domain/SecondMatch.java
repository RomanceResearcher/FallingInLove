package com.romanceResearcher.domain;

import java.util.List;

// 서로 호감표시를 보낸 기록을 담는 클래스
// 매치 여부를 알 수 있음
public class SecondMatch {

    private long secondMatchNo; // 세컨 매치 번호
    private List<User> counple;
    private boolean finalAccept; // 세컨 매치 연결 여부

    public SecondMatch() {}

    public SecondMatch(List<User> counple, boolean finalAccept) {

    }
}
