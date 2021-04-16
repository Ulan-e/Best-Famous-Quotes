package com.ulan.app.quotes.data.database;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import com.ulan.app.quotes.data.QuoteModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.Flowable;
import io.reactivex.subscribers.TestSubscriber;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class DaoQuotesTest {

    private QuotesDatabase database;
    private DaoQuotes quotes;
    private DaoStarredQuotes starredQuotes;

    @Before
    public void setUp() {
        database = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getInstrumentation().getContext(),
                QuotesDatabase.class)
                .allowMainThreadQueries()
                .build();
        quotes = database.getQuotesDao();
        starredQuotes = database.getLikedQuotesDao();
    }

    @After
    public void tearDown() {
        database.close();
    }

    @Test
    public void getWhenQuoteNullReturned() {
        assertNull(quotes.get());
    }

    @Test
    public void insertWhenInsertedQuoteNotNullReturned(){
        List<QuoteModel> quotes = DaoHelper.createQuotes(1);
        starredQuotes.insert(quotes.get(0));
        assertNotNull(starredQuotes.getAll());
    }

    @Test
    public void getAllWhenGetAllQuotesReturnedWithoutError(){
        List<QuoteModel> list = DaoHelper.createQuotes(3);
        for(int i = 0; i < 3; i++){
            starredQuotes.insert(list.get(i));
        }
        starredQuotes.getAll().test().assertNoErrors();
        starredQuotes.getAll().test().assertComplete();
    }

    @Test
    public void getAllWhenGetAllQuotesReturnedNotEmpty() throws InterruptedException {
        ArrayList<QuoteModel> list = DaoHelper.createQuotes(2);
        for (int i = 0; i < list.size(); i++) {
            starredQuotes.insert(list.get(i));
        }
        Flowable<List<QuoteModel>> flowableQuotes = starredQuotes.getAll();
        flowableQuotes.test().assertEmpty();
    }

    @Test
    public void deleteAllWhenDeleteAllReturnedEmpty(){
        List<QuoteModel> list = DaoHelper.createQuotes(3);
        for(int i =0; i < 3; i++){
            starredQuotes.insert(list.get(i));
        }
        starredQuotes.deleteAll();
        starredQuotes.getAll().test().assertNotComplete();
    }
}