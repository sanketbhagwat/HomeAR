package com.example.homear;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
//    private String mParam2;

    public ProductFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductFragment newInstance(String param1) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    DatabaseReference reference;
    ImageView imageView;
    TextView nameView;
    TextView priceView;
    Button arButton;
    Button _3dButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_product, container, false);

        reference= FirebaseDatabase.getInstance().getReference().child("products");
        imageView=view.findViewById(R.id.productImage);
        nameView=view.findViewById(R.id.productName);
        priceView=view.findViewById(R.id.productPrice);
        arButton=view.findViewById(R.id.arButton);
        _3dButton=view.findViewById(R.id._3dButton);

        products product_obj =getArguments().getParcelable("product_obj");
//        reference.child(prod_key).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists()){
//                    String name = snapshot.child("name").getValue().toString();
//                    String img =snapshot.child("img").getValue().toString();
//                    String price=snapshot.child("price").getValue().toString();
//                    String model=snapshot.child("model").getValue().toString();
//
                    Picasso.get().load(product_obj.img)
                            .into(imageView);
                    nameView.setText(product_obj.title);
                    priceView.setText(product_obj.price);

//                    FirebaseStorage storage = FirebaseStorage.getInstance();
//                    StorageReference httpsReference = storage.getReferenceFromUrl(model);
//                    arButton.setOnClickListener(v->{
//                        try {
//                            File file = File.createTempFile("model", "glb");
//                            httpsReference.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
//                                @Override
//                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                                    Intent  intent =new Intent(getActivity(),showAR.class);
//                                    intent.putExtra("modelFile",file);
//                                    startActivity(intent);
//
//
//
//
//                                }
//                            });
//                        } catch (IOException e) {
//                            e.printStackTrace();
////
//
//                        }
//                    });
//
//
//                }
//
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Log.d("test",error.getMessage());
//
//            }
//        });

//        nameView.setText(mParam1);



        return view;
    }
}