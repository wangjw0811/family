package com.family.controller;

import org.hibernate.validator.internal.util.StringHelper;

public class Test1 {
    public static void main(String[] args) {

        String str = " 1";
        System.out.println(StringHelper.isNullOrEmptyString(str));

    }
}
