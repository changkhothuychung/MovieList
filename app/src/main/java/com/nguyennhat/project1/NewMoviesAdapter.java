package com.nguyennhat.project1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewMoviesAdapter extends RecyclerView.Adapter<NewMoviesAdapter.MovieHolder> {
    private List<NewMovie> originalList;
    private List<NewMovie> listFilter;
    private LayoutInflater inflater;
    private ClickListener clickListener;
    private boolean isGridMode;
    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_movie,parent,false);
        return new MovieHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        if (isGridMode) {
            holder.gridcontainer.setVisibility(View.VISIBLE);
            holder.listcontainer.setVisibility(View.GONE);
            NewMovie movie = originalList.get(position);
            holder.moviegrid.setText(movie.getMovieName());
            holder.imgViewGrid.setImageResource(movie.getImgView());
        }
        else{
            holder.listcontainer.setVisibility(View.VISIBLE);
            holder.gridcontainer.setVisibility(View.GONE);
            NewMovie movie = originalList.get(position);
            holder.moviename.setText(movie.getMovieName());
            holder.rating.setText(movie.getRating());
            holder.releaseDate.setText(movie.getReleaseDate());
            holder.overview.setText(movie.getOverview());
            holder.imgView.setImageResource(movie.getImgView());
        }


    }

    @Override
    public int getItemCount() {
        return listFilter.size();
    }

    public NewMoviesAdapter(List<NewMovie> list, Context context){
        this.originalList = list;
        this.listFilter = list;
        inflater = LayoutInflater.from(context);
    }

    public void setListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setMode(boolean isMode) {
        isGridMode = isMode;
        notifyDataSetChanged();
    }


    public class MovieHolder extends RecyclerView.ViewHolder {
        public TextView moviename, releaseDate, rating, overview, moviegrid;
        public ImageView imgView, imgViewGrid;
        public View gridcontainer, listcontainer ;

        public MovieHolder(View itemView) {
            super(itemView);
            gridcontainer = itemView.findViewById(R.id.containerGridMode);
            listcontainer = itemView.findViewById(R.id.containerListMode);
            moviename = (TextView) itemView.findViewById(R.id.MovieName);
            moviegrid = itemView.findViewById(R.id.MovieNameGrid);
            releaseDate = (TextView) itemView.findViewById(R.id.releasedate);
            rating = (TextView) itemView.findViewById(R.id.rating);
            overview = (TextView) itemView.findViewById(R.id.overview);
            imgView = (ImageView) itemView.findViewById(R.id.imgAvatar);
            imgViewGrid = (ImageView) itemView.findViewById(R.id.imgAvatarGrid);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onClick(getAdapterPosition());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    clickListener.onLongClick(getAdapterPosition());
                    return false;
                }
            });
        }
    }



    public Filter getFilter() {
        return new Filter() {
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                listFilter = (List<NewMovie>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<NewMovie> filteredResults = null;
                if (constraint.length() == 0) {
                    filteredResults = originalList;
                } else {
                    filteredResults = getFilteredResults(constraint.toString().toLowerCase());
                }

                FilterResults results = new FilterResults();
                results.values = filteredResults;

                return results;
            }
        };
    }

    protected List<NewMovie> getFilteredResults(String constraint) {
        List<NewMovie> results = new ArrayList<>();

        for (NewMovie item : originalList) {
            if (item.getMovieName().toLowerCase().contains(constraint)) {
                results.add(item);
            }
        }
        return results;
    }

}
