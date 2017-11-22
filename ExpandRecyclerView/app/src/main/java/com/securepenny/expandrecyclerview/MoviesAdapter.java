package com.securepenny.expandrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by R041708040 on 11/22/2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<Movie> moviesList;
    int mExpandedPosition =-1;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;
        public Button btnCencel,btnSave;
        public LinearLayout linearLayout;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);
            btnCencel = (Button) view.findViewById(R.id.btnCencel);
            btnSave = (Button) view.findViewById(R.id.btnSave);
            linearLayout = (LinearLayout) view.findViewById(R.id.expand);
        }
    }


    public MoviesAdapter(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final int pos = position;
        Movie movie = moviesList.get(position);
        holder.title.setText(movie.getTitle());
        holder.genre.setText(movie.getGenre());
        holder.year.setText(movie.getYear());


        //// for exapand  Start
        final boolean isExpanded = position==mExpandedPosition; // for exapand
        holder.linearLayout.setVisibility(isExpanded?View.VISIBLE:View.GONE); // for exapand
        holder.linearLayout.setActivated(isExpanded); // for exapand

        holder.year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mExpandedPosition = isExpanded ? -1:pos; // for exapand
                notifyDataSetChanged(); //// for exapand
            }
        }); // for exapand end
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
