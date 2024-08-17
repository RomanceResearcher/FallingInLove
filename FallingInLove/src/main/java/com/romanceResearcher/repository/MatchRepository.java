package com.romanceResearcher.repository;

import com.romanceResearcher.domain.FirstMatch;
import com.romanceResearcher.domain.User;

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
    private UserRepository() {

        File file = new File(FILENAME);

        if (!file.exists()) {
            FirstMatch sample01 = new FirstMatch(1,new User(),new User(), LocalTime.now());
            FirstMatch sample02 = new FirstMatch(2,new User(),new User(), LocalTime.now());
            FirstMatch sample03 = new FirstMatch(3,new User(),new User(), LocalTime.now());

            firstMatches.add(sample01);
            firstMatches.add(sample02);
            firstMatches.add(sample03);

            saveUser(file);
        }
        loadUser(file);

    }

    // firstMatches 파일에서 데이터 꺼내오기
    public void loadUser(File file) {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                FirstMatch firstMatch = (FirstMatch) ois.readObject();
            }

        } catch (EOFException e) {
            System.out.println("파일을 모두 로딩했습니다.");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // firstMatches 파일에 데이터 저장하기
    public void saveUser(File file) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for (FirstMatch firstMatch : firstMatches) {
                oos.writeObject(firstMatch);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




    public MatchRepository(File) {}


    // 변경된 상대 유저의 정보를 FirstMatch 리스트에 저장
    public static void acceptFlagUpdate(boolean likeSignal) {

    }







    // acceptFlag -> true
    // sRepo (인자)

}
