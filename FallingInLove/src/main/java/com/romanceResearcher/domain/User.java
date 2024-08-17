package com.romanceResearcher.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class User implements Serializable {

    private long userNo; // 회원 번호
    private String name; // 회원 이름
    private LocalDate birth; // 생년월일
    private String id; // 회원 아이디
    private String pwd; // 회원 비밀번호
    private String email; // 회원 이메일
    private String hp; // 회원 전화번호
    private char gender; // 회원 성별
    private List<String> pictures; // 회원 사진 리스트
    private long point; // 회원 포인트


    public User() {
    }

    // 회원 가입 용도
    public User(String name, String birth, String id, String pwd, String email, String hp, char gender) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.name = name;
        this.birth = LocalDate.parse(birth, formatter);
        this.id = id;
        this.pwd = pwd;
        this.email = email;
        this.hp = hp;
        this.gender = gender;
        this.pictures = new ArrayList<>();
        this.point = 0;
    }

    public long getUserNo() {
        return userNo;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirth() {
        return birth;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public char getGender() {
        return gender;
    }


    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public long getPoint() {
        return point;
    }

    public void setPoint(long point) {
        this.point = point;
    }

    public void setUserNo(long userNo) {
        this.userNo = userNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userNo == user.userNo && gender == user.gender && point == user.point && Objects.equals(name, user.name) && Objects.equals(birth, user.birth) && Objects.equals(id, user.id) && Objects.equals(pwd, user.pwd) && Objects.equals(email, user.email) && Objects.equals(hp, user.hp) && Objects.equals(pictures, user.pictures);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userNo, name, birth, id, pwd, email, hp, gender, pictures, point);
    }

    @Override
    public String toString() {
        return "User{" +
                "userNo=" + userNo +
                ", name='" + name + '\'' +
                ", birth=" + birth +
                ", id='" + id + '\'' +
                ", pwd='" + pwd + '\'' +
                ", email='" + email + '\'' +
                ", hp='" + hp + '\'' +
                ", gender=" + gender +
                ", pictures=" + pictures +
                ", point=" + point +
                '}';
    }

}
