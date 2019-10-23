package com.lessons.firebase.quotes.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;

import com.lessons.firebase.quotes.data.QuoteData;
import com.lessons.firebase.quotes.data.network.pojo.Photo;
import com.lessons.firebase.quotes.data.network.pojo.Quote;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.lessons.firebase.quotes.utils.Constants.TAG_OTHER;

public class StringUtils {

    public static String generateAuthorName(String data){
        if(data.contains(",")){
            String[] author = data.split(",") ;
            String lastName = author[0];
            String firstName = author[1];
            return firstName + " " + lastName;
        }else {
            return data;
        }
    }

    public static void copyToClipBoard(Context context, String text){
        ClipboardManager manager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("copyText", text);
        manager.setPrimaryClip(clipData);
    }

    public static List<QuoteData> mergeQuotesPhotos(List<Photo> photos, List<QuoteData> quotes){
        for(int i =0; i < photos.size(); i++){
            QuoteData quoteData = quotes.get(i);
            quoteData.setUrlImage(photos.get(i).getWebformatURL());
        }
        Log.d(TAG_OTHER, "mergeQuotesPhotos quotes " + String.valueOf(quotes.size()));
        Log.d(TAG_OTHER, "mergeQuotesPhotos photos " + String.valueOf(photos.size()));
        return  quotes;
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

    public static String getRandomWord(){
        String[] strings = {"awesome", " ", "scenery", "photos", "home", "world", "architecture", " ", " "};
        Random random = new Random();
        int i = random.nextInt(strings.length);
        return strings[i];
    }

    public static String getLifeWord(){
        String[] strings = {"nature", "world", " ", "child",  "" };
        Random random = new Random();
        int i = random.nextInt(strings.length);
        return strings[i];
    }

    public static String getLoveWord(){
        String[] strings = {"couples", "family", "young", "", " "};
        Random random = new Random();
        int i = random.nextInt(strings.length);
        return strings[i];
    }

    public static String getWisdomWord(){
        String[] strings = {"book", "knowledge", "young", " ", " ", " "};
        Random random = new Random();
        int i = random.nextInt(strings.length);
        return strings[i];
    }

    public static String getMotivationalWord(){
        String[] strings = {"quotes", "word", "nature", "success", " ", " "};
        Random random = new Random();
        int i = random.nextInt(strings.length);
        return strings[i];
    }


    public static String getFunnyWord(){
        String[] strings = {"humor", "world","peoples", " ", " "};
        Random random = new Random();
        int i = random.nextInt(strings.length);
        return strings[i];
    }

    public static boolean getRandBoolean(){
        Random random = new Random();
        return random.nextBoolean();
    }
}
