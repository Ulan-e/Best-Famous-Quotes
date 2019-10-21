package com.lessons.firebase.quotes.data.database;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoQuotes {

    @Query("SELECT * FROM quotes1 ORDER BY RANDOM() LIMIT 25")
    List<QuoteEntity> getAllQuotes();

    @Query("SELECT * FROM quotes1 ORDER BY RANDOM() LIMIT 1")
    QuoteEntity getQuote();

}
