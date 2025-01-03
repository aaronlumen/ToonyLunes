package shop.surina.obdracing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(item -> {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.nav_sensors:
                    fragment = new SensorSelectionFragment();
                    break;
                case R.id.nav_race_display:
                    fragment = new RaceDisplayFragment();
                    break;
                case R.id.nav_dashboard:
                default:
                    fragment = new DashboardFragment();
                    break;
            }
            loadFragment(fragment);
            return true;
        });

        // Default selection
        bottomNav.setSelectedItemId(R.id.nav_dashboard);
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}
