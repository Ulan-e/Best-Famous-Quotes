package com.ulan.app.quotes.adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ulan.app.quotes.R;
import com.ulan.app.quotes.data.QuoteModel;
import com.ulan.app.quotes.ui.listeners.OnPositionClickListener;
import com.squareup.picasso.Picasso;
import java.util.List;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteViewHolder> {

    private OnPositionClickListener mOnPositionClickListener;
    private Context mContext;
    private List<QuoteModel> mQuotes;

    public QuoteAdapter(Context context, List<QuoteModel> quotes,
                        OnPositionClickListener clickListener) {
        this.mContext = context;
        this.mOnPositionClickListener = clickListener;
        this.mQuotes = quotes;
    }

    @NonNull
    @Override
    public QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.quote_items, parent, false);
        return new QuoteViewHolder(view, mOnPositionClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteViewHolder holder, int position) {
        QuoteModel quote = mQuotes.get(position);
        holder.mBodyText.setText(quote.getQuote());
        holder.mAuthorText.setText(quote.getAuthor());
        String copiedText = quote.getQuote() + "\n" + quote.getAuthor();
        holder.mCopyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                copyToClipBoard(mContext, copiedText);
                Toast.makeText(mContext, mContext.getResources().getText(R.string.copyClipboard), Toast.LENGTH_SHORT).show();
            }
        });
        if (quote.getIsLiked() == 1) {
            holder.misLiked.setEnabled(true);
            holder.misLiked.setImageResource(R.drawable.ic_delete_black_24dp);
        }
        Picasso.get()
                .load(quote.getUrlImage())
                .into(holder.mImageQuote);
    }

    @Override
    public int getItemCount() {
        return mQuotes.size();
    }

    // копировать текст
    private void copyToClipBoard(Context context, String text) {
        ClipboardManager manager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("copyText", text);
        manager.setPrimaryClip(clipData);
    }

    // добавить в избранные
    public void addToStarred(QuoteModel quoteData) {
        mQuotes.add(quoteData);
        notifyItemInserted(mQuotes.size() - 1);
    }

    // удалить из избранных
    public void removeFromStarred() {
        mQuotes.clear();
        notifyItemRangeChanged(0, mQuotes.size());
        notifyDataSetChanged();
    }
}