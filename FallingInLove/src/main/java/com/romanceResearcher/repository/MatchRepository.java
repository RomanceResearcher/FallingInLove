package com.romanceResearcher.repository;

import com.romanceResearcher.domain.FirstMatch;
import com.romanceResearcher.domain.User;
import com.romanceResearcher.myio.MyObjectOutputStream;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MatchRepository {

    // 매치 기록을 저장하는 리스트 선언 및 초기화
    private static final String FILENAME = "src/main/java/com/romanceResearcher/db/firstMatchRepo.dat";
    private final List<FirstMatch> firstMatches = new ArrayList<>();
    private static MatchRepository instance;

    public static MatchRepository getInstance() {
        if (instance == null) {
            instance = new MatchRepository();
        }
        return instance;
    }

    // MatchRepository 생성자
    // 파일에서 읽어와서 firstMatches 에 저장
    private MatchRepository() {

        File file = new File(FILENAME);

        if (!file.exists()) {
            List<FirstMatch> sampleData = new ArrayList<>();


            saveFirstMatches(file, sampleData);
        }
        loadfirstMatches(file);

    }

    // firstMatches 파일에서 데이터 꺼내오기
    public void loadfirstMatches(File file) {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                FirstMatch firstMatch = (FirstMatch) ois.readObject();
                firstMatches.add(firstMatch);
            }

        } catch (EOFException e) {
//            System.out.println("파일을 모두 로딩했습니다.");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // firstMatches 파일에 데이터 저장하기
    public void saveFirstMatches(File file, List<FirstMatch> firstMatchList) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for (FirstMatch firstMatch : firstMatchList) {
                oos.writeObject(firstMatch);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    // 매치 기록 추가 (firstMatch Add)
    public void addFirstMatch(User me, User randomDatingPartner) {

        FirstMatch firstMatch = new FirstMatch(getFirstMatchNo(), randomDatingPartner, me/*나의 정보*/, LocalTime.now());
        try (MyObjectOutputStream moo = new MyObjectOutputStream(new FileOutputStream(FILENAME, true))) {

            moo.writeObject(randomDatingPartner);
            firstMatches.add(firstMatch);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    // FirstMatch 리스트 반환
    public List<FirstMatch> findAllMatches() {
        return firstMatches;
    }

    public void deleteFirstMatch(FirstMatch firstMatch) {

        for (FirstMatch firstMatch1 : firstMatches) {
            if (firstMatch1.getFirstMatchNo() == firstMatch.getFirstMatchNo()) {
                firstMatches.remove(firstMatch1);
            }
        }

        File file = new File(FILENAME);

        saveFirstMatches(file, firstMatches);
    }

    public long getFirstMatchNo() {
        if(firstMatches.isEmpty()) {
            return 0;
        }
        return firstMatches.get(firstMatches.size() - 1).getFirstMatchNo() + 1;
    }

    public void saveFirstMatchList(List<FirstMatch> allMatches) {

        saveFirstMatches(new File(FILENAME), allMatches);
    }


    // acceptFlag -> true


    // sRepo (인자)

}
