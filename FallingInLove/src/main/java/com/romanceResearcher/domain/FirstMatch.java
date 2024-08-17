package com.romanceResearcher.domain;

import java.io.Serializable;
import java.time.LocalTime;

public class FirstMatch implements Serializable {

    private long firstMatchNo = 0; // 매치 번호
    private User to; // 호감 보낸 사람
    private User from; // 호감 받는 사람
    private boolean acceptFlag; // From 이 To 에게 호감을 보낸 여부 (true 라면 최종결정 db에 저장)
    private LocalTime createDate; // 매치 생성 일자

    // 단방향 호감 전송 기록 생성
    public FirstMatch(long firstMatchNo, User to, User from, LocalTime createDate) {
        this.firstMatchNo = firstMatchNo;
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
