package com.lessons.firebase.quotes.adapter;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lessons.firebase.quotes.R;
import com.lessons.firebase.quotes.utils.listeners.OnPositionClickListener;

import java.lang.ref.WeakReference;

public class QuoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView mImageQuote;
    TextView mBodyText;
    TextView mAuthorText;
    ImageButton misLiked;
    WeakReference<OnPositionClickListener> lClickListenerWeakReference;

    public QuoteViewHolder(@NonNull View itemView, OnPositionClickListener lClickListener) {
        super(itemView);
        lClickListenerWeakReference = new WeakReference<>(lClickListener);
        mBodyText = itemView.findViewById(R.id.quote_text);
        mImageQuote = itemView.findViewById(R.id.quote_image);
        mAuthorText = itemView.findViewById(R.id.quote_author);
        misLiked = itemView.findViewById(R.id.id_liked_clicker);
        misLiked.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
            if(view.getId() == misLiked.getId()){
                misLiked.setImageResource(R.drawable.ic_star_black_24dp);
            }else {
                Toast.makeText(view.getContext(), "Error to added to Liked List", Toast.LENGTH_SHORT).show();
            }
            misLiked.setEnabled(false);

        lClickListenerWeakReference.get().onPositionClicked(getAdapterPosition());
    }
}
