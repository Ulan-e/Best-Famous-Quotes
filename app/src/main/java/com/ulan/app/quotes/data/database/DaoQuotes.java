package com.ulan.app.quotes.data.database;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface DaoQuotes {

    @Query("SELECT * FROM quotes1 ORDER BY RANDOM() LIMIT 1")
    QuoteEntity get();
}