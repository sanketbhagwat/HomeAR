package com.example.homear;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment {

    private static final int  PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE=121;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
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
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    user_adapter userAdapter;
    private int[] tabIcons = { R.drawable.outline_crop_original_24,
            R.drawable.outline_video_library_24,R.drawable.outline_favorite_24};
    galleryAdapter gallery_adapter;
    RecyclerView imageRecycler;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_user, container, false);
        viewPager2 = view.findViewById(R.id.viewPager2);
//
        tabLayout = view.findViewById(R.id.tabLayout);
        userAdapter=new user_adapter((FragmentActivity) view.getContext());

        viewPager2.setAdapter(userAdapter);
        new TabLayoutMediator(tabLayout,viewPager2,(tab, position) -> tab.setIcon(tabIcons[position])).attach();

        if (ContextCompat.checkSelfPermission(view.getContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
        }
        else {
            // has the permission.
            loadImages();
        }



        return view;
    }

    private void loadImages() {
        List<String>imagelist=new ArrayList<>();

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if (requestCode == PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "heree", Toast.LENGTH_SHORT).show();

                // permission was granted.
                loadImages();
//                } else {
//                    // permission denied.
//                    // tell the user the action is cancelled
//                    AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
//                    alertDialog.setMessage(getString(R.string.cannot_save_image));
//                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getString(R.string.ok),
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.dismiss();
//                                }
//                            });
//                    alertDialog.show();
//                }
//                return;
            }
        }
    }




}