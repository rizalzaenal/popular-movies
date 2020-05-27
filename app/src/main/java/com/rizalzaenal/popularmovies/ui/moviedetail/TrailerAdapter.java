package com.rizalzaenal.popularmovies.ui.moviedetail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.rizalzaenal.popularmovies.R;
import com.rizalzaenal.popularmovies.data.model.Trailer;
import java.util.ArrayList;
import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> {
    private List<Trailer> trailers;
    private OnItemClicked listener;

    public TrailerAdapter(OnItemClicked listener) {
        this.trailers = new ArrayList();
        this.listener = listener;
    }

    public void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_trailer, parent, false);
        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder holder, int position) {
        holder.bind(trailers.get(position));
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }


    class TrailerViewHolder extends RecyclerView.ViewHolder {
        TextView trailerName;

        public TrailerViewHolder(@NonNull View itemView) {
            super(itemView);
            trailerName = itemView.findViewById(R.id.tv_trailer_name);
        }

        void bind(Trailer trailer){
            trailerName.setText(trailer.getName());
            itemView.setOnClickListener(v -> {
                listener.doOnClick(trailer);
            });
        }
    }

    interface OnItemClicked {
        void doOnClick(Trailer trailer);
    }
}
