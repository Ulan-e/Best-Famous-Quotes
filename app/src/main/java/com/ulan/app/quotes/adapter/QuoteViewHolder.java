package com.ulan.app.quotes.adapter;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ulan.app.quotes.R;
import com.ulan.app.quotes.ui.listeners.OnPositionClickListener;

import java.lang.ref.WeakReference;

/**
 * View Holder for QuoteAdapter
 */
public class QuoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView mImageQuote;
    TextView mBodyText;
    TextView mAuthorText;
    ImageButton misLiked;
    ImageButton mCopyText;

    WeakReference<OnPositionClickListener> mClickListenerWeakReference;

    public QuoteViewHolder(@NonNull View itemView, OnPositionClickListener clickListener) {
        super(itemView);
        mClickListenerWeakReference = new WeakReference<>(clickListener);
        mBodyText = itemView.findViewById(R.id.quote_text);
        mImageQuote = itemView.findViewById(R.id.quote_image);
        mAuthorText = itemView.findViewById(R.id.quote_author);
        misLiked = itemView.findViewById(R.id.id_liked_clicker);
        mCopyText = itemView.findViewById(R.id.id_copy_content);
        misLiked.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == misLiked.getId()) {
            misLiked.setImageResource(R.drawable.ic_star_liked_24dp);
            misLiked.setEnabled(false);
        }
        mClickListenerWeakReference.get().onPositionClicked(getAdapterPosition());
    }
}
