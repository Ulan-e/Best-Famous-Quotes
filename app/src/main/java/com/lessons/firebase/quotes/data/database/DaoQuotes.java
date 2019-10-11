package com.lessons.firebase.quotes.data.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.lessons.firebase.quotes.data.QuoteData;
import com.lessons.firebase.quotes.data.database.QuoteEntity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
public interface DaoQuotes {

    @Query("SELECT * FROM quotes1 ORDER BY RANDOM() LIMIT 25")
    List<QuoteEntity> getAllQuotes();

    @Query("SELECT * FROM quotes1 ORDER BY RANDOM() LIMIT 1")
    QuoteEntity getQuote();

}
