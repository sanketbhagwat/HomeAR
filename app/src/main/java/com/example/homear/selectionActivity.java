package com.example.homear;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class selectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new BrandsFragment()).commit();

//        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Fragment temp = null;
//                switch (item.getItemId())
//                {
//                    case R.id.brands:
//                        temp = new BrandsFragment();
//                        break;
//                    case R.id.idea :
//                        temp = new IdeaFragment();
//                        break;
//                    case R.id.account :
//                        temp = new UserFragment();
//
//                        break;
//                    case R.id.home:
//                        temp = new HomeFragment();
//                }
//                getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer,temp).commit();
//                return true;
//            }
//        });
    }
}