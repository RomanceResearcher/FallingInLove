package com.romanceResearcher.repository;

import com.romanceResearcher.domain.SecondMatch;
import com.romanceResearcher.domain.User;
import com.romanceResearcher.myio.MyObjectOutputStream;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SecondMatchRepository {

    private static SecondMatchRepository instance;
    private List<SecondMatch> secondMatches = new ArrayList<>();
    private final String SECOND_MATCH_FILENAME = "src/main/java/com/romanceResearcher/db/secondmatchdb.dat";

    public static SecondMatchRepository getInstance() {
        if (instance == null) {
            instance = new SecondMatchRepository();
        }
        return instance;
    }

    private SecondMatchRepository() {

        File file = new File(SECOND_MATCH_FILENAME);

        if (!file.exists()) {
            List<SecondMatch> sampleData = new ArrayList<>();

            saveSecondMatches(file, sampleData);
        }
        loadSencondMatches(file);

    }

    // 파일에서 데이터 꺼내오기
    public void loadSencondMatches(File file) {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                secondMatches.add((SecondMatch) ois.readObject());
            }

        } catch (EOFException e) {
//            System.out.println("SecondMatch 파일을 모두 로딩했습니다.");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // 파일에 저장하기 (덮어쓰우기)
    public void saveSecondMatches(File file, List<SecondMatch> secondMatchList) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for (SecondMatch secondMatch : secondMatchList) {
                oos.writeObject(secondMatch);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // crud

    // secondMatch 추가
    public void addSecondMatch(SecondMatch secondMatch) {

        secondMatch.setSecondMatchNo(getSecondMatchNo());
        try (MyObjectOutputStream moo = new MyObjectOutputStream(new FileOutputStream(SECOND_MATCH_FILENAME, true))) {
            secondMatch.setSecondMatchNo(secondMatch.getSecondMatchNo());
            moo.writeObject(secondMatch);
            secondMatches.add(secondMatch);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    // SecondMatch 목록 조회 (userId 로 조회)
    public List<SecondMatch> findSecondMatchByUserId(String userId) {

        List<SecondMatch> mySecondMatches = new ArrayList<>();

        for(SecondMatch secondMatch : secondMatches) {
            if(secondMatch.getCoupleId().contains(userId))
                mySecondMatches.add(secondMatch);
        }

        return mySecondMatches;
    }

    // SecondMatch 전체 목록 조회
    public List<SecondMatch> findAllSecondMatches() {
        return secondMatches;
    }

    // SecondMatch 수정
    public void updateSecondMatch(SecondMatch secondMatch) {

        for (int i = 0; i < secondMatches.size(); i++) {
            if (secondMatch.getSecondMatchNo() == secondMatches.get(i).getSecondMatchNo()) {
                secondMatches.set(i, secondMatch);
            }
        }

        File file = new File(SECOND_MATCH_FILENAME);

        saveSecondMatches(file, secondMatches);
    }

    // SecondMatch 삭제
    public void deleteSecondMatch(SecondMatch secondMatch) {

        for(SecondMatch sm : secondMatches) {
            if (sm.getSecondMatchNo() == secondMatch.getSecondMatchNo()) {
                secondMatches.remove(sm);
                break;
            }
        }

        File file = new File(SECOND_MATCH_FILENAME);

        saveSecondMatches(file, secondMatches);
    }

    public long getSecondMatchNo() {
        if(secondMatches.isEmpty()) return 0;
        return secondMatches.get(secondMatches.size() - 1).getSecondMatchNo() + 1;
    }

    public void saveSecondMatchesList(List<SecondMatch> allSecondMatches) {
        saveSecondMatches(new File(SECOND_MATCH_FILENAME), allSecondMatches);
    }


    // 쌍방호감 성사

    // 채팅방 오픈 여부

    // 포인트 차감

}
