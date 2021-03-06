package com.example.homear;


//public class MainActivity extends AppCompatActivity {
//
//    BottomNavigationView bottomNavigationView;
//
//    homeFragment homefragment =new homeFragment();
//    ideaFragment ideafragment =new ideaFragment();
//    userProfile userprofile =new userProfile();
//    brandsFragment brandsfragment =new brandsFragment();
//
//
//
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
//                .getBoolean("isFirstRun", true);
//
//
//        bottomNavigationView= (BottomNavigationView) findViewById(R.id.bottomNavigationView);
//
//        setContentView(R.layout.activity_main);
//        getSupportFragmentManager().beginTransaction().replace(R.id.container,homefragment).commit();
//
//
//        findViewById(R.id.fab).setOnClickListener(e->{
//            Intent intent=new Intent(this,showAR.class);
//                    startActivity(intent);
//
//
//        });
//
//        findViewById(R.id.favorite).setOnClickListener(e->{
//                Intent intent=new Intent(this,show3D.class);
//                startActivity(intent);
//        });
//
//        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(MenuItem item) {
//                switch(item.getItemId()){
//                    case R.id.brands:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.container,brandsfragment).commit();
//                        break;
//                    case R.id.idea:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.container,ideafragment).commit();
//                        break;
//                    case R.id.account:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.container,userprofile).commit();
//                        break;
//                }
//                return true;
//            }
//        });
//
//
//
//    }
//
//
//}

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomnavigationbar);

        bottomNavigationView.setBackground(null);

//        bottomNavigationView.getMenu().getItem(2).setEnabled(false);

        getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer,new HomeFragment()).commit();

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment temp = null;
                switch (item.getItemId())
                {
                    case R.id.brands:
                        temp = new BrandsFragment();
                        break;
                    case R.id.idea :
                        temp = new IdeaFragment();
                        break;
                    case R.id.account :
                        temp = new UserFragment();

                        break;
                    case R.id.home:
                        temp = new HomeFragment();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer,temp).commit();
                return true;
            }
        });

        findViewById(R.id.fab).setOnClickListener(e->{
            Intent intent=new Intent(this,showAR.class);
                    startActivity(intent);
        });

        findViewById(R.id.favorite).setOnClickListener(e->{
               Intent intent=new Intent(this,show3D.class);
               startActivity(intent);
        });
    }
}



