package com.example.homear;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class productAdapter extends RecyclerView.Adapter<productAdapter.prodViewHolder>{
    private ArrayList<products> productList;
//    private ItemClickListener clickListener;

    public productAdapter(ArrayList<products> productList){
        this.productList=productList;
//        this.clickListener= clickListener;


    }

    @NonNull
    @Override
    public prodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new prodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull prodViewHolder holder, int position) {
//        holder.prodImage.setImageResource( productList.get(position).img);
        Picasso.get().load(productList.get(position).img)
                        .into(holder.prodImage);
        holder.prodTitle.setText(productList.get(position).title);
        holder.prodPrice.setText(productList.get(position).price);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity) view.getContext();
                ProductFragment productFragment=new ProductFragment();
                Bundle args=new Bundle();
                args.putParcelable("product_obj",productList.get(holder.getAdapterPosition()));
                productFragment.setArguments(args);

                activity.getSupportFragmentManager().beginTransaction().
//                        .hide(activity.getSupportFragmentManager().findFragmentById(R.id.framecontainer));
//                activity.getSupportFragmentManager().beginTransaction().add(R.id.framecontainer,productFragment).addToBackStack(null).commit();


                        replace(R.id.framecontainer,productFragment).addToBackStack(null).commit();
//                transaction.hide(activity.getSupportFragmentManager().findFragmentById(R.id.brands));
//                transaction.add(R.id.framecontainer,productFragment);
//                transaction.addToBackStack(null).commit();
//                  itemClickListener.onItemClick(productList.get(position));
            }
        });

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                clickListener.onItemClick(productList.get(holder.getAdapterPosition()));
//
//            }
//        });

    }

    @Override
    public int getItemCount() {

        return productList.size() ;
    }

    class prodViewHolder extends RecyclerView.ViewHolder{
        private ImageView prodImage;
        private TextView prodTitle;
        private TextView prodPrice;

        public prodViewHolder(@NonNull View itemView) {
            super(itemView);
            prodImage = itemView.findViewById(R.id.prodImage);
            prodTitle = itemView.findViewById(R.id.prodTitle);
            prodPrice =itemView.findViewById(R.id.prodPrice);
        }
    }

    public interface ItemClickListener{
        public void onItemClick(products product);
    }
}
