package com.example.homear;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.*;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BrandsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BrandsFragment extends Fragment  {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private RecyclerView prodRecyclerView;


    public BrandsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BrandsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BrandsFragment newInstance(String param1, String param2) {
        BrandsFragment fragment = new BrandsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    ArrayList<products>productList =new ArrayList<>();
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReferencese;
//    products p1=new products(R.drawable.ic_share_black_40dp,"Chair","1k");
//    products p2=new products(R.drawable.ic_share_black_40dp,"Chair","1k");
//    products p3=new products(R.drawable.ic_share_black_40dp,"Chair","1k");
//    products p4=new products(R.drawable.ic_share_black_40dp,"Chair","1k");





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_brands, container, false);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReferencese = firebaseDatabase.getReference("products");

        databaseReferencese.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                productList.clear();
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    products product=new products(snapshot1.child("img").getValue().toString(),
                            snapshot1.child("name").getValue().toString(),
                            snapshot1.child("price").getValue().toString(),
                            snapshot1.child("model").getValue().toString(),
                            snapshot1.getRef().getKey().toString()
                    );
                    productList.add(product);

                }

                prodRecyclerView.setAdapter(new productAdapter(productList));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("test",error.getMessage());

            }
        });

//        ValueEventListener FirebaseListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Iterable<DataSnapshot> child = snapshot.getChildren();
//                child.forEach(
//
//                );
//
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        };
//        databaseReferencese.addValueEventListener(FirebaseListener);


        prodRecyclerView = view.findViewById(R.id.prodRecyclerView);
        prodRecyclerView.setHasFixedSize(true);
        prodRecyclerView.setLayoutManager(new GridLayoutManager(view.getContext(),2));

//        productList.add(p1);
//        productList.add(p2);
//        productList.add(p3);
//        productList.add(p4);



        return view;
    }

//    @Override
//    public void onItemClick(products product) {
//        Fragment fragment = ProductFragment.newInstance(product.getKey());
//        FragmentTransaction transaction= getActivity().getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.framecontainer,fragment,"product_fragment").addToBackStack(null).commit();
//
//
//
//    }
}