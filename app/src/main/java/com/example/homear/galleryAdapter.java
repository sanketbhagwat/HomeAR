package com.example.homear;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class galleryAdapter extends RecyclerView.Adapter<galleryAdapter.ViewHolder> {
//    private Context context;
    private ArrayList<image_view> images;

//    public galleryAdapter(Context context, List<String> images) {
//        this.context = context;
//        this.images = images;
//    }


    public galleryAdapter(ArrayList<image_view> images) {
        this.images = images;
    }

    @NonNull
    @Override
    public galleryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.image_view,parent,false);
        return new ViewHolder(view);
//
    }

    @Override
    public void onBindViewHolder(@NonNull galleryAdapter.ViewHolder holder, int position) {
        holder.image.setImageResource(images.get(position).uri);




    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);

        }
    }
}
