package com.example.amazonprimeclone;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GenreListAdapter extends RecyclerView.Adapter<GenreListAdapter.GenreVH> {
    private List<GenreOrLanguages> stringList;

    public GenreListAdapter(List<GenreOrLanguages> stringList) {
        this.stringList = stringList;
    }

    @NonNull
    @Override
    public GenreVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.genre_and_languages_layout,parent,false);
        return new GenreVH(v);

    }

    @Override
    public void onBindViewHolder(@NonNull GenreVH holder, int position) {
        holder.languages.setText(stringList.get(position).getLine());
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    class GenreVH extends RecyclerView.ViewHolder{
        TextView languages;


        public GenreVH(@NonNull View itemView) {
            super(itemView);
            languages = itemView.findViewById(R.id.language_or_genre);

        }
    }

}
