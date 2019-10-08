package com.lessons.firebase.quotes.data.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.lessons.firebase.quotes.data.QuoteData;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
public interface DaoLikedQuotes {

    @Query("SELECT * FROM liked_quotes")
    List<QuoteData> getLikedQuotes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void setToTable(QuoteData quoteData);

    @Delete
    void deleteQuote(QuoteData quoteData);

    @Query("DELETE FROM liked_quotes")
    void deleteAll();

    @Update
    void updateAll(List<QuoteData> quoteData);

}
