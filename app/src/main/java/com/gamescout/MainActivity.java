package com.gamescout;

import android.os.Bundle;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.topAppBar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.open,
                R.string.open
        );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // 🔥 FIX: increase swipe edge area
        increaseDrawerEdge();

        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
            navigationView.setCheckedItem(R.id.nav_home);
        }

        navigationView.setNavigationItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.nav_home) {
                loadFragment(new HomeFragment());
            } else if (id == R.id.nav_games) {
                loadFragment(new GamesFragment());
            } else if (id == R.id.nav_demos) {
                loadFragment(new DemosFragment());
            } else if (id == R.id.nav_wishlist) {
                loadFragment(new WishlistFragment());
            } else if (id == R.id.nav_settings) {
                loadFragment(new SettingsFragment());
            } else if (id == R.id.nav_about) {
                loadFragment(new AboutFragment());
            }

            drawerLayout.closeDrawers();
            return true;
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }

    // ✅ Swipe edge fix (no external files needed)
    private void increaseDrawerEdge() {
        try {
            java.lang.reflect.Field mDragger = drawerLayout.getClass()
                    .getDeclaredField("mLeftDragger");

            mDragger.setAccessible(true);

            androidx.customview.widget.ViewDragHelper dragger =
                    (androidx.customview.widget.ViewDragHelper) mDragger.get(drawerLayout);

            java.lang.reflect.Field mEdgeSize = dragger.getClass()
                    .getDeclaredField("mEdgeSize");

            mEdgeSize.setAccessible(true);

            int edgeSize = mEdgeSize.getInt(dragger);

            // 🔥 increase swipe zone (adjust 2–4 if you want more)
            mEdgeSize.setInt(dragger, edgeSize * 3);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
