package com.tntran.start.adapter;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tntran.start.R;
import com.tntran.start.api.APIInterface;
import com.tntran.start.model.Result;

import java.util.List;

/**
 * Created by TTN on 6/17/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Result> data;

    private Context context;

    private IClickListener         listener;

    public RecyclerViewAdapter(Context ctx) {

        this.context = ctx;

    }

    public void setData(List<Result> movies) {

        this.data = movies;

        for (int i=0; i<this.data.size(); i++) {

            Result movie = this.data.get(i);

            String posterPath = movie.getPosterPath();

            String updatePosterPath = createImageURL(posterPath, APIInterface.POSTER_SIZE);

            movie.setPosterPath(updatePosterPath);

            String backdropPath = movie.getBackdropPath();

            String updateBackdropPath = createImageURL(backdropPath, APIInterface.BACKDROP);

            movie.setBackdropPath(updateBackdropPath);

        }

        notifyDataSetChanged();

    }
    public void setListener(IClickListener listener) {

        this.listener = listener;

    }
    public void clearData() {

        data.clear();

        notifyDataSetChanged();

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())

                .inflate( R.layout.row_item, parent, false);



        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Result movie = data.get(position);



        holder.title.setText(movie.getTitle());

        holder.overview.setText(movie.getOverview());




        if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){

            Glide.with(context).load(movie.getPosterPath())

                    .into(holder.poster);

        }

        else {

            Glide.with(context).load(movie.getPosterPath())

                    .into(holder.poster);

        }



    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public void setItemClickListener(IClickListener listener) {

        this.listener = listener;

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;

        public TextView title_original;

        public TextView overview;

        public ImageView poster;

        public ViewHolder(View itemView) {
            super( itemView );
            title = itemView.findViewById(R.id.movie_title_details);

            overview = itemView.findViewById(R.id.title_original);

            poster = itemView.findViewById(R.id.thumbnail);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(data.get(getAdapterPosition()));
        }
    }
    private String createImageURL(String path, String size) {

        if (path == null) return null;

        StringBuilder builder = new StringBuilder();

        builder.append(APIInterface.BASE_IMAGES_URL);

        builder.append(size);

        builder.append(path);

        return builder.toString();

    }
}
