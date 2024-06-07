package com.example.tapetrove.Activity.Profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tapetrove.Database.Wishlist;
import com.example.tapetrove.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WishlistFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WishlistFragment extends Fragment {

  private FirebaseAuth mAuth;
  private DatabaseReference databaseReference;
  private FirebaseUser curUser;
  RecyclerView recyclerView;
  private WishlistAdapter adapter;
  private TextView tvGreet;

  List<Wishlist> wishlistList = new ArrayList<>();

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  public WishlistFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment WishlistFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static WishlistFragment newInstance(String param1, String param2) {
    WishlistFragment fragment = new WishlistFragment();
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
    View view = inflater.inflate(R.layout.fragment_wishlist, container, false);

    recyclerView = view.findViewById(R.id.rvWishlist);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    mAuth = FirebaseAuth.getInstance();
    curUser = mAuth.getCurrentUser();
    databaseReference = FirebaseDatabase.getInstance().getReference("wishlist").child(curUser.getUid());

    adapter = new WishlistAdapter(getContext(), wishlistList);
    recyclerView.setAdapter(adapter);

    tvGreet = view.findViewById(R.id.tvGreet);
    tvGreet.setText(curUser.getEmail() + " Wishlist");

    fetchDataWislist();
    return view;
  }

  @Override
  public void onResume() {
    super.onResume();
    fetchDataWislist();
  }

  private void fetchDataWislist() {
    databaseReference.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        wishlistList.clear();
        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
          Wishlist wishlist = snapshot.getValue(Wishlist.class);
          if (wishlist != null) {
            wishlist.setKey(snapshot.getKey());
            wishlistList.add(wishlist);
          }
        }
        adapter.notifyDataSetChanged();
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {
        Toast.makeText(getContext(), "Failed fetch data", Toast.LENGTH_SHORT).show();
      }
    });
  }


}