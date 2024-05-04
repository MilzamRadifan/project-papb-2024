package com.example.tapetrove;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Search extends AppCompatActivity  {
    EditText inputIdentifier;
    Button btSearch;
    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<Movie> movieList = new ArrayList<>();

//    retrofit Implementation
    public static String BASE_URL = "https://api.themoviedb.org";
    public static int PAGE = 1;
    public static String API_KEY = "f8e9f4c81b4ed42c209634560167e1a9";
    public static String LANGUAGE = "en-US";
    public String IDENTIFIER;
    public String SORT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);

        recyclerView = findViewById(R.id.rvSearch);
        layoutManager = new GridLayoutManager(Search.this, 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        searchAdapter = new SearchAdapter(Search.this, movieList);
        recyclerView.setAdapter(searchAdapter);

        //      Spinner logic
        Spinner spinSort = findViewById(R.id.spinSorting);

        ArrayList<String> spinner = new ArrayList<>();
        spinner.add("Popular");
        spinner.add("Less Popular");
        spinner.add("Title Ascending");
        spinner.add("Title Descending");
        spinner.add("Newest");
        spinner.add("Oldest");
        spinner.add("Most votes");
        spinner.add("Fewest votes");

        final String[] values = {"popularity.desc", "popularity.asc",
                "original_title.asc", "original_title.desc",
                "primary_release_date.desc", "primary_release_date.asc",
                "vote_count.desc", "vote_count.asc"};

        ArrayAdapter<String> spinnerAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinner);
        spinnerAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinSort.setAdapter(spinnerAdapter);

        spinSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                SORT = values[position];
                Toast.makeText(Search.this, "Selected item: " + SORT, Toast.LENGTH_SHORT).show();
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        sortDataFromAPI();
                    }
                });
                thread.start();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



//      Search logic
        inputIdentifier = findViewById(R.id.etSearchForm);
        btSearch = findViewById(R.id.btSearch);
        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IDENTIFIER = inputIdentifier.getText().toString();
                if (IDENTIFIER != "") {
                    searchDataFromAPI(IDENTIFIER);
                }
            }
        });


//        inputIdentifier = findViewById(R.id.etSearchForm);
//        inputIdentifier.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
//                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
//                        (keyEvent.getAction() == KeyEvent.ACTION_DOWN &&
//                                keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
//                    IDENTIFIER = inputIdentifier.getText().toString();
//                    if (!IDENTIFIER.isEmpty()) {
//                        searchDataFromAPI(IDENTIFIER);
//                        return true; // Indicate that the event has been handled
//                    }
//                }
//                return false; // Return false to indicate that we have not consumed the event
//            }
//        });






//      Bottom navigation bar logic
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavBar);
        bottomNavigationView.setSelectedItemId(R.id.bottom_search);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.bottom_home) {
                // Handle Home item click
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.bottom_search) {
                // Handle Search item click
                return true;
            } else if (item.getItemId() == R.id.bottom_profile) {
                // Handle Profile item click
//                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                finish();
                return true;
            }
            return false;
        });
    }

    public void sortDataFromAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        Call<ResponseBody> call = apiInterface.sortMovies(API_KEY, LANGUAGE, PAGE, SORT);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String jsonResponse = response.body().string();
                        Gson gson = new Gson();
                        ApiResponse apiResponse = gson.fromJson(jsonResponse, ApiResponse.class);

                        if (apiResponse.getPage() == 1) {
                            movieList.clear();
                            movieList.addAll(apiResponse.getResults());
                            searchAdapter.notifyDataSetChanged();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void searchDataFromAPI(String identifier) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        Call<ResponseBody> call = apiInterface.searchMovie(API_KEY, identifier,LANGUAGE, PAGE);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String jsonResponse = response.body().string();
                        Gson gson = new Gson();
                        ApiResponse apiResponse = gson.fromJson(jsonResponse, ApiResponse.class);

                        if (apiResponse.getPage() == 1) {
                            movieList.clear();
                            movieList.addAll(apiResponse.getResults());
                            searchAdapter.notifyDataSetChanged();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}