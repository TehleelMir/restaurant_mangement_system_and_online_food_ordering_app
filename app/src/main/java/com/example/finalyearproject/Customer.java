package com.example.finalyearproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Customer extends AppCompatActivity {

    private MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        getSupportActionBar().hide();
        bottomNavigation = findViewById(R.id.customer_bottom_navbar);
        setListeners();
        bottomNavigation.show(2 , true);
        getSupportFragmentManager().beginTransaction().replace(R.id.customer_frameLayout , new Customer_fragmentForFav()).commit();
    }

    private void setListeners(){
//        BottomNavigationView navBar = findViewById(R.id.customer_bottom_navbar);
//        navBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Fragment fragment = null;
//                switch (item.getItemId()){
//                    case R.id.items_for_order:{
//                        fragment = new Customer_fragmentForItems();
//                        break;
//                    }
//                    case R.id.favorite_food_items:{
//                        fragment = new Customer_fragmentForFav();
//                        break;
//                    }
//                    case R.id.profile_for_customer:{
//                        fragment = new Customer_fragmentForProfile();
//                        break;
//                    }
//                }
//                getSupportFragmentManager().beginTransaction().replace(R.id.customer_frameLayout , fragment).commit();
//                return true;
//            }
//        });

        bottomNavigation.add(new MeowBottomNavigation.Model(1 , R.drawable.ic_fast_food));
        bottomNavigation.add(new MeowBottomNavigation.Model(2 , R.drawable.ic_groceries));
        bottomNavigation.add(new MeowBottomNavigation.Model(3 , R.drawable.ic_man));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;
                switch (item.getId()){
                    case 1:{
                        fragment = new Customer_fragmentForItems();
                        break;
                    }
                    case 2:{
                        fragment = new Customer_fragmentForFav();
                        break;
                    }
                    case 3:{
                        fragment = new Customer_fragmentForProfile();
                        break;
                    }
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.customer_frameLayout , fragment).commit();
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