package com.ulan.app.quotes.data.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ulan.app.quotes.data.QuoteModel;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface DaoStarredQuotes {

    @Query("SELECT * FROM liked_quotes")
    Flowable<List<QuoteModel>> getLikedQuotes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void setToTable(QuoteModel quoteData);

    @Delete
    void deleteQuote(QuoteModel quoteData);

    @Query("DELETE FROM liked_quotes")
    void deleteAll();
}
