package com.lessons.firebase.quotes.data.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.lessons.firebase.quotes.data.QuoteData;

import java.util.List;

@Dao
public interface DaoStarredQuotes {

    @Query("SELECT * FROM liked_quotes")
    List<QuoteData> getLikedQuotes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void setToTable(QuoteData quoteData);

    @Delete
    void deleteQuote(QuoteData quoteData);

    @Query("DELETE FROM liked_quotes")
    void deleteAll();
}
