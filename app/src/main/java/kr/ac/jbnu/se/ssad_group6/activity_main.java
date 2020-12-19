package kr.ac.jbnu.se.ssad_group6;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.util.FusedLocationSource;

import kr.ac.jbnu.se.ssad_group6.R;
import kr.ac.jbnu.se.ssad_group6.fragment.Fragment_Map;
import kr.ac.jbnu.se.ssad_group6.fragment.Fragment_tracking;

public class activity_main extends AppCompatActivity {
    BottomNavigationView navigationView;
    Fragment_Map fragment_map;
    Fragment_tracking fragment_tracking;
    FragmentManager fm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        navigationView = findViewById(R.id.menubar);

        fragment_map = new Fragment_Map();
        fragment_tracking = new Fragment_tracking();
        fm = getSupportFragmentManager();

        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, fragment_map).commitAllowingStateLoss();
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.tab_map:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, fragment_map).commitAllowingStateLoss();

                        return true;

                    case R.id.tab_tracking:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, fragment_tracking).commitAllowingStateLoss();

                        return true;

                    default:
                        return false;
                }
            }
        });
    }
}
