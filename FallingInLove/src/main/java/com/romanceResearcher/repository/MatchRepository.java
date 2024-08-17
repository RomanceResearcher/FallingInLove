package com.romanceResearcher.repository;

import com.romanceResearcher.domain.FirstMatch;
import com.romanceResearcher.domain.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MatchRepository {

    // 매치 기록을 저장하는 리스트 선언 및 초기화
    private static final String FILENAME = "src/main/java/com/romanceResearcher/db/firstMatchRepo.dat";
    private final List<FirstMatch> firstMatches = new ArrayList<>();


    public MatchRepository() {

        File file = new File(FILENAME);

        if (!file.exists()) {

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
                // sample user 추가
                firstMatches.add(new FirstMatch());
            } catch (IOException e) {
                System.out.println("해당 파일이 존재하지 않습니다.");
            }
        } else {
            loadUser(file);
        }

    // CRUD
    private long firstMatchgityNo; // 매치 번호
    private User to; // 호감 보낸 사람
    private User from; // 호감 받는 사람
    private boolean acceptFlag; // From 이 To 에게 호감을 보낸 여부 (true 라면 최종결정 db에 저장)
    private LocalTime createDate; // 매치 생성 일자

    public MatchRepository(File) {}


    // 변경된 상대 유저의 정보를 FirstMatch 리스트에 저장
    public static void acceptFlagUpdate(boolean likeSignal) {

    }





}
