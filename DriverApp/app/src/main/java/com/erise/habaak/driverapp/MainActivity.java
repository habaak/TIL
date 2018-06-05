package com.erise.habaak.driverapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.habaa.driverapp.LoginActivity;

public class MainActivity extends AppCompatActivity {


    Fragment fragment;
    public String id,name,age,num,busidx,platenum,busType,driverIdx,busEnergy,gender;
    public static String intent_id,intent_name,intent_age,intent_num,intent_busidx,intent_platenum,intent_busType,intent_driverIdx,intent_busEnergy,intent_gender;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;

    {
        mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.navigation_home:
                        fragment = new HomeFragment();
                        switchFragment(fragment);
                        return true;
                    case R.id.navigation_bus_info:
                        fragment = new BusFragment();
                        switchFragment(fragment);
                        return true;
                    case R.id.navigation_driver_info:
                        fragment = new DriverFragment();
                        switchFragment(fragment);
                        return true;
                }
                return false;
            }

        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = getIntent();
        intent_id=intent.getStringExtra("ID");
        intent_gender =intent.getStringExtra("GENDER");
        intent_name=intent.getStringExtra("NAME");
        intent_age=intent.getStringExtra("AGE");
        intent_num=intent.getStringExtra("NUM");
        intent_busidx=intent.getStringExtra("BUSIDX");
        intent_driverIdx=intent.getStringExtra("DRIVERIDX");
        intent_busEnergy=intent.getStringExtra("BUSENERGY");
        intent_platenum=intent.getStringExtra("PLATENUM");



        if(loginChekcker().equals("")){
            Intent loginintent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(loginintent);
        }else{
            //  Fragmnet
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            HomeFragment homeFragment = new HomeFragment();

            fragmentTransaction.add(R.id.fragment_container,homeFragment);
            fragmentTransaction.commit();

//  Bottom Nav
            //mTextMessage = (TextView) findViewById(R.id.message);
            BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        }

        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        id =pref.getString("ID", intent_id);
        name = pref.getString("NAME", intent_name);
        age = pref.getString("AGE", intent_age);
        num =pref.getString("NUM", intent_num);
        busidx = pref.getString("BUSIDX", intent_busidx);
        busType = pref.getString("BUSTYPE", "");
        driverIdx = pref.getString("DRIVERIDX", intent_driverIdx);
        busEnergy = pref.getString("BUSENERGY", intent_busEnergy);
        platenum = pref.getString("PLATENUM",intent_platenum);
        gender = pref.getString("GENDER",intent_gender);
    }
    private String loginChekcker(){
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        String driveridx = pref.getString("DRIVERIDX", "");
        return driveridx;
    }

    public void switchFragment(Fragment fragment){

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack
        transaction.replace(R.id.fragment_container, fragment);
// Commit the transaction
        transaction.commit();
    }


}