package com.lessons.firebase.quotes.data.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "quotes1")
public class QuoteEntity {

    @PrimaryKey
    @ColumnInfo(name = "Quote_ID")
    public int Quote_ID;

    @ColumnInfo(name = "Name")
    public String Name;

    @ColumnInfo(name = "Quote_Category")
    public String Quote_Category;

    @ColumnInfo(name = "Quote")
    public String Quote;

    public int getQuote_ID() {
        return Quote_ID;
    }

    public void setQuote_ID(int quote_ID) {
        Quote_ID = quote_ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getQuote_Category() {
        return Quote_Category;
    }

    public void setQuote_Category(String quote_Category) {
        Quote_Category = quote_Category;
    }

    public String getQuote() {
        return Quote;
    }

    public void setQuote(String quote) {
        Quote = quote;
    }
}
