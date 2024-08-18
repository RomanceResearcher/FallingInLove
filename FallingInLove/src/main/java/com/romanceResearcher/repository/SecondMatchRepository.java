package com.romanceResearcher.repository;

import com.romanceResearcher.domain.SecondMatch;
import com.romanceResearcher.domain.User;
import com.romanceResearcher.myio.MyObjectOutputStream;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SecondMatchRepository {

    private static SecondMatchRepository instance;
    private List<SecondMatch> secondMatches;
    private final String SECOND_MATCH_FILENAME = "src/main/java/com/romanceResearcher/db/secondmatchdb.dat";

    public SecondMatchRepository getInstance() {
        if (instance == null) {
            instance = new SecondMatchRepository();
        }
        return instance;
    }

    private SecondMatchRepository() {

        File file = new File(SECOND_MATCH_FILENAME);

        if (!file.exists()) {
            SecondMatch sampleSecondMatch1 = new SecondMatch(Arrays.asList("testId01", "testId04"));
            SecondMatch sampleSecondMatch2 = new SecondMatch(Arrays.asList("testId01", "testId05"));
            SecondMatch sampleSecondMatch3 = new SecondMatch(Arrays.asList("testId01", "testId06"));
            SecondMatch sampleSecondMatch4 = new SecondMatch(Arrays.asList("testId02", "testId04"));
            SecondMatch sampleSecondMatch5 = new SecondMatch(Arrays.asList("testId03", "testId05"));
            SecondMatch sampleSecondMatch6 = new SecondMatch(Arrays.asList("testId03", "testId06"));

            saveSecondMatches(file);
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
            System.out.println("SecondMatch 파일을 모두 로딩했습니다.");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // 파일에 저장하기 (덮어쓰우기)
    private void saveSecondMatches(File file) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for (SecondMatch secondMatch : secondMatches) {
                oos.writeObject(secondMatch);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // crud

    // secondMatch 추가
    public void addSecondMatch(SecondMatch secondMatch) {

        try (MyObjectOutputStream moo = new MyObjectOutputStream(new FileOutputStream(SECOND_MATCH_FILENAME, true))) {
            moo.writeObject(secondMatch);
            secondMatches.add(secondMatch);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    // SecondMatch 목록 조회
    public List<SecondMatch> findSecondMatchByUserId(String userId) {
        return secondMatches;
    }

    // SecondMatch 수정
    public void updateSecondMatch(SecondMatch secondMatch) {
        secondMatch.setFinalAccept(true);

        for (int i = 0; i < secondMatches.size(); i++) {
            if (secondMatch.getSecondMatchNo() == secondMatches.get(i).getSecondMatchNo()) {
                secondMatches.set(i, secondMatch);
            }
        }

        File file = new File(SECOND_MATCH_FILENAME);

        saveSecondMatches(file);
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

        saveSecondMatches(file);
    }

    // 쌍방호감 성사

    // 채팅방 오픈 여부

    // 포인트 차감

}
