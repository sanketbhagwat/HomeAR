package com.example.homear;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class galleryAdapter extends RecyclerView.Adapter<galleryAdapter.ViewHolder> {
    private Context context;
    private List<String> images;

    public galleryAdapter(Context context, List<String> images) {
        this.context = context;
        this.images = images;
    }

    @NonNull
    @Override
    public galleryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(null);
//                LayoutInflater.from(context).inflate(R.layout.galleryItem,parent,false)
    }

    @Override
    public void onBindViewHolder(@NonNull galleryAdapter.ViewHolder holder, int position) {
        String image=images.get(position);
        Picasso.get().load(image).into(holder.image);


    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
