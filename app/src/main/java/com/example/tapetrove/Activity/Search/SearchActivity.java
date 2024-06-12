package com.example.tapetrove.Activity.Search;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.tapetrove.Activity.Home.MainActivity;
import com.example.tapetrove.Activity.Profile.ProfileActivity;
import com.example.tapetrove.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class SearchActivity extends AppCompatActivity {

  //    Fragment implementation
  FrameLayout frameLayout;
  TabLayout tabLayout;
  FragmentManager fragmentManager = getSupportFragmentManager();
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search);

    frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
    tabLayout = (TabLayout) findViewById(R.id.tabLayout);

    fragmentManager.beginTransaction().replace(R.id.frameLayout, new SearchFragment())
            .addToBackStack(null)
            .commit();

    tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override
      public void onTabSelected(TabLayout.Tab tab) {

        Fragment fragment = null;
        switch (tab.getPosition()) {
          case 0:
            fragment = new SearchFragment();
            break;
          case 1:
            fragment = new SortFragment();
            break;
        }

        if (fragment != null) {
          fragmentManager.beginTransaction()
                  .replace(R.id.frameLayout, fragment)
                  .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                  .commit();
        }
      }

      @Override
      public void onTabUnselected(TabLayout.Tab tab) {

      }

      @Override
      public void onTabReselected(TabLayout.Tab tab) {

      }
    });

//    Bottomnavigationbar logic
    BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavBar);
    bottomNavigationView.setSelectedItemId(R.id.search);
    bottomNavigationView.setOnItemSelectedListener(item -> {
      if (item.getItemId() == R.id.home) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
        return true;
      } else if (item.getItemId() == R.id.search) {
        startActivity(new Intent(getApplicationContext(), SearchActivity.class));
        finish();
        return true;
      } else if (item.getItemId() == R.id.profile) {
        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        finish();
        return true;
      }
      return false;
    });
  }
}