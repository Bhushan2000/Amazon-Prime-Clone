package com.example.amazonprimeclone;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LanguagesAdapter extends RecyclerView.Adapter<LanguagesAdapter.LanguagesVH> {
    private List<GenreOrLanguages> stringList;

    public LanguagesAdapter(List<GenreOrLanguages> stringList) {
        this.stringList = stringList;
    }

    @NonNull
    @Override
    public LanguagesVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.genre_and_languages_layout,parent,false);
        return new LanguagesVH(v);

    }

    @Override
    public void onBindViewHolder(@NonNull LanguagesVH holder, int position) {
        holder.languages.setText(stringList.get(position).getLine());
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    class LanguagesVH extends RecyclerView.ViewHolder{
        TextView languages;


        public LanguagesVH(@NonNull View itemView) {
            super(itemView);
            languages = itemView.findViewById(R.id.language_or_genre);

        }
    }

}
