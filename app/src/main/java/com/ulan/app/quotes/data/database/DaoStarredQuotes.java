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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(QuoteModel quoteData);

    @Query("SELECT * FROM liked_quotes")
    Flowable<List<QuoteModel>> getAll();

    @Delete
    void delete(QuoteModel quoteData);

    @Query("DELETE FROM liked_quotes")
    void deleteAll();
}