package com.lessons.firebase.quotes.data.database.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.lessons.firebase.quotes.data.QuoteData;

import java.util.List;

@Dao
public interface DaoLikedQuotes {

    @Query("SELECT * FROM liked_quotes")
    List<QuoteData> getLikedQuotes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long setToTable(QuoteData quoteData);

    @Delete
    void deleteQuote(QuoteData quoteData);

    @Query("DELETE FROM liked_quotes")
    void deleteAll();

}
