package com.romanceResearcher.service;

import java.util.List;

public class PictureService {

    private List<String> pictures;

    public PictureService(List<String> pictures) {
        this.pictures = pictures;
    }

    // 사진 추가
    public void addPicture(String picture) {
        pictures.add(picture);
    }

    // 사진 수정
    public void updatePicture(int index,String picture) {
        pictures.set(index,picture);
    }

    // 사진 삭제
    public void deletePicture(int index) {
        pictures.remove(index);
    }

    public List<String> getPictures() {
        return pictures;
    }

    public boolean hasPictures() {
        return pictures.isEmpty();
    }
}
