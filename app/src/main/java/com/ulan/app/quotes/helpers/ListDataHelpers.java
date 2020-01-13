package com.ulan.app.quotes.helpers;

import android.util.Log;

import com.ulan.app.quotes.data.QuoteModel;
import com.ulan.app.quotes.data.network.model.Photo;
import com.ulan.app.quotes.data.network.model.Quote;

import java.util.ArrayList;
import java.util.List;

import static com.ulan.app.quotes.helpers.Constants.TAG_OTHER;

/**
 * Class handle list of requesting data from network:
 * 1) merge quotes and photos
 * 2) return new List of quote data
 * 3) generate author name
 */

public class ListDataHelpers {

    // generate author name ('Gates, Bill' -> 'Bill Gates')
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

    // merge quotes text with photos url
    public static List<QuoteModel> mergeQuotesPhotos(List<Photo> photos, List<QuoteModel> quotes){
        for(int i =0; i < photos.size(); i++){
            QuoteModel quoteData = quotes.get(i);
            quoteData.setUrlImage(photos.get(i).getWebformatURL());
        }
        Log.d(TAG_OTHER, "mergeQuotesPhotos quotes " + String.valueOf(quotes.size()));
        Log.d(TAG_OTHER, "mergeQuotesPhotos photos " + String.valueOf(photos.size()));
        return  quotes;
    }

    // populate new list of quotedata
    public static List<QuoteModel> populateQuotes(List<Quote> listOfQuotes){
        int size = 25;
        List<QuoteModel> resultList = new ArrayList<>();
        for(int i=0; i < size; i++){
            QuoteModel quoteData = new QuoteModel();
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
