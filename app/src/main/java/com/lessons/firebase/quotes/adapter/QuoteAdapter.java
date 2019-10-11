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

import java.util.ArrayList;
import java.util.List;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteViewHolder> {

    private LClickListener lClickListener;
    private Context mContext;
    private List<QuoteData> mQuotesList;

    public QuoteAdapter(Context mContext, LClickListener lClickListener) {
        this.mContext = mContext;
        this.lClickListener = lClickListener;
    }

    public void setList(List<QuoteData> quotesList){
        this.mQuotesList = new ArrayList<>();
        this.mQuotesList.addAll(quotesList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.quote_items, parent, false);
        return new QuoteViewHolder(view, lClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteViewHolder holder, int position) {
        QuoteData quote = mQuotesList.get(position);
        holder.mBodyText.setText(quote.getQuote());
        holder.mAuthorText.setText(quote.getAuthor());
        if(quote.getIsLiked() == 1){
            holder.misLiked.setImageResource(R.drawable.ic_delete_black_24dp);
        }
        Picasso.get()
                .load(quote.getUrlImage())
                .into(holder.mImageQuote);
    }

    @Override
    public int getItemCount() {
        return mQuotesList.size();
    }

    public void deleteI(int position){
        mQuotesList.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public void deleteAllI(){
        mQuotesList.clear();
        notifyItemRangeChanged(0, mQuotesList.size());
        notifyDataSetChanged();
    }

    public void addI(QuoteData quoteData){
        mQuotesList.add(quoteData);
        notifyItemInserted(mQuotesList.size() - 1);
        notifyDataSetChanged();
    }

    public void swapList(List<QuoteData> list){
        mQuotesList.clear();
        mQuotesList.addAll(list);
        notifyDataSetChanged();
    }

}