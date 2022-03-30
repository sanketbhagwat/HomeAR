package com.example.homear;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class CategoriesAdapter  extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    List<Categories> categoriesList;
    Context context;
    public CategoriesAdapter(){

    }

    public void setCategoriesList(List<Categories> categoriesList) {
        this.categoriesList = categoriesList;
    }

    public CategoriesAdapter(List<Categories> categoriesList, Context context) {
        this.categoriesList = categoriesList;
        this.context = context;
        this.categoriesList.removeAll(Collections.singleton(null));
    }

    @NonNull
    @Override
    public CategoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.category_cards,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesAdapter.ViewHolder holder, int position) {
        Categories categories = categoriesList.get(position);
        holder.category_name.setText(categories.getName());
        holder.category_image.setImageResource(categories.getImg());
//        Picasso.get().load(categories.getImg())
//                .into(holder.category_image);

    }

    @Override
    public int getItemCount() {

        if (categoriesList != null){
            return categoriesList.size();
        }else{
            return  0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView category_image;
        TextView  category_name;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            category_image=itemView.findViewById(R.id.category_image);
            category_name=itemView.findViewById(R.id.category_name);
        }
    }
}
