package com.romanceResearcher.repository;

import com.romanceResearcher.domain.User;
import com.romanceResearcher.myio.MyObjectOutputStream;

import java.io.*;
import java.util.*;

public class UserRepository {



    private static final String FILENAME = "src/main/java/com/romanceResearcher/db/userRe.dat"; // DB 파일
    private List<User> users = new ArrayList<>(); // 모든 회원 유저
    private HashMap<String, String> userIdPw = new HashMap<>();


    private static UserRepository instance;

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    // UserRepository 생성자
    // 파일에서 읽어와서 users 에 저장
    private UserRepository() {

        File file = new File(FILENAME);

        if (!file.exists()) {
            User sample01 = new User("test01", "2015-10-15", "testId01", "pwd01", "email01", "010-1111-1111", 'M');
            User sample02 = new User("test02", "2014-09-25", "testId02", "pwd02", "email02", "010-2222-1111", 'M');
            User sample03 = new User("test03", "2016-11-08", "testId03", "pwd03", "email03", "010-3333-1111", 'M');

            User sample04 = new User("test04", "2012-10-14", "testId04", "pwd04", "email04", "010-4444-1111", 'W');
            User sample05 = new User("test05", "2013-02-27", "testId05", "pwd05", "email05", "010-5555-1111", 'W');
            User sample06 = new User("test06", "2010-12-08", "testId06", "pwd06", "email06", "010-6666-1111", 'W');

            users.add(sample01);
            users.add(sample02);
            users.add(sample03);
            users.add(sample04);
            users.add(sample05);
            users.add(sample06);

            saveUser(file);
        }
        loadUser(file);

    }

    // userRe 파일에서 데이터 꺼내오기
    public void loadUser(File file) {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                User user = (User) ois.readObject();
                users.add(user);
                userIdPw.put(user.getId(), user.getPwd());
            }

        } catch (EOFException e) {
            System.out.println("파일을 모두 로딩했습니다.");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // userRe 파일에 데이터 저장하기
    public void saveUser(File file) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for (User user : users) {
                oos.writeObject(user);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 회원 가입 (User Add)
    public int addUser(User user) {

        int result = 0;
        user.setUserNo(getUserNo());

        try (MyObjectOutputStream moo = new MyObjectOutputStream(new FileOutputStream(FILENAME, true))) {
            if (userIdPw.containsKey(user.getId())) { // ID 중복 체크
                return 0;
            }
            moo.writeObject(user);
            users.add(user);
            userIdPw.put(user.getId(), user.getPwd());
            result = 1;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    // userId set 만 가져오기
    public Set<String> getIds() {
        return userIdPw.keySet();
    }

    // 회원 정보 수정 (User Update)
    public int updateUser(User user) {

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(user.getId())) {
                users.set(i, user);

                File file = new File(FILENAME);
                saveUser(file);
                return 1;
            }
        }
        return 0;
    }

    // 회원 탈퇴 (User delete)
    public int deleteUser(User user) {

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(user.getId())) {
                users.remove(i);
                userIdPw.remove(user.getId());

                File file = new File(FILENAME);
                saveUser(file);
                return 1;
            }
        }
        return 0;
    }

    // User 를 추가할 때의 마지막 userNo 가 무엇인지 알아온다.
    public long getUserNo() {
        return users.get(users.size() - 1).getUserNo() + 1;
    }

    // 유저 번호로 User 객체 조회
    public Optional<User> findByNo(int no) {

        for (User user : users) {
            if (user.getUserNo() == no) {
                return Optional.of(user);
            }
        }
        return Optional.of(null);
    }

    // 유저 목록 조회
    public List<User> findAll() {
        return users;
    }

    // 유저 로그인 정보 맵 조회
    public Map<String, String> findLoginInfo() {
        return userIdPw;
    }

    // 유저를 id로 불러오기
    public Optional<User> findById(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return Optional.of(user);
            }
        }
        return Optional.of(null);
    }



}
