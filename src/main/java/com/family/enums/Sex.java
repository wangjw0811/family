package com.family.enums;

import lombok.Getter;

@Getter
public enum  Sex implements IndexEnum {
    MEN("男",1),
    WOMEN("女",0),
    ;
    private Integer index;
    private String message;
    Sex(String message,Integer index) {
        this.index = index;
        this.message = message;
    }
}
