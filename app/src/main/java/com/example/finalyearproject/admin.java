package com.example.finalyearproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class admin extends AppCompatActivity {
    MeowBottomNavigation bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        getSupportActionBar().hide();
        bottomNavigation = findViewById(R.id.bottom_navbar);
        setListeners();
        bottomNavigation.show(2 , true);
        //getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout , new Admin_FragmenForFoodItems()).commit();

    }

    private void setListeners(){
//        BottomNavigationView navBar = findViewById(R.id.bottom_navbar);
//        //navBar.setItemIconTintList(null);
//        navBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Fragment fragment = null;
//                switch (item.getItemId()){
//                    case R.id.employ:{
//                        fragment = new Admin_FragmenForEmploy();
//                        break;
//                    }
//                    case R.id.food_items:{
//                        fragment = new Admin_FragmenForFoodItems();
//                        break;
//                    }
//                    case R.id.profile:{
//                        fragment = new Admin_FragmenForProfile();
//                        break;
//                    }
//                }
//                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout , fragment).commit();
//                return true;
//            }
//        });

        bottomNavigation.add(new MeowBottomNavigation.Model(1 , R.drawable.ic_employee));
        bottomNavigation.add(new MeowBottomNavigation.Model(2 , R.drawable.ic_hamburger));
        bottomNavigation.add(new MeowBottomNavigation.Model(3 , R.drawable.ic_programmer));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;
                switch (item.getId()){
                    case 1:{
                        fragment = new Admin_FragmenForEmploy();
                        break;
                    }
                    case 2:{
                        fragment = new Admin_FragmenForFoodItems();
                        break;
                    }
                    case 3:{
                        fragment = new Admin_FragmenForProfile();
                        break;
                    }
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout , fragment).commit();
            }
        });

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {

            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {

            }
        });
    }
}