package com.lessons.firebase.quotes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lessons.firebase.quotes.data.QuoteData;
import com.lessons.firebase.quotes.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteViewHolder> {

    private LClickListener lClickListener;
    private Context mContext;
    private List<QuoteData> mQuotesList;

    public QuoteAdapter(Context mContext, List<QuoteData> mQuotesList, LClickListener lClickListener) {
        this.mContext = mContext;
        this.mQuotesList = mQuotesList;
        this.lClickListener = lClickListener;
    }

    @NonNull
    @Override
    public QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.quotes_items, parent, false);
        return new QuoteViewHolder(view, lClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteViewHolder holder, int position) {
        QuoteData quote = mQuotesList.get(position);
        holder.mBodyText.setText(quote.getQuote());
        holder.mAuthorText.setText(quote.getAuthor());
        Picasso.get()
                .load(quote.getUrlImage())
                .into(holder.mImageQuote);
    }

    @Override
    public int getItemCount() {
        return mQuotesList.size();
    }

}
