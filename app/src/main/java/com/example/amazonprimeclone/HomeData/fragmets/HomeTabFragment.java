package com.example.amazonprimeclone.HomeData.fragmets;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.example.amazonprimeclone.HomeData.adapter.GenreAdapter;
import com.example.amazonprimeclone.HomeData.adapter.TrendingAdapter;
import com.example.amazonprimeclone.HomeData.api.ApiClient;
import com.example.amazonprimeclone.HomeData.model.DiscoverResult;
import com.example.amazonprimeclone.HomeData.model.Genre;
import com.example.amazonprimeclone.HomeData.model.Movie;
import com.example.amazonprimeclone.HomeData.utillity.Constants;
import com.example.amazonprimeclone.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeTabFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeTabFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeTabFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeTabFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeTabFragment newInstance(String param1, String param2) {
        HomeTabFragment fragment = new HomeTabFragment();
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


    RelativeLayout relativeLayout;
    private RecyclerView mTrendingRecycler;

    private TrendingAdapter mTrendingAdapter;

    private List<Movie> mTrendingList;

//    private CardView mLoadingCard;

    private RecyclerView mGenreRecycler;

    private GenreAdapter mGenreAdapter;

    private static final String TAG = "HomeTabFragment";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_tab, container, false);
        relativeLayout = view.findViewById(R.id.relativeLayout);
        relativeLayout.setVisibility(View.GONE);

        bindViews(view);

        setUpTrendingRecycler();

        setUpGenreRecycler();


        return view;


    }


    private void bindViews(View view) {
        /* bind trending recycler */
        mTrendingRecycler = view.findViewById(R.id.trending_recycler);

        /* bind loading banner */
//        mLoadingCard = findViewById(R.id.loading_card);

        mGenreRecycler = view.findViewById(R.id.genre_recycler);

       /* mGenreListView = findViewById(R.id.genre_list);
        GenreListAdapter genreListAdapter = new GenreListAdapter(this, R.layout.genere_unit, Genre.GenreList);
        mGenreListView.setAdapter(genreListAdapter);*/

    }

    private void setUpTrendingRecycler() {
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mTrendingRecycler);

        mTrendingRecycler.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));

        mTrendingAdapter = new TrendingAdapter(getContext(), new ArrayList<Movie>());

        mTrendingRecycler.setAdapter(mTrendingAdapter);
    }

    private void setUpGenreRecycler() {
        mGenreRecycler.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        /* for smooth scrolling */
        mGenreRecycler.setNestedScrollingEnabled(false);

        mGenreAdapter = new GenreAdapter(getContext(), Genre.GenreList);
        mGenreRecycler.setAdapter(mGenreAdapter);
    }

    @Override
    @SuppressLint("DefaultLocale")
    public void onResume() {
        super.onResume();

        ApiClient.TheMovieDBApiInterface service = ApiClient.getTheMovieDBApiInterface();
        if (mTrendingList == null) {
            Calendar today = Calendar.getInstance();

            String todayString = String.format("%d-%02d-%02d", today.get(Calendar.YEAR),
                    today.get(Calendar.MONTH) + 1, today.get(Calendar.DAY_OF_MONTH));

            today.add(Calendar.DAY_OF_MONTH, -30);
            String oneMonthBackString = String.format("%d-%02d-%02d", today.get(Calendar.YEAR),
                    today.get(Calendar.MONTH) + 1, today.get(Calendar.DAY_OF_MONTH));

            Log.d("MainActivity", oneMonthBackString + " " + todayString);

            Call<DiscoverResult> call = service.getTopTrendingMovies(getString(R.string.api_key),
                    Constants.QueryDefaultVal.LANG, Constants.QueryDefaultVal.REGION,
                    Constants.QueryDefaultVal.SORT_BY, Constants.QueryDefaultVal.CERT_COUNTRY,
                    Constants.QueryDefaultVal.PAGE_NO, oneMonthBackString, todayString);

            call.enqueue(new Callback<DiscoverResult>() {
                @Override
                public void onResponse(Call<DiscoverResult> call, Response<DiscoverResult> response) {
                    mTrendingList = response.body().getResults();
                    //                    mLoadingCard.setVisibility(View.INVISIBLE);


                    mTrendingAdapter.setTrendingMovieList(mTrendingList);
                    mTrendingAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<DiscoverResult> call, Throwable t) {
                    Log.e("MainActivity", "Error in fetching data: " + t.getMessage());
                }
            });
        }
    }


}






