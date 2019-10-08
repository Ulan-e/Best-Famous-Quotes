package com.lessons.firebase.quotes.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.lessons.firebase.quotes.data.QuoteData;

@Database(entities = {QuoteEntity.class, QuoteData.class}, version = 1, exportSchema = false)
public abstract class QuotesDatabase extends RoomDatabase {
    public abstract DaoQuotes getQuotesDao();
    public abstract DaoLikedQuotes getLikedQuotes();
}
