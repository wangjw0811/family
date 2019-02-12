package com.family.enums;

import lombok.Getter;

@Getter
public enum EducationalBackground implements IndexEnum {
    PRIMARY_SCHOOL("小学",1),
    MIDDLE_SCHOOL("初中",2),
    HIGH_SCHOOL("高中",3),
    JUNIOR_COLLEGE("大专",4),
    UNDERGRADUATE("本科",5),
    MASTER_DEGREE_CANDIDATE("硕士研究生",6),
    DOCTORAL_CANDIDATE("博士研究生",7),
    ;
    private Integer index;
    private String message;
    EducationalBackground(String message,Integer index) {
        this.index = index;
        this.message = message;
    }
}
