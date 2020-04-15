package com.example.moviedb.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.example.moviedb.R;
import com.example.moviedb.fragments.AccountFragment;
import com.example.moviedb.fragments.MoviesFragment;
import com.example.moviedb.fragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.rl_container)
    RelativeLayout rlContainer;
    @BindView(R.id.bn_main)
    BottomNavigationView bnMain;

    private AccountFragment accountFragment = new AccountFragment();
    private MoviesFragment moviesFragment = new MoviesFragment();
    private SearchFragment searchFragment = new SearchFragment();
    private Fragment activeFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().add(R.id.rl_container, accountFragment).hide(accountFragment).commit();
        fragmentManager.beginTransaction().add(R.id.rl_container, searchFragment).hide(searchFragment).commit();
        fragmentManager.beginTransaction().add(R.id.rl_container, moviesFragment).commit();
        activeFragment = moviesFragment;
        bnMain.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_bar_item_movies:
                        fragmentManager.beginTransaction().hide(activeFragment).show(moviesFragment).commit();
                        activeFragment = moviesFragment;
                        return true;
                    case R.id.nav_bar_item_search:
                        fragmentManager.beginTransaction().hide(activeFragment).show(searchFragment).commit();
                        activeFragment = searchFragment;
                        return true;
                    case R.id.nav_bar_item_Account:
                        fragmentManager.beginTransaction().hide(activeFragment).show(accountFragment).commit();
                        activeFragment = accountFragment;
                        return true;
                }
                return false;
            }
        });
    }
}
