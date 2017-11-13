package com.example.project.seniorpj.Hamburger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.egco428.camera.CameraOneStepActivity;
import com.example.project.seniorpj.Camera.CameraActivity;
import com.example.project.seniorpj.Food.FoodListFragment;
import com.example.project.seniorpj.GalleryFragment;
import com.example.project.seniorpj.Healthy.HealthListActivity;
import com.example.project.seniorpj.Home.HomeFragment;
import com.example.project.seniorpj.R;
import com.example.project.seniorpj.RegisLogIn.Main_Activity_Regis_LogIn;

public class HamburgerActivity extends AppCompatActivity {

    //Defining Variables
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();

        if (v != null &&
                (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                v instanceof EditText &&
                !v.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            v.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + v.getLeft() - scrcoords[0];
            float y = ev.getRawY() + v.getTop() - scrcoords[1];

            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom())
                hideKeyboard(this);
        }
        return super.dispatchTouchEvent(ev);
    }

    public static void hideKeyboard(Activity activity) {
        if (activity != null && activity.getWindow() != null && activity.getWindow().getDecorView() != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hamburger);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(username);
        setSupportActionBar(toolbar);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        View header=navigationView.getHeaderView(0);
        TextView tv_username_header = (TextView)header.findViewById(R.id.tv_username_header);
        tv_username_header.setText(username);

        if (savedInstanceState == null) {
            Fragment fragment1 = new HomeFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
            fragmentTransaction1.replace(R.id.frame, fragment1);
            fragmentTransaction1.commit();
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();
                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    // For rest of the options we just show a toast on click
                    case R.id.tab_home:
                        toolbar.setTitle("");
                        Fragment fragment1 = new HomeFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction1.replace(R.id.frame, fragment1);
                        fragmentTransaction1.commit();
                        return true;
                    case R.id.tab_camera:
                        Intent intent_camera = new Intent(getBaseContext(), CameraOneStepActivity.class);
                        startActivity(intent_camera);
//                        toolbar.setTitle("Camera");
//                        Fragment fragment2 = new FragmentCamera();
//                        android.support.v4.app.FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
//                        fragmentTransaction2.replace(R.id.frame, fragment2);
//                        fragmentTransaction2.commit();
                        return true;
                    case R.id.tab_gallery:
                        toolbar.setTitle("Gallery");
                        Fragment fragment3 = new GalleryFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction3.replace(R.id.frame, fragment3);
                        fragmentTransaction3.commit();
                        return true;
                    case R.id.tab_foodList:
                        toolbar.setTitle("FoodList");
                        Fragment fragment4 = new FoodListFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction4 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction4.replace(R.id.frame, fragment4);
                        fragmentTransaction4.commit();
                        return true;
                    case R.id.tab_healthyTricks:
//                        Intent intent_healthy = new Intent(getBaseContext(), HealthListActivity.class);
//                        startActivity(intent_healthy);
                        toolbar.setTitle("HealthyTricks");
                        Fragment fragment5 = new HealthListActivity();
                        android.support.v4.app.FragmentTransaction fragmentTransaction5 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction5.replace(R.id.frame, fragment5);
                        fragmentTransaction5.commit();
                        return true;
                    case R.id.tab_logOut:
                        Intent intent_logout = new Intent(getBaseContext(), Main_Activity_Regis_LogIn.class);
                        startActivity(intent_logout);
                        finish();
                        return true;
                    default:
                        return true;
                }
            }
        });

        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your search icon wont show up
        actionBarDrawerToggle.syncState();
    }
}
