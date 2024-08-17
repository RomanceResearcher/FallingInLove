package com.romanceResearcher.domain;

import java.time.LocalTime;

public class FirstMatch {

    private long firstMatchNo; // 매치 번호
    private User to; // 호감 보낸 사람
    private User from; // 호감 받는 사람
    private boolean acceptFlag; // From 이 To 에게 호감을 보낸 여부 (true 라면 최종결정 db에 저장)
    private LocalTime createDate; // 매치 생성 일자

    public FirstMatch(User to, User from, LocalTime createDate) {
        this.to = to;
        this.from = from;
        this.createDate = createDate;
    }


    public boolean isAcceptFlag() {
        return acceptFlag;
    }

    public void setAcceptFlag(boolean acceptFlag) {
        this.acceptFlag = acceptFlag;
    }

}
