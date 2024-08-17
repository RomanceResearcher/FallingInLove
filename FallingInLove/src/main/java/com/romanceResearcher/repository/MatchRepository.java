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
    private UserRepository() {

        File file = new File(FILENAME);

        if (!file.exists()) {
            FirstMatch sample01 = new FirstMatch(1,new User(),new User(), LocalTime.now());
            FirstMatch sample02 = new FirstMatch(2,new User(),new User(), LocalTime.now());
            FirstMatch sample03 = new FirstMatch(3,new User(),new User(), LocalTime.now());

            firstMatches.add(sample01);
            firstMatches.add(sample02);
            firstMatches.add(sample03);

            savefirstMatches(file);
        }
        loadfirstMatches(file);

    }

    // firstMatches 파일에서 데이터 꺼내오기
    public void loadfirstMatches(File file) {

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
    public void savefirstMatches(File file) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for (FirstMatch firstMatch : firstMatches) {
                oos.writeObject(firstMatch);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 매치 기록 추가 (firstMatch Add)
    public void addFirstMatch(User randomDatingPartner) {

        FirstMatch firstMatch = new FirstMatch(4, randomDatingPartner, new User()/*나의 정보*/, LocalTime.now());
        try (MyObjectOutputStream moo = new MyObjectOutputStream(new FileOutputStream(FILENAME, true))) {

            moo.writeObject(randomDatingPartner);
            firstMatches.add(firstMatch);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 내가 좋아요 한 상대가 소개팅에 동의한다면 secondMatches 리스트에 기록 저장
    public void succeseMatching() {
        SecondMatchRepository.addSecondMatch();
    }

    // 내가 호감보낸 상대 목록 리스트 생성 후 출력
    public void findmyFirstmatches(User user) {
        // FirstMatch list를 가져온다.
        List<FirstMatch> myFirstMatches = new ArrayList<>(); // 내가 호감보낸 상대 리스트 생성
        // 매개변수와 User from 가 같으면 myFirstMatches 리스트에 담는다.
        for (int i = 0; i < firstMatches.size(); i++) {
            if (user/*사용자*/.equals(firstMatches.get(i).getFrom())) {
                myFirstMatches.add(firstMatches.get(i));
            }
        }
        // 조회된 리스트 출력
        System.out.println(myFirstMatches);
    }





    // acceptFlag -> true
    // sRepo (인자)

}
