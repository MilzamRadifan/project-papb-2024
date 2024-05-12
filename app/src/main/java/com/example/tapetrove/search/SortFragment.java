package com.example.tapetrove.search;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tapetrove.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SortFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SortFragment extends Fragment {

  private RecyclerView recyclerView;
  private SearchAdapter searchAdapter;
  RecyclerView.LayoutManager layoutManager;
  List<Movie> movieList = new ArrayList<>();

  public static String BASE_URL = "https://api.themoviedb.org";
  public static int PAGE = 1;
  public static String API_KEY = "f8e9f4c81b4ed42c209634560167e1a9";
  public static String LANGUAGE = "en-US";
  public String SORT;

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  public SortFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment SortFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static SortFragment newInstance(String param1, String param2) {
    SortFragment fragment = new SortFragment();
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

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_sort, container, false);

    recyclerView = view.findViewById(R.id.rvSearch);
    recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 3));
    recyclerView.setHasFixedSize(true);

    searchAdapter = new SearchAdapter(requireContext(), movieList);
    recyclerView.setAdapter(searchAdapter);

    setupSpinner(view);

    return view;
  }

  private void setupSpinner(View view) {
    Spinner spinSort = view.findViewById(R.id.spinSorting);
    ArrayList<String> spinner = new ArrayList<>(Arrays.asList(
            "Popular", "Less Popular", "Title Ascending", "Title Descending",
            "Newest", "Oldest", "Most votes", "Fewest votes"));

    final String[] values = {"popularity.desc", "popularity.asc",
            "original_title.asc", "original_title.desc",
            "primary_release_date.desc", "primary_release_date.asc",
            "vote_count.desc", "vote_count.asc"};

    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
            requireContext(), android.R.layout.simple_spinner_item, spinner);
    spinnerAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
    spinSort.setAdapter(spinnerAdapter);

    spinSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        SORT = values[position];
        Toast.makeText(requireContext(), "Selected item: " + SORT, Toast.LENGTH_SHORT).show();
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
        if (response.isSuccessful() && response.body() != null) {
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