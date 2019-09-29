package com.lessons.firebase.quotes.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lessons.firebase.quotes.R;

import java.lang.ref.WeakReference;

public class QuoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

     ImageView mImageQuote;
  TextView mBodyText;
    TextView mAuthorText;
    Button misLiked;
   WeakReference<LClickListener> lClickListenerWeakReference;

    public QuoteViewHolder(@NonNull View itemView, LClickListener lClickListener) {
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
            misLiked.setEnabled(false);
            Toast.makeText(view.getContext(), "IsLiked CLiked " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(view.getContext(), "Not Ne clicked " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
        }
        lClickListenerWeakReference.get().onPositionClicked(getAdapterPosition());
    }
}
