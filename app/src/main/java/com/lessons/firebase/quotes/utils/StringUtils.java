package com.lessons.firebase.quotes.utils;

import com.lessons.firebase.quotes.data.database.QuoteEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public static ArrayList<QuoteEntity> getResultData(List<QuoteEntity> list){
        Collections.shuffle(list);
        int ourSize = 25;
        ArrayList<QuoteEntity> resultList = new ArrayList<>(list.subList(0,ourSize));
        return resultList;
    }
}
