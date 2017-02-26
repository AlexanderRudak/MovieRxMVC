package bonosoft.rudak.movie.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

import bonosoft.rudak.movie.R;
import bonosoft.rudak.movie.models.Movie;

public class RecyclerMovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Movie> movies = new ArrayList<Movie>();
    OnItemClickListener itemClickListener;
    Picasso picasso;
    Context ctx;

    public RecyclerMovieAdapter(Context context, List<Movie> movies) {
        picasso = Picasso.with(context);
        this.movies = movies;
        ctx = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ItemViewHolder holder = (ItemViewHolder) viewHolder;
        holder.posterUrl.setScaleType(ImageView.ScaleType.CENTER_CROP);
        picasso.load(movies.get(position).getPosterPath()).into(holder.posterUrl);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView posterUrl;

        public ItemViewHolder(View view) {
            super(view);
            posterUrl = (ImageView) view.findViewById(R.id.iv_item_movie_poster);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            if (itemClickListener != null) {
                itemClickListener.onItemClick(v, pos);
            }
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view , int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

}
