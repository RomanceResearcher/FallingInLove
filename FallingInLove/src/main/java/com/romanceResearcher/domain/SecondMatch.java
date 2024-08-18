package com.romanceResearcher.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

// 서로 호감표시를 보낸 기록을 담는 클래스
// 매치 여부를 알 수 있음
public class SecondMatch implements Serializable {

    private long secondMatchNo; // 세컨 매치 번호
    private List<String> coupleId; // 유저의 Id만 저장하는 List
    private boolean finalAccept; // 세컨 매치 연결 여부

    public SecondMatch() {}

    public SecondMatch(List<String> coupleId) {
        this.coupleId = coupleId;
    }

    public long getSecondMatchNo() {
        return secondMatchNo;
    }

    public void setSecondMatchNo(long secondMatchNo) {
        this.secondMatchNo = secondMatchNo;
    }

    public void setCouple(List<String> couple) {
        this.coupleId = couple;
    }

    public List<String> getCoupleId() {
        return coupleId;
    }

    public void setFinalAccept(boolean finalAccept) {
        this.finalAccept = finalAccept;
    }

    // 해당 아이디의 secondMatch 인지를 확인하는 메소드
    public boolean isContainsId(String userId) {
        return coupleId.contains(userId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecondMatch that = (SecondMatch) o;
        return secondMatchNo == that.secondMatchNo && finalAccept == that.finalAccept && Objects.equals(coupleId, that.coupleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(secondMatchNo, coupleId, finalAccept);
    }

    @Override
    public String toString() {
        return "SecondMatch{" +
                "< " + coupleId.get(0) +  ", " + coupleId.get(1) + " >" +
                ", finalAccept=" + finalAccept +
                '}';
    }
}
