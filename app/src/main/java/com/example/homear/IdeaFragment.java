package com.example.homear;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IdeaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IdeaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public IdeaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IdeaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IdeaFragment newInstance(String param1, String param2) {
        IdeaFragment fragment = new IdeaFragment();
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

    ArrayList<cartItem>cartItemArrayList =new ArrayList<>();
    CartAdapter cartAdapter;
    RecyclerView cartRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_idea, container, false);

        cartRecyclerView=view.findViewById(R.id.cartRecyclerView);
        cartItem cart_item1=new cartItem("chair",R.drawable.texture,"blue","metallic","20$");
        cartItem cart_item2=new cartItem("chair",R.drawable.texture,"blue","metallic","20$");
        cartItem cart_item3=new cartItem("chair",R.drawable.texture,"blue","metallic","20$");
        cartItem cart_item4=new cartItem("chair",R.drawable.texture,"blue","metallic","20$");
        cartItem cart_item5=new cartItem("chair",R.drawable.texture,"blue","metallic","20$");
        cartItem cart_item6=new cartItem("chair",R.drawable.texture,"blue","metallic","20$");


        cartItemArrayList.add(cart_item1);
        cartItemArrayList.add(cart_item2);
        cartItemArrayList.add(cart_item3);
        cartItemArrayList.add(cart_item4);
        cartItemArrayList.add(cart_item5);
        cartItemArrayList.add(cart_item6);



        cartAdapter=new CartAdapter(cartItemArrayList);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        cartRecyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                cartItemArrayList.remove(viewHolder.getAdapterPosition());
                cartAdapter.notifyDataSetChanged();

            }
        }).attachToRecyclerView(cartRecyclerView);


        return view;
    }
}