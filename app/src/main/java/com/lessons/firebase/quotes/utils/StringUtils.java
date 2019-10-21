package com.lessons.firebase.quotes.utils;

import android.util.Log;

import com.lessons.firebase.quotes.data.QuoteData;
import com.lessons.firebase.quotes.data.network.pojo.Photo;
import com.lessons.firebase.quotes.data.network.pojo.Quote;

import java.util.ArrayList;
import java.util.List;

import static com.lessons.firebase.quotes.utils.Constants.TAG_OTHER;

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

    public static List<QuoteData> mergeQuotesPhotos(List<Photo> photosList, List<QuoteData> quotesList){
        for(int i =0; i < photosList.size(); i++){
            QuoteData quoteData = quotesList.get(i);
            quoteData.setUrlImage(photosList.get(i).getWebformatURL());
        }
        Log.d(TAG_OTHER, "mergeQuotesPhotos quotes " + String.valueOf(quotesList.size()));
        Log.d(TAG_OTHER, "mergeQuotesPhotos photos " + String.valueOf(photosList.size()));
        return  quotesList;
    }

    public static List<QuoteData> populateQuotes(List<Quote> listOfQuotes){
        int size = 25;
        List<QuoteData> resultList = new ArrayList<>();
        for(int i=0; i < size; i++){
            QuoteData quoteData = new QuoteData();
            quoteData.setId(i);
            quoteData.setQuote(listOfQuotes.get(i).getBody());
            quoteData.setAuthor(listOfQuotes.get(i).getAuthor());
            quoteData.setIsLiked(0);
            resultList.add(quoteData);
        }
        Log.d(TAG_OTHER, "populateQuotes: List of Quotes " + String.valueOf(resultList.size()) );
        return resultList;
    }
}
