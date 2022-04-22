package com.example.homear;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHodler> {

//    private CartClickedListeners cartClickedListeners;
//   private List<cartItem> cartItemList;
    ArrayList<cartItem> cartItemArrayList;

//    public CartAdapter(CartClickedListeners cartClickedListeners) {
//        this.cartClickedListeners = cartClickedListeners;
//    }

//    public void setShoeCartList(List<ShoeCart> shoeCartList) {
//        this.shoeCartList = shoeCartList;
//        notifyDataSetChanged();
//    }


    public CartAdapter(ArrayList<cartItem> cartItemArrayList) {
        this.cartItemArrayList = cartItemArrayList;
    }

    @NonNull
    @Override
    public CartViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new CartViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHodler holder, int position) {


//        ShoeCart shoeCart = shoeCartList.get(position);
//        holder.shoeImageView.setImageResource(shoeCart.getShoeImage());
//        holder.shoeNameTv.setText(shoeCart.getShoeName());
//        holder.shoeBrandNameTv.setText(shoeCart.getShoeBrandName());
//        holder.shoeQuantity.setText(shoeCart.getQuantity() + "");
//        holder.shoePriceTv.setText(shoeCart.getTotalItemPrice() + "");
        holder.shoeImageView.setImageResource(cartItemArrayList.get(position).image);
        holder.cartItemName.setText(cartItemArrayList.get(position).name);
        holder.cartItemPrice.setText(cartItemArrayList.get(position).price);
//        holder.cartItemColor.setText(cartItemArrayList.get(position).color);
//        holder.cartItemTexture.setText(cartItemArrayList.get(position).texture);

        boolean isExpandable=cartItemArrayList.get(position).isExpandable();
        holder.relativeLayout.setVisibility(isExpandable? View.VISIBLE:View.GONE);


//        holder.deleteShoeBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                cartClickedListeners.onDeleteClicked(shoeCart);
//            }
//        });
//
//
//        holder.addQuantityBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                cartClickedListeners.onPlusClicked(shoeCart);
//            }
//        });
//
//        holder.minusQuantityBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                cartClickedListeners.onMinusClicked(shoeCart);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return cartItemArrayList.size();

    }

    public class CartViewHodler extends RecyclerView.ViewHolder {

        private TextView cartItemName, cartItemPrice, cartItemColor, cartItemTexture,cartItemQuantity;
        private ImageView deleteShoeBtn;
        private ImageView shoeImageView;
        private ImageButton addQuantityBtn, minusQuantityBtn;
        LinearLayout linearLayout;
        RelativeLayout relativeLayout;

        public CartViewHodler(@NonNull View itemView) {
            super(itemView);

            cartItemName= itemView.findViewById(R.id.CartItemName);
            cartItemPrice= itemView.findViewById(R.id.CartItemPrice);
//            cartItemColor= itemView.findViewById(R.id.CartItemColor);
//            cartItemTexture= itemView.findViewById(R.id.CartItemTexture);
//            shoePriceTv = itemView.findViewById(R.id.CartItemColor);

            deleteShoeBtn = itemView.findViewById(R.id.eachCartItemDeleteBtn);
            shoeImageView = itemView.findViewById(R.id.eachCartItemIV);
            cartItemQuantity = itemView.findViewById(R.id.eachCartItemQuantityTV);
            addQuantityBtn = itemView.findViewById(R.id.eachCartItemAddQuantityBtn);
            minusQuantityBtn = itemView.findViewById(R.id.eachCartItemMinusQuantityBtn);

            linearLayout=itemView.findViewById(R.id.cart_item);
            relativeLayout=itemView.findViewById(R.id.expandable);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cartItem cart_item=cartItemArrayList.get(getAdapterPosition());
                    cart_item.setExpandable(!cart_item.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }

//    public interface CartClickedListeners {
//        void onDeleteClicked(ShoeCart shoeCart);
//
//        void onPlusClicked(ShoeCart shoeCart);
//
//        void onMinusClicked(ShoeCart shoeCart);
//    }
}