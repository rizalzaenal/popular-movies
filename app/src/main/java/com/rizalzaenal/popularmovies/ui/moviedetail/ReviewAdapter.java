package com.rizalzaenal.popularmovies.ui.moviedetail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.rizalzaenal.popularmovies.R;
import com.rizalzaenal.popularmovies.data.model.Review;
import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {
    private List<Review> reviews;
    private OnItemClicked listener;

    public ReviewAdapter(OnItemClicked listener) {
        this.reviews = new ArrayList<Review>();
        this.listener = listener;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_review, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        holder.bind(reviews.get(position));
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }


    class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView reviewer;
        TextView tv_review;

        ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            reviewer = itemView.findViewById(R.id.tv_reviewer);
            tv_review = itemView.findViewById(R.id.tv_review);
        }

        void bind(Review review){
            reviewer.setText(review.getAuthor());
            tv_review.setText(review.getContent());
            itemView.setOnClickListener(v -> {
                listener.doOnClick(review);
            });
        }
    }

    interface OnItemClicked {
        void doOnClick(Review review);
    }
}