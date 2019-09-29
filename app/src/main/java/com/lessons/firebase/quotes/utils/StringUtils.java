package com.lessons.firebase.quotes.utils;

public class StringUtils {

    public static String exchangeSrtings(String data){
        if(data.contains(",")){
            String[] author = data.split(",") ;
            String lastName = author[0];
            String firstName = author[1];
            return firstName + " " + lastName;
        }else {
            return data;
        }


    }
}
