package com.ulan.app.quotes.data.database;

import com.ulan.app.quotes.data.QuoteModel;
import java.util.ArrayList;
import java.util.List;
import static com.ulan.app.quotes.data.database.DaoDataHelper.randomInt;
import static com.ulan.app.quotes.data.database.DaoDataHelper.randomUuid;

public class DaoHelper {

    public static ArrayList<QuoteModel> createQuotes(int size) {
        ArrayList<QuoteModel> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(createQuote());
        }
        return list;
    }

    public static QuoteModel createQuote() {
        return new QuoteModel(
                randomInt(),
                randomUuid().toString(),
                randomUuid().toString(),
                randomUuid().toString(),
                randomInt()
        );
    }
}