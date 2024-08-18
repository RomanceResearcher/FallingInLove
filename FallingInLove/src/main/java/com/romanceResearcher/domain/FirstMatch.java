package com.romanceResearcher.domain;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Arrays;

public class FirstMatch implements Serializable {

    private long firstMatchNo = 0; // 매치 번호 -> 추후에 자동으로 생성되도록 수정
    private User to; // 호감 받는 사람
    private User from; // 호감 보낸 사람
    private boolean acceptFlag; // From 이 To 에게 호감을 보낸 여부 (true 라면 최종결정 db에 저장)
    private LocalTime createDate; // 매치 생성 일자

    // 단방향 호감 전송 기록 생성
    public FirstMatch(long firstMatchNo, User to, User from, LocalTime createDate) {
        this.firstMatchNo = firstMatchNo;
        this.to = to;
        this.from = from;
        this.createDate = createDate;
    }

    public long getFirstMatchNo() {
        return firstMatchNo;
    }

    public void setFirstMatchNo(long firstMatchNo) {
        this.firstMatchNo = firstMatchNo;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public LocalTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalTime createDate) {
        this.createDate = createDate;
    }

    public boolean isAcceptFlag() {
        return acceptFlag;
    }

    public void setAcceptFlag(boolean acceptFlag) {
        this.acceptFlag = acceptFlag;
    }

    @Override
    public String toString() {
        return "FirstMatch{" +
                "to=" + to.getName() +
                ", from=" + from.getName() +
                ", acceptFlag=" + acceptFlag +
                ", createDate=" + createDate +
                '}';
    }

    public SecondMatch changeToSecondMatch() {
        return new SecondMatch(Arrays.asList(this.getTo().getId(), this.getFrom().getId()));
    }
}
