package com.example.homear;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    ArrayList<products>productList =new ArrayList<>();
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReferencese;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
//

    }

    private RecyclerView recyclerView;
//    private FirebaseViewModel firebaseViewModel;
    ArrayList<homeClass>homeClassArrayList;
    ArrayList<Categories> categoriesArrayList;
    ArrayList<Categories>favouriteArrayList;
    ArrayList<Categories>recentlyArrayList;
    ArrayList<products>productsArrayList;
    homeAdapter home_Adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_home, container, false);
//        getView().findViewById(R.id.cardTap).setOnClickListener(v -> {
//            Intent intent=new Intent(getActivity(),product_page.class);
//            startActivity(intent);
//        });

        recyclerView=view.findViewById(R.id.homeRecyclerView);
        categoriesArrayList=new ArrayList<>();
        favouriteArrayList= new ArrayList<>();
        recentlyArrayList= new ArrayList<>();
        homeClassArrayList=new ArrayList<>();
        productsArrayList=new ArrayList<>();

        recyclerView.setHasFixedSize(true);
        categoriesArrayList.add(new Categories("sofa",R.drawable.texture));
        categoriesArrayList.add(new Categories("bed",R.drawable.texture));
        categoriesArrayList.add(new Categories("chair",R.drawable.texture));
        categoriesArrayList.add(new Categories("desk",R.drawable.texture));
        categoriesArrayList.add(new Categories("table",R.drawable.texture));

        homeClassArrayList.add(new homeClass("categories",categoriesArrayList));

//        categoriesArrayList.clear();
        favouriteArrayList.add(new Categories("sofa",R.drawable.texture));
        favouriteArrayList.add(new Categories("bed",R.drawable.texture));
        favouriteArrayList.add(new Categories("chair",R.drawable.texture));
        favouriteArrayList.add(new Categories("desk",R.drawable.texture));
        favouriteArrayList.add(new Categories("table",R.drawable.texture));

        homeClassArrayList.add(new homeClass("categories",favouriteArrayList));

//        categoriesArrayList.clear();
        recentlyArrayList.add(new Categories("sofa",R.drawable.texture));
        recentlyArrayList.add(new Categories("bed",R.drawable.texture));
        recentlyArrayList.add(new Categories("chair",R.drawable.texture));
        recentlyArrayList.add(new Categories("desk",R.drawable.texture));
        recentlyArrayList.add(new Categories("table",R.drawable.texture));

        homeClassArrayList.add(new homeClass("categories",recentlyArrayList));

//        homeClassArrayList.add(new homeClass("products",))

//        home_Adapter=new homeAdapter(homeClassArrayList,view.getContext());
//        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
//        recyclerView.setAdapter(home_Adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
//
//        firebaseViewModel=new ViewModelProvider(this).get(FirebaseViewModel.class);
//
//        firebaseViewModel.getAllData();
//        firebaseViewModel.getHomeClassMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<homeClass>>() {
//            @Override
//            public void onChanged(List<homeClass> homeClassList) {
//
//                home_Adapter.setHomeClassList(homeClassList);
//                home_Adapter.notifyDataSetChanged();
//            }
//        });
//        firebaseViewModel.getDatabaseErrorMutableLiveData().observe(getViewLifecycleOwner(), new Observer<DatabaseError>() {
//            @Override
//            public void onChanged(DatabaseError error) {
//                Toast.makeText((Context) getViewLifecycleOwner(),error.toString(),Toast.LENGTH_SHORT).show();
//            }
//        });

//        categoriesArrayList.add(new Categories("sofa",R.drawable.texture));
//        categoriesArrayList.add(new Categories("bed",R.drawable.texture));
//        categoriesArrayList.add(new Categories("chair",R.drawable.texture));
//        categoriesArrayList.add(new Categories("desk",R.drawable.texture));
//        categoriesArrayList.add(new Categories("table",R.drawable.texture));
//
//        homeClassArrayList.add(new homeClass("categories",categoriesArrayList));
//
////        categoriesArrayList.clear();
//        favouriteArrayList.add(new Categories("sofa",R.drawable.texture));
//        favouriteArrayList.add(new Categories("bed",R.drawable.texture));
//        favouriteArrayList.add(new Categories("chair",R.drawable.texture));
//        favouriteArrayList.add(new Categories("desk",R.drawable.texture));
//        favouriteArrayList.add(new Categories("table",R.drawable.texture));
//
//        homeClassArrayList.add(new homeClass("categories",favouriteArrayList));
//
////        categoriesArrayList.clear();
//        recentlyArrayList.add(new Categories("sofa",R.drawable.texture));
//        recentlyArrayList.add(new Categories("bed",R.drawable.texture));
//        recentlyArrayList.add(new Categories("chair",R.drawable.texture));
//        recentlyArrayList.add(new Categories("desk",R.drawable.texture));
//        recentlyArrayList.add(new Categories("table",R.drawable.texture));

//        homeClassArrayList.add(new homeClass("categories",recentlyArrayList));

        home_Adapter=new homeAdapter(homeClassArrayList,view.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(home_Adapter);
        home_Adapter.notifyDataSetChanged();









        return view;
    }




}