package com.ulan.app.quotes.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.ulan.app.quotes.data.QuoteModel;

@Database(entities = {QuoteEntity.class, QuoteModel.class}, version = 1, exportSchema = false)
public abstract class QuotesDatabase extends RoomDatabase {

    public abstract DaoQuotes getQuotesDao();
    public abstract DaoStarredQuotes getLikedQuotesDao();
}