package com.lessons.firebase.quotes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lessons.firebase.quotes.R;
import com.lessons.firebase.quotes.data.QuoteData;
import com.lessons.firebase.quotes.utils.StringUtils;
import com.lessons.firebase.quotes.utils.listeners.OnPositionClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteViewHolder> {

    private OnPositionClickListener mOnPositionClickListener;
    private Context mContext;
    private List<QuoteData> mQuotesList;

    public QuoteAdapter(Context context, List<QuoteData> quotesList,
                        OnPositionClickListener clickListener) {
        this.mContext = context;
        this.mOnPositionClickListener = clickListener;
        this.mQuotesList = quotesList;
    }

    @NonNull
    @Override
    public QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.quote_items, parent, false);
        return new QuoteViewHolder(view, mOnPositionClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteViewHolder holder, int position){
        QuoteData quote = mQuotesList.get(position);
        holder.mBodyText.setText(quote.getQuote());
        holder.mAuthorText.setText(quote.getAuthor());
        String copiedText = quote.getQuote() + "\n" + quote.getAuthor();
        holder.mCopyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringUtils.copyToClipBoard(mContext, copiedText);
                Toast.makeText(mContext, "Quote copied to Clipboard", Toast.LENGTH_SHORT).show();
            }
        });
        if(quote.getIsLiked() == 1){
            holder.misLiked.setEnabled(true);
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

    // Delete all quotes from StarredFragment List
    public void deleteLikedQuotes(){
        mQuotesList.clear();
        notifyItemRangeChanged(0, mQuotesList.size());
        notifyDataSetChanged();
    }

    // Add quote to StarredFragment List
    public void addQuote(QuoteData quoteData){
        mQuotesList.add(quoteData);
        notifyItemInserted(mQuotesList.size() - 1);
    }

}