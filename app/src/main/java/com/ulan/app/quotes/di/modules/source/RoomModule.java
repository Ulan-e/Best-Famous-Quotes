package com.ulan.app.quotes.di.modules.source;

import android.content.Context;

import androidx.room.Room;

import com.ulan.app.quotes.data.database.DaoStarredQuotes;
import com.ulan.app.quotes.data.database.DaoQuotes;
import com.ulan.app.quotes.data.database.QuotesDatabase;
import com.ulan.app.quotes.di.modules.AppModule;
import com.ulan.app.quotes.di.scopes.AppScope;

import dagger.Module;
import dagger.Provides;

@AppScope
@Module(includes = {AppModule.class})
public class RoomModule {

    @AppScope
    @Provides
    public QuotesDatabase database(Context context){
        return Room.databaseBuilder(context, QuotesDatabase.class, "data.db")
                .createFromAsset("data.db")
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
    public DaoStarredQuotes daoQuotesLiked(QuotesDatabase database){
        return database.getLikedQuotes();
    }
}

