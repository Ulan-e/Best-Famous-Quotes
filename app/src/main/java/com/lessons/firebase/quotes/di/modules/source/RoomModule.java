package com.lessons.firebase.quotes.di.modules.source;

import android.content.Context;

import androidx.room.Room;

import com.lessons.firebase.quotes.data.database.DaoLikedQuotes;
import com.lessons.firebase.quotes.data.database.DaoQuotes;
import com.lessons.firebase.quotes.data.database.QuotesDatabase;
import com.lessons.firebase.quotes.di.modules.AppModule;
import com.lessons.firebase.quotes.di.scopes.AppScope;

import dagger.Module;
import dagger.Provides;

@AppScope
@Module(includes = {AppModule.class})
public class RoomModule {

    @AppScope
    @Provides
    public QuotesDatabase database(Context context){
        return Room.databaseBuilder(context, QuotesDatabase.class, "quotes.db")
                .allowMainThreadQueries()
                .build();
    }

    @AppScope
    @Provides
    public DaoQuotes daoQuotes(QuotesDatabase database){
        return database.getQuotesDao();
    }


    @AppScope
    @Provides
    public DaoLikedQuotes daoQuotesChoosed(QuotesDatabase database){
        return database.getLikedQuotes();
    }
}

