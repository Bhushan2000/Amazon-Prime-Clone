package com.example.amazonprimeclone;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.amazonprimeclone.HomeData.adapter.GenreAdapter;
import com.example.amazonprimeclone.HomeData.model.Genre;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FindFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FindFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FindFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FindFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FindFragment newInstance(String param1, String param2) {
        FindFragment fragment = new FindFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    RecyclerView languagesRecycler, genreRecycler;


    androidx.appcompat.widget.SearchView searchView;
    ImageView mic;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        searchView = view.findViewById(R.id.searchView);
        mic = view.findViewById(R.id.mic);


        languagesRecycler = view.findViewById(R.id.languagesRecycler);
        genreRecycler = view.findViewById(R.id.genreRecycler);

        setUpGenreRecycler();
        setUpLanguageRecycler();


        // Detect SearchView icon clicks
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mic.setVisibility(View.GONE);
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {

                mic.setVisibility(View.VISIBLE);
                return false;
            }
        });


        return view;

    }

    private void setUpLanguageRecycler() {
        List<GenreOrLanguages> genreOrLanguages = new ArrayList<>();
        genreOrLanguages.add(new GenreOrLanguages("English"));
        genreOrLanguages.add(new GenreOrLanguages("Hindi"));
        genreOrLanguages.add(new GenreOrLanguages("Tamil"));
        genreOrLanguages.add(new GenreOrLanguages("Urdu"));
        genreOrLanguages.add(new GenreOrLanguages("Arabic"));
        genreOrLanguages.add(new GenreOrLanguages("Marathi"));
        genreOrLanguages.add(new GenreOrLanguages("Spanish"));
        genreOrLanguages.add(new GenreOrLanguages("French"));
        genreOrLanguages.add(new GenreOrLanguages("Dutch"));

        languagesRecycler.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        /* for smooth scrolling */
        languagesRecycler.setNestedScrollingEnabled(false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL);
        languagesRecycler.addItemDecoration(dividerItemDecoration);


        LanguagesAdapter languagesAdapter = new LanguagesAdapter(genreOrLanguages);
        languagesRecycler.setAdapter(languagesAdapter);
    }

    private void setUpGenreRecycler() {

        List<GenreOrLanguages> genreOrLanguages = new ArrayList<>();
        genreOrLanguages.add(new GenreOrLanguages("Latest movies"));
        genreOrLanguages.add(new GenreOrLanguages("Top Movies"));
        genreOrLanguages.add(new GenreOrLanguages("TV shows we think you'll like"));
        genreOrLanguages.add(new GenreOrLanguages("Amazon Original series"));
        genreOrLanguages.add(new GenreOrLanguages("Thriller movies"));
        genreOrLanguages.add(new GenreOrLanguages("Recommended movies"));
        genreOrLanguages.add(new GenreOrLanguages("Romantic movies"));
        genreOrLanguages.add(new GenreOrLanguages("Movies in hindi"));
        genreOrLanguages.add(new GenreOrLanguages("Dark Romance"));
        genreOrLanguages.add(new GenreOrLanguages("Action and adventure"));
        genreOrLanguages.add(new GenreOrLanguages("Drama"));
        genreOrLanguages.add(new GenreOrLanguages("comedy"));
        genreOrLanguages.add(new GenreOrLanguages("Animation"));


        genreRecycler.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        /* for smooth scrolling */
        genreRecycler.setNestedScrollingEnabled(false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL);
        languagesRecycler.addItemDecoration(dividerItemDecoration);
        GenreListAdapter genreListAdapter = new GenreListAdapter(genreOrLanguages);
        genreRecycler.setAdapter(genreListAdapter);
    }


}