package com.lessons.firebase.quotes.data.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "quotes1")
public class QuoteEntity {

    @PrimaryKey
    @ColumnInfo(name = "quote_id")
    public int quoteId;

    @ColumnInfo(name = "author")
    public String author;

    @ColumnInfo(name = "category")
    public String category;

    @ColumnInfo(name = "quote")
    public String quote;

    public int getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(int quoteId) {
        this.quoteId = quoteId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }
}
